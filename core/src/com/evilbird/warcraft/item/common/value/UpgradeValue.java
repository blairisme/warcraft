/*
 * Copyright (c) 2019, Blair Butterworth
 *
 * This work is licensed under the MIT License. To view a copy of this
 * license, visit
 *
 *        https://opensource.org/licenses/MIT
 */

package com.evilbird.warcraft.item.common.value;

import com.evilbird.engine.item.ItemGroup;
import com.evilbird.warcraft.item.common.upgrade.Upgrade;
import com.evilbird.warcraft.item.common.upgrade.UpgradeRank;
import com.evilbird.warcraft.item.common.upgrade.UpgradeSeries;
import com.evilbird.warcraft.item.data.player.Player;
import com.evilbird.warcraft.item.unit.Unit;
import com.google.gson.annotations.JsonAdapter;
import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;
import org.apache.commons.lang3.builder.ToStringBuilder;

import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

/**
 * A {@link Value} implementation representing a game object property that can
 * be modified by the presence of an {@link Upgrade} belonging to the objects
 * parent.
 *
 * @author Blair Butterworth
 */
@JsonAdapter(ValueSerializer.class)
public class UpgradeValue implements ModifiedValue
{
    public static final UpgradeValue Zero = new UpgradeValue(0, Collections.emptyMap());

    protected int base;
    protected Map<Upgrade, Integer> upgrades;

    public UpgradeValue(UpgradeSeries series, int base, int improved) {
        this(series, base, improved, improved);
    }

    public UpgradeValue(UpgradeSeries series, int base, int improved, int advanced) {
        this(base, getUpgrades(series, base, improved, advanced));
    }

    public UpgradeValue(int base, Map<Upgrade, Integer> upgrades) {
        this.base = base;
        this.upgrades = upgrades;
    }

    @Override
    public int getValue(Unit unit) {
        int result = base;
        for (Upgrade upgrade: getOwnedUpgrades(unit)) {
            result += upgrades.getOrDefault(upgrade, 0);
        }
        return result;
    }

    @Override
    public int getBaseValue(Unit unit) {
        return base;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == null) { return false; }
        if (obj == this) { return true; }
        if (obj.getClass() != getClass()) { return false; }

        UpgradeValue that = (UpgradeValue)obj;
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

    private static Map<Upgrade, Integer> getUpgrades(UpgradeSeries series, int base, int improved, int advanced) {
        Map<Upgrade, Integer> upgrades = new HashMap<>(2);
        for (Upgrade upgrade: series.getUpgrades()) {
            UpgradeRank rank = upgrade.getRank();

            if (rank == UpgradeRank.Improved) {
                upgrades.put(upgrade, base - improved);
            } else if (rank == UpgradeRank.Advanced) {
                upgrades.put(upgrade, improved - advanced);
            }
        }
        return upgrades;
    }

    private Collection<Upgrade> getOwnedUpgrades(Unit unit) {
        ItemGroup parent = unit.getParent();
        if (parent instanceof Player) {
            return getOwnedUpgrades((Player)parent);
        }
        return Collections.emptyList();
    }

    private Collection<Upgrade> getOwnedUpgrades(Player player) {
        Set<Upgrade> ownedUpgrades = new HashSet<>();
        for (Upgrade upgrade: upgrades.keySet()) {
            if (player.hasUpgrade(upgrade)) {
                ownedUpgrades.add(upgrade);
            }
        }
        return ownedUpgrades;
    }
}
