package com.apartment.slot.modal.user;

import com.apartment.slot.modal.base.AbstractConstantEntity;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

@Entity
@Table(name = "user_status")
@SuperBuilder
@NoArgsConstructor
public class UserStatus extends AbstractConstantEntity {
}
