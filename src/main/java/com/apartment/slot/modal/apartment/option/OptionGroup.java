package com.apartment.slot.modal.apartment.option;

import com.apartment.slot.modal.base.AbstractConstantEntity;
import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.NamedQueries;
import jakarta.persistence.NamedQuery;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

import java.util.ArrayList;
import java.util.List;

@EqualsAndHashCode(callSuper = true)
@Entity
@Table(name = "option_group")
@NamedQueries({
        @NamedQuery(
                name = "OptionGroup.findByValueWithOptionItems",
                query = "SELECT og FROM OptionGroup og " +
                        "LEFT JOIN FETCH og.optionItems oi " +
                        "WHERE og.value = : value"
        ),
        @NamedQuery(
                name = "OptionGroup.findWithOptionItems",
                query = "SELECT og FROM OptionGroup og " +
                        "LEFT JOIN FETCH og.optionItems oi"
        ),
        @NamedQuery(
                name = "OptionGroup.findByValue",
                query = "SELECT og FROM OptionGroup og " +
                        "WHERE og.value = : value"
        ),
})
@Data
@SuperBuilder
@AllArgsConstructor
@NoArgsConstructor
public class OptionGroup extends AbstractConstantEntity {
    @OneToMany(mappedBy = "optionGroup", fetch = FetchType.LAZY, cascade = CascadeType.ALL, orphanRemoval = true)
    @Builder.Default
    private List<OptionItem> optionItems = new ArrayList<>();

    public void addOptionItem(OptionItem optionItem) {
        this.optionItems.add(optionItem);
        optionItem.setOptionGroup(this);
    }

    public void removeOptionItem(OptionItem optionItem) {
        this.optionItems.remove(optionItem);
        optionItem.setOptionGroup(null);
    }
}
