package com.apartment.slot.modal.apartment;

import com.apartment.slot.modal.base.AbstractConstantEntity;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

@Entity
@Table(name = "country")
@SuperBuilder
@NoArgsConstructor
public class Country extends AbstractConstantEntity {
}
