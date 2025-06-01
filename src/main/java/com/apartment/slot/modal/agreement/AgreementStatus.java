package com.apartment.slot.modal.agreement;

import com.apartment.slot.modal.base.AbstractConstantEntity;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

@Entity
@Table(name = "agreement_status")
@SuperBuilder
@NoArgsConstructor
public class AgreementStatus extends AbstractConstantEntity {
}
