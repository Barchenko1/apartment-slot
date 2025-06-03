package com.apartment.slot.modal.apartment.address;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Entity
@Table(name = "mapcoordinate_id")
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class MapCoordinate {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", updatable = false, nullable = false)
    private long id;
    @Column(precision = 9, scale = 6, nullable = false)
    private BigDecimal latitude;
    @Column(precision = 9, scale = 6, nullable = false)
    private BigDecimal longitude;
    private boolean isShow;
}
