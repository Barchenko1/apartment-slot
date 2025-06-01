package com.apartment.slot.modal.user.creditcard;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "user_credit_card")
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@NamedQueries({
        @NamedQuery(
                name = "UserCreditCard.findByCreditCardNumber",
                query = "SELECT c FROM CreditCard c WHERE c.cardNumber =: cardNumber"
        ),
})
public class UserCreditCard {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", updatable = false, nullable = false)
    private long id;
    @OneToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL, orphanRemoval = true)
    @JoinColumn(name = "credit_card_id")
    private CreditCard creditCard;
    private boolean isDefault;
}
