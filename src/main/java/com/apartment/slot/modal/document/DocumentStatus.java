package com.apartment.slot.modal.document;

import com.apartment.slot.modal.base.AbstractConstantEntity;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

@Entity
@Table(name = "document_status")
@SuperBuilder
@NoArgsConstructor
public class DocumentStatus extends AbstractConstantEntity {
}
