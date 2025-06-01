package com.apartment.slot.modal.apartment.review;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.NamedQueries;
import jakarta.persistence.NamedQuery;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

@Entity
@Table(name = "rating")
@Data
@SuperBuilder
@AllArgsConstructor
@NoArgsConstructor
@NamedQueries({
        @NamedQuery(
                name = "Rating.findByValue",
                query = "SELECT r FROM Rating r WHERE r.value = : value"
        ),
        @NamedQuery(
                name = "Rating.all",
                query = "SELECT r FROM Rating r"
        )
})
public class Rating {
        @Id
        @GeneratedValue(strategy = GenerationType.IDENTITY)
        @Column(name = "id", updatable = false, nullable = false)
        private long id;
        @Column(name = "label", nullable = false)
        private String label;
        @Column(name = "value", unique = true, nullable = false)
        private String value;

}
