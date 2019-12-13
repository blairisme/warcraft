/*
 * Copyright (c) 2019, Blair Butterworth
 *
 * This work is licensed under the MIT License. To view a copy of this
 * license, visit
 *
 *        https://opensource.org/licenses/MIT
 */

package com.evilbird.warcraft.data.upgrade;

import com.evilbird.engine.common.collection.Sets;

import java.util.Collection;
import java.util.Collections;
import java.util.Set;

/**
 * Defines a grouping of {@link Upgrade upgrades}, where each
 * {@link UpgradeRank rank} builds upon the previous to improve a game objects
 * attributes.
 *
 * @author Blair Butterworth
 */
public enum UpgradeSeries
{
    None,
    MeleeDamage,
    MeleeDefence,
    MeleeType,
    NavalDamage,
    NavalDefence,
    RangedAccuracy,
    RangedDamage,
    RangedSight,
    RangedWeapon,
    RangedType,
    SiegeDamage,
    GoldProduction,
    OilProduction,
    WoodProduction;

    private Set<Upgrade> upgrades;

    /**
     * Returns the {@link Collection} of {@link Upgrade Upgrades} in the
     * {@code UpgradeSeries}.
     *
     * @return an unmodifiable {@code Collection} of {@code Upgrade Upgrades}.
     */
    public Set<Upgrade> getUpgrades() {
        if (upgrades == null) {
            upgrades = Collections.unmodifiableSet(getUpgradeSet());
        }
        return upgrades;
    }

    private Set<Upgrade> getUpgradeSet() {
        switch (this) {
            case None: return Collections.emptySet();
            case MeleeDamage: return Sets.of(Upgrade.MeleeDamage1, Upgrade.MeleeDamage2);
            case MeleeType: return Collections.singleton(Upgrade.MeleeType1);
            case RangedDamage: return Sets.of(Upgrade.RangedDamage1, Upgrade.RangedDamage2);
            case RangedAccuracy: return Sets.of(Upgrade.RangedAccuracy1);
            case RangedType: return Sets.of(Upgrade.RangedType1);
            case RangedSight: return Sets.of(Upgrade.RangedSight1);
            case RangedWeapon: return Sets.of(Upgrade.RangedWeapon1);
            case NavalDamage: return Sets.of(Upgrade.NavalDamage1, Upgrade.NavalDamage2);
            case SiegeDamage: return Sets.of(Upgrade.SiegeDamage1, Upgrade.SiegeDamage2);
            case MeleeDefence: return Sets.of(Upgrade.MeleeDefence1, Upgrade.MeleeDefence2);
            case NavalDefence: return Sets.of(Upgrade.NavalDefence1, Upgrade.NavalDefence2);
            case GoldProduction : return Sets.of(Upgrade.GoldProduction1, Upgrade.GoldProduction2);
            case OilProduction: return Sets.of(Upgrade.OilProduction1, Upgrade.OilProduction2);
            case WoodProduction: return Sets.of(Upgrade.WoodProduction1, Upgrade.WoodProduction2);
            default: throw new UnsupportedOperationException(this.toString());
        }
    }
}
