package com.apartment.slot.modal.apartment.price;

import com.apartment.slot.modal.base.AbstractConstantEntity;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

@EqualsAndHashCode(callSuper = true)
@Entity
@Table(name = "price_type")
@Data
@SuperBuilder
@NoArgsConstructor
public class PriceType extends AbstractConstantEntity {
}
