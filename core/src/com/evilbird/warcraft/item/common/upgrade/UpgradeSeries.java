/*
 * Copyright (c) 2019, Blair Butterworth
 *
 * This work is licensed under the MIT License. To view a copy of this
 * license, visit
 *
 *        https://opensource.org/licenses/MIT
 */

package com.evilbird.warcraft.item.common.upgrade;

import com.evilbird.engine.common.collection.Sets;

import java.util.Collection;
import java.util.Collections;
import java.util.Set;

import static com.evilbird.warcraft.item.common.upgrade.Upgrade.GoldProduction1;
import static com.evilbird.warcraft.item.common.upgrade.Upgrade.GoldProduction2;
import static com.evilbird.warcraft.item.common.upgrade.Upgrade.MeleeDamage1;
import static com.evilbird.warcraft.item.common.upgrade.Upgrade.MeleeDamage2;
import static com.evilbird.warcraft.item.common.upgrade.Upgrade.MeleeDefence1;
import static com.evilbird.warcraft.item.common.upgrade.Upgrade.MeleeDefence2;
import static com.evilbird.warcraft.item.common.upgrade.Upgrade.MeleeType1;
import static com.evilbird.warcraft.item.common.upgrade.Upgrade.NavalDamage1;
import static com.evilbird.warcraft.item.common.upgrade.Upgrade.NavalDamage2;
import static com.evilbird.warcraft.item.common.upgrade.Upgrade.NavalDefence1;
import static com.evilbird.warcraft.item.common.upgrade.Upgrade.NavalDefence2;
import static com.evilbird.warcraft.item.common.upgrade.Upgrade.OilProduction1;
import static com.evilbird.warcraft.item.common.upgrade.Upgrade.OilProduction2;
import static com.evilbird.warcraft.item.common.upgrade.Upgrade.RangedAccuracy1;
import static com.evilbird.warcraft.item.common.upgrade.Upgrade.RangedDamage1;
import static com.evilbird.warcraft.item.common.upgrade.Upgrade.RangedDamage2;
import static com.evilbird.warcraft.item.common.upgrade.Upgrade.RangedSight1;
import static com.evilbird.warcraft.item.common.upgrade.Upgrade.RangedType1;
import static com.evilbird.warcraft.item.common.upgrade.Upgrade.RangedWeapon1;
import static com.evilbird.warcraft.item.common.upgrade.Upgrade.SiegeDamage1;
import static com.evilbird.warcraft.item.common.upgrade.Upgrade.SiegeDamage2;
import static com.evilbird.warcraft.item.common.upgrade.Upgrade.WoodProduction1;
import static com.evilbird.warcraft.item.common.upgrade.Upgrade.WoodProduction2;

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
            case MeleeDamage: return Sets.of(MeleeDamage1, MeleeDamage2);
            case MeleeType: return Collections.singleton(MeleeType1);
            case RangedDamage: return Sets.of(RangedDamage1, RangedDamage2);
            case RangedAccuracy: return Sets.of(RangedAccuracy1);
            case RangedType: return Sets.of(RangedType1);
            case RangedSight: return Sets.of(RangedSight1);
            case RangedWeapon: return Sets.of(RangedWeapon1);
            case NavalDamage: return Sets.of(NavalDamage1, NavalDamage2);
            case SiegeDamage: return Sets.of(SiegeDamage1, SiegeDamage2);
            case MeleeDefence: return Sets.of(MeleeDefence1, MeleeDefence2);
            case NavalDefence: return Sets.of(NavalDefence1, NavalDefence2);
            case GoldProduction : return Sets.of(GoldProduction1, GoldProduction2);
            case OilProduction: return Sets.of(OilProduction1, OilProduction2);
            case WoodProduction: return Sets.of(WoodProduction1, WoodProduction2);
            default: throw new UnsupportedOperationException(this.toString());
        }
    }
}
