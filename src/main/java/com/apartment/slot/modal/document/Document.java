package com.apartment.slot.modal.document;

import com.apartment.slot.modal.apartment.Apartment;
import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "document")
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Document {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", updatable = false, nullable = false)
    private long id;
    @ManyToOne(fetch = FetchType.LAZY, cascade = {CascadeType.MERGE})
    private Apartment apartment;
    @OneToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL, orphanRemoval = true)
    @JoinColumn(name = "ownerSubscriberData_id", nullable = false)
    private SubscriberData ownerSubscriberDate;
    @OneToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL, orphanRemoval = true)
    @JoinColumn(name = "clientSubscriberDate_id", nullable = false)
    private SubscriberData clientSubscriberDate;
    @OneToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL, orphanRemoval = true)
    @JoinColumn(name = "document_id")
    @Builder.Default
    private Set<UserData> additionalUserDataSet = new HashSet<>();
    private Date subscribeDate;
    private int dateOfPayment;
    private String ownerIbanCode;
    @ManyToOne(fetch = FetchType.LAZY, cascade = {CascadeType.MERGE})
    private DocumentTemplate documentTemplate;
    @ManyToOne(fetch = FetchType.LAZY, cascade = {CascadeType.MERGE})
    private DocumentStatus status;
}
