/*
 * Copyright (c) 2020, Blair Butterworth
 *
 * This Source Code Form is subject to the terms of the Mozilla Public
 * License, v. 2.0. If a copy of the MPL was not distributed with this
 * file, You can obtain one at http://mozilla.org/MPL/2.0/.
 */

package com.evilbird.warcraft.data.upgrade;

import com.evilbird.engine.common.lang.Identifier;
import com.evilbird.warcraft.data.product.Product;
import org.apache.commons.lang3.EnumUtils;

import static com.evilbird.warcraft.data.upgrade.UpgradeRank.Advanced;
import static com.evilbird.warcraft.data.upgrade.UpgradeRank.Improved;
import static com.evilbird.warcraft.data.upgrade.UpgradeSeries.GoldProduction;
import static com.evilbird.warcraft.data.upgrade.UpgradeSeries.MeleeDamage;
import static com.evilbird.warcraft.data.upgrade.UpgradeSeries.MeleeDefence;
import static com.evilbird.warcraft.data.upgrade.UpgradeSeries.MeleeType;
import static com.evilbird.warcraft.data.upgrade.UpgradeSeries.NavalDamage;
import static com.evilbird.warcraft.data.upgrade.UpgradeSeries.NavalDefence;
import static com.evilbird.warcraft.data.upgrade.UpgradeSeries.OilProduction;
import static com.evilbird.warcraft.data.upgrade.UpgradeSeries.RangedAccuracy;
import static com.evilbird.warcraft.data.upgrade.UpgradeSeries.RangedDamage;
import static com.evilbird.warcraft.data.upgrade.UpgradeSeries.RangedSight;
import static com.evilbird.warcraft.data.upgrade.UpgradeSeries.RangedType;
import static com.evilbird.warcraft.data.upgrade.UpgradeSeries.RangedWeapon;
import static com.evilbird.warcraft.data.upgrade.UpgradeSeries.SiegeDamage;
import static com.evilbird.warcraft.data.upgrade.UpgradeSeries.WoodProduction;

/**
 * Defines upgrades that can be applied to game objects to improve their
 * attributes. Upgrades usually represent a single rank in a series of
 * upgrades, where each rank provides a further improvement to a unit
 * attribute.
 *
 * @author Blair Butterworth
 */
@SuppressWarnings("checkstyle:MethodParamPad")
public enum Upgrade implements Identifier, Product
{
    MeleeDamage1        (MeleeDamage,       Improved),
    MeleeDamage2        (MeleeDamage,       Advanced),
    MeleeDefence1       (MeleeDefence,      Improved),
    MeleeDefence2       (MeleeDefence,      Advanced),
    MeleeType1          (MeleeType,         Advanced),
    RangedDamage1       (RangedDamage,      Improved),
    RangedDamage2       (RangedDamage,      Advanced),
    RangedAccuracy1     (RangedAccuracy,    Improved),
    RangedSight1        (RangedSight,       Improved),
    RangedType1         (RangedType,        Improved),
    RangedWeapon1       (RangedWeapon,      Improved),
    NavalDamage1        (NavalDamage,       Improved),
    NavalDamage2        (NavalDamage,       Advanced),
    NavalDefence1       (NavalDefence,      Improved),
    NavalDefence2       (NavalDefence,      Advanced),
    SiegeDamage1        (SiegeDamage,       Improved),
    SiegeDamage2        (SiegeDamage,       Advanced),
    GoldProduction1     (GoldProduction,    Improved),
    GoldProduction2     (GoldProduction,    Advanced),
    OilProduction1      (OilProduction,     Improved),
    OilProduction2      (OilProduction,     Advanced),
    WoodProduction1     (WoodProduction,    Improved),
    WoodProduction2     (WoodProduction,    Advanced),

    BlizzardUpgrade,
    BloodlustUpgrade,
    DeathAndDecayUpgrade,
    ExorcismUpgrade,
    FlameShieldUpgrade,
    HasteUpgrade,
    HealingUpgrade,
    InvisibilityUpgrade,
    PolymorphUpgrade,
    RaiseTheDeadUpgrade,
    RunesUpgrade,
    SlowUpgrade,
    UnholyArmourUpgrade,
    WhirlwindUpgrade,
    None;

    private UpgradeRank rank;
    private UpgradeSeries series;

    Upgrade() {
        this(UpgradeSeries.None, UpgradeRank.None);
    }

    Upgrade(UpgradeSeries series, UpgradeRank rank) {
        this.series = series;
        this.rank = rank;
    }

    /**
     * Returns the {@link UpgradeSeries} the {@code Upgrade} belongs to, if
     * any.
     */
    public UpgradeSeries getSeries() {
        return series;
    }

    /**
     * Returns the {@link UpgradeRank} of the {@code Upgrade} in the
     * {@link UpgradeSeries} it belongs to, if any.
     */
    public UpgradeRank getRank() {
        return rank;
    }

    /**
     * Returns a {@link UpgradeProduction} instance defining the resources and
     * times required to produce the {@code Upgrade}.
     */
    @Override
    public UpgradeProduction getProduction() {
        String name = name();

        if (EnumUtils.isValidEnum(UpgradeProduction.class, name)) {
            return UpgradeProduction.valueOf(name);
        }
        return UpgradeProduction.None;
    }
}
