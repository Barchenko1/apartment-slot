package com.apartment.slot.modal.slot;

import com.apartment.slot.modal.base.AbstractConstantEntity;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

import java.time.LocalTime;

@EqualsAndHashCode(callSuper = true)
@Entity
@Table(name = "time_duration")
@Data
@SuperBuilder
@AllArgsConstructor
@NoArgsConstructor
public class TimeDuration extends AbstractConstantEntity {
    @Column(name = "start_time", nullable = false)
    private LocalTime startTime;
    @Column(name = "end_time", nullable = false)
    private LocalTime endTime;
    @Column(name = "duration_in_min", nullable = false)
    private int duration;
}
