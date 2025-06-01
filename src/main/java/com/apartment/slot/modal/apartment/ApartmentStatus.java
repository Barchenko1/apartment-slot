package com.apartment.slot.modal.apartment;

import com.apartment.slot.modal.base.AbstractConstantEntity;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

@Entity
@Table(name = "apartment_status")
@SuperBuilder
@NoArgsConstructor
public class ApartmentStatus extends AbstractConstantEntity {
}
