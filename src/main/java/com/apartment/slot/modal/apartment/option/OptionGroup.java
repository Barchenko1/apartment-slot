package com.apartment.slot.modal.apartment.option;

import com.b2c.prototype.modal.base.constant.AbstractConstantEntity;
import jakarta.persistence.*;
import lombok.*;
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
        @NamedQuery(
                name = "OptionGroup.all",
                query = "SELECT og FROM OptionGroup og"
        ),
        @NamedQuery(
                name = "OptionGroup.withOptionItemsAndArticularItems",
                query = "SELECT DISTINCT og FROM OptionGroup og " +
                        "LEFT JOIN FETCH og.optionItems oi " +
                        "LEFT JOIN FETCH oi.articularItems ai " +
                        "LEFT JOIN FETCH ai.optionItems ao " +
                        "LEFT JOIN FETCH ai.totalPrice t " +
                        "LEFT JOIN FETCH ai.fullPrice f " +
                        "LEFT JOIN FETCH t.currency c1 " +
                        "LEFT JOIN FETCH f.currency c2 " +
                        "WHERE og.value = :value"
        )
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
