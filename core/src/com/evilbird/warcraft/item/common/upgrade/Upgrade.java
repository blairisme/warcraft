/*
 * Copyright (c) 2019, Blair Butterworth
 *
 * This work is licensed under the MIT License. To view a copy of this
 * license, visit
 *
 *        https://opensource.org/licenses/MIT
 */

package com.evilbird.warcraft.item.common.upgrade;

import com.evilbird.engine.common.lang.Identifier;

import static com.evilbird.warcraft.item.common.upgrade.UpgradeRank.Advanced;
import static com.evilbird.warcraft.item.common.upgrade.UpgradeRank.Improved;
import static com.evilbird.warcraft.item.common.upgrade.UpgradeSeries.GoldProduction;
import static com.evilbird.warcraft.item.common.upgrade.UpgradeSeries.MeleeDamage;
import static com.evilbird.warcraft.item.common.upgrade.UpgradeSeries.MeleeDefence;
import static com.evilbird.warcraft.item.common.upgrade.UpgradeSeries.OilProduction;
import static com.evilbird.warcraft.item.common.upgrade.UpgradeSeries.RangedDamage;
import static com.evilbird.warcraft.item.common.upgrade.UpgradeSeries.SeaDamage;
import static com.evilbird.warcraft.item.common.upgrade.UpgradeSeries.SeaDefence;
import static com.evilbird.warcraft.item.common.upgrade.UpgradeSeries.SiegeDamage;
import static com.evilbird.warcraft.item.common.upgrade.UpgradeSeries.WoodProduction;

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
    MeleeDamage1 (MeleeDamage, Improved),
    MeleeDamage2 (MeleeDamage, Advanced),

    RangedDamage1 (RangedDamage, Improved),
    RangedDamage2 (RangedDamage, Advanced),

    SeaDamage1 (SeaDamage, Improved),
    SeaDamage2 (SeaDamage, Advanced),

    SiegeDamage1 (SiegeDamage, Improved),
    SiegeDamage2 (SiegeDamage, Advanced),

    MeleeDefence1 (MeleeDefence, Improved),
    MeleeDefence2 (MeleeDefence, Advanced),

    SeaDefence1 (SeaDefence, Improved),
    SeaDefence2 (SeaDefence, Advanced),

    GoldProduction1 (GoldProduction, Improved),
    GoldProduction2 (GoldProduction, Advanced),

    OilProduction1 (OilProduction, Improved),
    OilProduction2 (OilProduction, Advanced),

    WoodProduction1 (WoodProduction, Improved),
    WoodProduction2 (WoodProduction, Advanced);

//    ArmourPlating1,
//    ArmourPlating2,
//
//    CannonDamage1,
//    CannonDamage2,
//
//    MountedSpeed1,
//    MountedSpeed2,
//
//    RangedAccuracy1,
//    RangedAccuracy2,
//
//    SiegeDamage;

    private UpgradeRank rank;
    private UpgradeSeries series;

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
}
