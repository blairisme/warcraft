/*
 * Copyright (c) 2019, Blair Butterworth
 *
 * This work is licensed under the MIT License. To view a copy of this
 * license, visit
 *
 *        https://opensource.org/licenses/MIT
 */

package com.evilbird.warcraft.object.common.upgrade;

import com.evilbird.engine.common.collection.EnumUtils;
import com.evilbird.engine.common.lang.Identifier;

import static com.evilbird.warcraft.object.common.upgrade.UpgradeRank.Advanced;
import static com.evilbird.warcraft.object.common.upgrade.UpgradeRank.Improved;
import static com.evilbird.warcraft.object.common.upgrade.UpgradeSeries.GoldProduction;
import static com.evilbird.warcraft.object.common.upgrade.UpgradeSeries.MeleeDamage;
import static com.evilbird.warcraft.object.common.upgrade.UpgradeSeries.MeleeDefence;
import static com.evilbird.warcraft.object.common.upgrade.UpgradeSeries.MeleeType;
import static com.evilbird.warcraft.object.common.upgrade.UpgradeSeries.NavalDamage;
import static com.evilbird.warcraft.object.common.upgrade.UpgradeSeries.NavalDefence;
import static com.evilbird.warcraft.object.common.upgrade.UpgradeSeries.OilProduction;
import static com.evilbird.warcraft.object.common.upgrade.UpgradeSeries.RangedAccuracy;
import static com.evilbird.warcraft.object.common.upgrade.UpgradeSeries.RangedDamage;
import static com.evilbird.warcraft.object.common.upgrade.UpgradeSeries.RangedSight;
import static com.evilbird.warcraft.object.common.upgrade.UpgradeSeries.RangedType;
import static com.evilbird.warcraft.object.common.upgrade.UpgradeSeries.RangedWeapon;
import static com.evilbird.warcraft.object.common.upgrade.UpgradeSeries.SiegeDamage;
import static com.evilbird.warcraft.object.common.upgrade.UpgradeSeries.WoodProduction;

/**
 * Defines upgrades that can be applied to game entities to improve their
 * attributes. Upgrades usually represent a single rank in a series of
 * upgrades, where each rank provides a further improvement to a unit
 * attribute.
 *
 * @author Blair Butterworth
 */
public enum Upgrade implements Identifier
{
    None,

    MeleeDamage1(MeleeDamage, Improved),
    MeleeDamage2(MeleeDamage, Advanced),

    MeleeDefence1(MeleeDefence, Improved),
    MeleeDefence2(MeleeDefence, Advanced),

    MeleeType1(MeleeType, Advanced),

    RangedDamage1(RangedDamage, Improved),
    RangedDamage2(RangedDamage, Advanced),

    RangedAccuracy1(RangedAccuracy, Improved),
    RangedSight1(RangedSight, Improved),
    RangedType1(RangedType, Improved),
    RangedWeapon1(RangedWeapon, Improved),

    NavalDamage1(NavalDamage, Improved),
    NavalDamage2(NavalDamage, Advanced),

    NavalDefence1(NavalDefence, Improved),
    NavalDefence2(NavalDefence, Advanced),

    SiegeDamage1(SiegeDamage, Improved),
    SiegeDamage2(SiegeDamage, Advanced),

    GoldProduction1(GoldProduction, Improved),
    GoldProduction2(GoldProduction, Advanced),

    OilProduction1(OilProduction, Improved),
    OilProduction2(OilProduction, Advanced),

    WoodProduction1(WoodProduction, Improved),
    WoodProduction2(WoodProduction, Advanced),

    BlizzardUpgrade,
    ExorcismUpgrade,
    FlameShieldUpgrade,
    HealingUpgrade,
    InvisibilityUpgrade,
    PolymorphUpgrade,
    SlowUpgrade;

    private UpgradeRank rank;
    private UpgradeSeries series;

    Upgrade() {
        rank = UpgradeRank.None;
        series = UpgradeSeries.None;
    }

    Upgrade(UpgradeSeries series, UpgradeRank rank) {
        this.series = series;
        this.rank = rank;
    }

    /**
     * Returns the {@link UpgradeSeries} the {@code Upgrade} belongs to, if
     * any.
     *
     * @return  an {@code UpgradeSeries} value. {@link UpgradeSeries#None None}
     *          is used to indicate that the {@code Upgrade} doesn't belong to
     *          an {@code UpgradeSeries}.
     */
    public UpgradeSeries getSeries() {
        return series;
    }

    /**
     * Returns the {@link UpgradeRank} of the {@code Upgrade} in the
     * {@link UpgradeSeries} it belongs to, if any.
     *
     * @return  an {@code UpgradeRank} value. {@link UpgradeRank#None None}
     *          is used to indicate that the {@code Upgrade} doesn't belong to
     *          an {@code UpgradeSeries}.
     */
    public UpgradeRank getRank() {
        return rank;
    }

    public boolean isAttributeUpgrade() {
        return EnumUtils.isBetween(this, MeleeDamage1, WoodProduction2);
    }

    public boolean isFeatureUpgrade() {
        return EnumUtils.isBetween(this, BlizzardUpgrade, SlowUpgrade);
    }
}
