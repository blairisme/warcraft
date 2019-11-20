/*
 * Copyright (c) 2019, Blair Butterworth
 *
 * This work is licensed under the MIT License. To view a copy of this
 * license, visit
 *
 *        https://opensource.org/licenses/MIT
 */

package com.evilbird.warcraft.object.common.value;

import com.evilbird.engine.common.collection.Maps;
import com.evilbird.engine.object.GameObjectGroup;
import com.evilbird.warcraft.object.common.upgrade.Upgrade;
import com.evilbird.warcraft.object.common.upgrade.UpgradeRank;
import com.evilbird.warcraft.object.common.upgrade.UpgradeSeries;
import com.evilbird.warcraft.object.data.player.Player;
import com.evilbird.warcraft.object.unit.Unit;
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

    protected float base;
    protected Map<Upgrade, Float> upgrades;

    public UpgradeValue(UpgradeSeries series, float base, float improved) {
        this(series, base, improved, improved);
    }

    public UpgradeValue(UpgradeSeries series, float base, float improved, float advanced) {
        this(base, getUpgrades(series, base, improved, advanced));
    }

    public UpgradeValue(float base, Map<Upgrade, Float> upgrades) {
        this.base = base;
        this.upgrades = upgrades;
    }

    @Override
    public float getValue(Unit unit) {
        float result = base;
        for (Upgrade upgrade: getOwnedUpgrades(unit)) {
            result += Maps.getOrDefault(upgrades, upgrade, 0f);
        }
        return result;
    }

    @Override
    public float getBaseValue(Unit unit) {
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

    private static Map<Upgrade, Float> getUpgrades(UpgradeSeries series, float base, float improved, float advanced) {
        Map<Upgrade, Float> upgrades = new HashMap<>(2);
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
        GameObjectGroup parent = unit.getParent();
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
