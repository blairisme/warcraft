/*
 * Copyright (c) 2019, Blair Butterworth
 *
 * This work is licensed under the MIT License. To view a copy of this
 * license, visit
 *
 *        https://opensource.org/licenses/MIT
 */

package com.evilbird.warcraft.item.common.upgrade;

import com.google.gson.annotations.JsonAdapter;
import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;
import org.apache.commons.lang3.builder.ToStringBuilder;

import java.util.Collections;
import java.util.Map;
import java.util.Set;

/**
 * Represents a numerical value that can be improved by a {@link Set} of
 * {@link Upgrade Upgrades}, each with ank
 *
 * part of the overall value.
 *
 * @author Blair Butterworth
 */
@JsonAdapter(UpgradeValueSerializer.class)
public class UpgradeCombination implements UpgradeValue<Integer>
{
    public static final UpgradeCombination Zero = new UpgradeCombination(0, Collections.emptyMap());

    protected int base;
    protected Map<Upgrade, Integer> upgrades;

    public UpgradeCombination(int base, Map<Upgrade, Integer> upgrades) {
        this.base = base;
        this.upgrades = upgrades;
    }

    @Override
    public Set<Upgrade> getUpgrades() {
        return upgrades.keySet();
    }

    @Override
    public Integer getBaseValue() {
        return base;
    }

    @Override
    public Integer getValue(Set<Upgrade> researched) {
        int result = base;
        for (Upgrade upgrade: researched) {
            result += upgrades.getOrDefault(upgrade, 0);
        }
        return result;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == null) { return false; }
        if (obj == this) { return true; }
        if (obj.getClass() != getClass()) { return false; }

        UpgradeCombination that = (UpgradeCombination)obj;
        return new EqualsBuilder()
            .append(base, that.base)
            .append(upgrades, that.upgrades)
            .isEquals();
    }

    @Override
    public int hashCode() {
        return new HashCodeBuilder(17, 37)
            .append(base)
            .append(upgrades)
            .toHashCode();
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this)
            .append("base", base)
            .append("upgrades", upgrades)
            .toString();
    }
}
