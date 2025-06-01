package com.apartment.slot.configuration;

import com.tm.core.configuration.dbType.DatabaseConfigurationAnnotationClass;
import com.tm.core.configuration.dbType.DatabaseType;
import com.tm.core.configuration.dbType.DatabaseTypeConfiguration;
import com.tm.core.configuration.entityManager.EntityManagerFactoryManager;
import com.tm.core.configuration.entityManager.IEntityManagerFactoryManager;
import com.tm.core.configuration.session.ISessionFactoryManager;
import com.tm.core.configuration.session.SessionFactoryManager;
import com.tm.core.finder.manager.EntityMappingManager;
import com.tm.core.finder.manager.IEntityMappingManager;
import com.tm.core.finder.scanner.EntityScanner;
import com.tm.core.finder.scanner.IEntityScanner;
import com.tm.core.process.dao.IFetchHandler;
import com.tm.core.process.dao.fetch.EntityManagerFetchHandler;
import com.tm.core.process.dao.fetch.SessionFetchHandler;
import com.tm.core.process.dao.query.IQueryService;
import com.tm.core.process.dao.query.QueryService;
import com.tm.core.process.manager.generic.IGenericOperationManager;
import com.tm.core.process.manager.generic.operator.GenericOperationManager;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.DependsOn;

@Configuration
public class BeanConfiguration {

    @Value("${entity.package.path}")
    private String entityPackagePath;
    private static final String MAIN_WRITE_DATABASE_CONFIG = "hikari.hibernate.cfg.xml";
    private static final String MAIN_READ_DATABASE_CONFIG = "";

    public DatabaseTypeConfiguration getDatabaseTypeConfiguration() {
        DatabaseConfigurationAnnotationClass[] databaseConfigurationAnnotationClass =
                new DatabaseConfigurationAnnotationClass[] {
                        new DatabaseConfigurationAnnotationClass("hikari.hibernate.cfg.xml"),
                };

        return new DatabaseTypeConfiguration(DatabaseType.WRITE, databaseConfigurationAnnotationClass);
    }

    @Bean
    public EntityManagerFactory entityManagerFactory() {
        IEntityManagerFactoryManager entityManagerFactoryManager = EntityManagerFactoryManager.getInstance(getDatabaseTypeConfiguration());
        return entityManagerFactoryManager.getEntityManagerFactory(DatabaseType.WRITE, MAIN_WRITE_DATABASE_CONFIG);
    }

    @Bean
    public EntityManager entityManager(EntityManagerFactory entityManagerFactory) {
        return entityManagerFactory.createEntityManager();
    }

    @Bean
    public IEntityMappingManager entityMappingManager() {
        return new EntityMappingManager();
    }

    @Bean
    @DependsOn({"entityMappingManager"})
    public IEntityScanner entityScanner() {
        return new EntityScanner(entityMappingManager(), entityPackagePath);
    }

    @Bean
    @DependsOn({"entityScanner"})
    public IQueryService queryService() {
        return new QueryService(entityMappingManager());
    }

    @Bean
    public IFetchHandler fetchHandler(EntityManager entityManager) {
        return new EntityManagerFetchHandler(entityManager, queryService());
    }

//    @Bean
//    public IGenericOperationManager genericOperationManager(SessionFactory sessionFactory) {
//        return new GenericOperationManager(sessionFactory, entityPackagePath);
//    }

}
