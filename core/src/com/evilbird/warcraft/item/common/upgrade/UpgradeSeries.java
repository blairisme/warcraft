/*
 * Copyright (c) 2019, Blair Butterworth
 *
 * This work is licensed under the MIT License. To view a copy of this
 * license, visit
 *
 *        https://opensource.org/licenses/MIT
 */

package com.evilbird.warcraft.item.common.upgrade;

import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;

import static com.evilbird.warcraft.item.common.upgrade.Upgrade.GoldProduction1;
import static com.evilbird.warcraft.item.common.upgrade.Upgrade.GoldProduction2;
import static com.evilbird.warcraft.item.common.upgrade.Upgrade.MeleeDamage1;
import static com.evilbird.warcraft.item.common.upgrade.Upgrade.MeleeDamage2;
import static com.evilbird.warcraft.item.common.upgrade.Upgrade.MeleeDefence1;
import static com.evilbird.warcraft.item.common.upgrade.Upgrade.MeleeDefence2;
import static com.evilbird.warcraft.item.common.upgrade.Upgrade.OilProduction1;
import static com.evilbird.warcraft.item.common.upgrade.Upgrade.OilProduction2;
import static com.evilbird.warcraft.item.common.upgrade.Upgrade.RangedDamage1;
import static com.evilbird.warcraft.item.common.upgrade.Upgrade.RangedDamage2;
import static com.evilbird.warcraft.item.common.upgrade.Upgrade.SeaDamage1;
import static com.evilbird.warcraft.item.common.upgrade.Upgrade.SeaDamage2;
import static com.evilbird.warcraft.item.common.upgrade.Upgrade.SeaDefence1;
import static com.evilbird.warcraft.item.common.upgrade.Upgrade.SeaDefence2;
import static com.evilbird.warcraft.item.common.upgrade.Upgrade.SiegeDamage1;
import static com.evilbird.warcraft.item.common.upgrade.Upgrade.SiegeDamage2;
import static com.evilbird.warcraft.item.common.upgrade.Upgrade.WoodProduction1;
import static com.evilbird.warcraft.item.common.upgrade.Upgrade.WoodProduction2;

/**
 * Defines a grouping of {@link Upgrade upgrades}, where each
 * {@link UpgradeRank rank} builds upon the previous to improve a game entities
 * attributes.
 *
 * @author Blair Butterworth
 */
public enum UpgradeSeries
{
    None,

    MeleeDamage (MeleeDamage1, MeleeDamage2),
    RangedDamage (RangedDamage1, RangedDamage2),
    SeaDamage (SeaDamage1, SeaDamage2),
    SiegeDamage (SiegeDamage1, SiegeDamage2),

    MeleeDefence (MeleeDefence1, MeleeDefence2),
    SeaDefence (SeaDefence1, SeaDefence2),

    GoldProduction (GoldProduction1, GoldProduction2),
    OilProduction (OilProduction1, OilProduction2),
    WoodProduction (WoodProduction1, WoodProduction2);

    private Collection<Upgrade> upgrades;

    UpgradeSeries(Upgrade ... upgrades) {
        this.upgrades = Collections.unmodifiableCollection(Arrays.asList(upgrades));
    }

    /**
     * Returns the {@link Collection} of {@link Upgrade Upgrades} in the
     * {@oce UpgradeSeries}.
     *
     * @return an unmodifiable {@code Collection} of {@code Upgrade Upgrades}.
     */
    public Collection<Upgrade> getUpgrades() {
        return upgrades;
    }
}
