package com.apartment.slot.modal.user.creditcard;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "credit_card")
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class CreditCard {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", updatable = false, nullable = false)
    private long id;
    private String cardNumber;
    private int monthOfExpire;
    private int yearOfExpire;
    private String cvv;
    private boolean isActive;
    private String ownerName;
    private String ownerSecondName;
}
