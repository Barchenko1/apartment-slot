package com.apartment.slot.modal.apartment.option;

import com.apartment.slot.modal.apartment.Apartment;
import com.apartment.slot.modal.base.AbstractConstantEntity;
import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.NamedQueries;
import jakarta.persistence.Table;

import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.ToString;
import lombok.experimental.SuperBuilder;

import java.util.HashSet;
import java.util.Set;

@EqualsAndHashCode(callSuper = true)
@Entity
@Table(name = "option_item")
@Data
@SuperBuilder
@NoArgsConstructor
@NamedQueries({
})
public class OptionItem extends AbstractConstantEntity {
    @ManyToOne(fetch = FetchType.LAZY, cascade = {CascadeType.MERGE, CascadeType.PERSIST})
    @JoinColumn(name = "option_group_id", nullable = false)
    @ToString.Exclude
    @EqualsAndHashCode.Exclude
    private OptionGroup optionGroup;
    @ManyToMany(mappedBy = "optionItems")
    @Builder.Default
    @ToString.Exclude
    @EqualsAndHashCode.Exclude
    private Set<Apartment> apartments = new HashSet<>();

    public void addApartment(Apartment apartment) {
        this.apartments.add(apartment);
        apartment.getOptionItems().add(this);
    }

    public void removeApartment(Apartment apartment) {
        this.apartments.remove(apartment);
        apartment.getOptionItems().remove(this);
    }
}
