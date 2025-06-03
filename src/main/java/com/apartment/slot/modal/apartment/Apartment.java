package com.apartment.slot.modal.apartment;

import com.apartment.slot.modal.apartment.address.Address;
import com.apartment.slot.modal.apartment.option.OptionItem;
import com.apartment.slot.modal.apartment.price.PriceHistory;
import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.ToString;
import org.hibernate.annotations.SQLRestriction;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Entity
@Table(name = "apartment")
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Apartment {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", updatable = false, nullable = false)
    private long id;
    @Column(name = "apartment_uuid", unique = true, nullable = false)
    private String apartmentId;
    private String photoFolderPath;
    private long dateOfCreate;
    private String apartmentTitle;
    private double squareMeters;
    private int flour;
    private Date dateOfCreation;
    private Date dateOfAble;
    private int yearBuilt;
    private String note;
    @ManyToMany(cascade = {CascadeType.DETACH, CascadeType.MERGE, CascadeType.PERSIST, CascadeType.REFRESH})
    @JoinTable(
            name = "apartment_option_item",
            joinColumns = {@JoinColumn(name = "apartment_id")},
            inverseJoinColumns = {@JoinColumn(name = "option_item_id")}
    )
    @Builder.Default
    @ToString.Exclude
    @EqualsAndHashCode.Exclude
    private Set<OptionItem> optionItems = new HashSet<>();
    @OneToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL, orphanRemoval = true)
    @JoinColumn(name = "address_id", nullable = false)
    private Address address;
    @OneToMany(mappedBy = "apartment", fetch = FetchType.LAZY, cascade = CascadeType.ALL, orphanRemoval = true)
    @Builder.Default
    @SQLRestriction("price_type = 'PUBLIC_SERVICE'")
    private List<PriceHistory> publicServicePriceHistory = new ArrayList<>();
    @OneToMany(mappedBy = "apartment", fetch = FetchType.LAZY, cascade = CascadeType.ALL, orphanRemoval = true)
    @Builder.Default
    @SQLRestriction("price_type = 'DEPOSIT'")
    private List<PriceHistory> depositPriceHistory = new ArrayList<>();
    @OneToMany(mappedBy = "apartment", fetch = FetchType.LAZY, cascade = CascadeType.ALL, orphanRemoval = true)
    @Builder.Default
    @SQLRestriction("price_type = 'RENT'")
    private List<PriceHistory> rentPriceHistory = new ArrayList<>();
    @OneToMany(mappedBy = "apartment", fetch = FetchType.LAZY, cascade = CascadeType.ALL, orphanRemoval = true)
    @Builder.Default
    @SQLRestriction("price_type = 'TOTAL'")
    private List<PriceHistory> totalPriceHistory = new ArrayList<>();
    @ManyToOne(fetch = FetchType.LAZY, cascade = {CascadeType.PERSIST, CascadeType.MERGE})
    @JoinColumn(name = "propery_type_id")
    private PropertyType propertyType;
    @ManyToOne(fetch = FetchType.LAZY, cascade = {CascadeType.PERSIST, CascadeType.MERGE})
    @JoinColumn(name = "status_id")
    private ApartmentStatus status;

    public void addOptionItem(OptionItem optionItem) {
        this.optionItems.add(optionItem);
        optionItem.getApartments().add(this);
    }

    public void removeOptionItem(OptionItem optionItem) {
        this.optionItems.remove(optionItem);
        optionItem.getApartments().remove(this);
    }
}
