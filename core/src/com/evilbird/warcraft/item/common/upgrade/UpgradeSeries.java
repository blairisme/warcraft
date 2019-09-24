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
import java.util.List;

import static com.evilbird.warcraft.item.common.upgrade.Upgrade.*;

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
    MeleeDamage,
    RangedDamage,
    SeaDamage,
    SiegeDamage,
    MeleeDefence,
    SeaDefence,
    GoldProduction,
    OilProduction,
    WoodProduction;

    /**
     * Returns the {@link Collection} of {@link Upgrade Upgrades} in the
     * {@co UpgradeSeries}.
     *
     * @return an unmodifiable {@code Collection} of {@code Upgrade Upgrades}.
     */
    public List<Upgrade> getUpgrades() {
        switch (this) {
            case None: return Collections.emptyList();
            case MeleeDamage: return Arrays.asList(MeleeDamage1, MeleeDamage2);
            case RangedDamage: return Arrays.asList(RangedDamage1, RangedDamage2);
            case SeaDamage: return Arrays.asList(SeaDamage1, SeaDamage2);
            case SiegeDamage: return Arrays.asList(SiegeDamage1, SiegeDamage2);
            case MeleeDefence: return Arrays.asList(MeleeDefence1, MeleeDefence2);
            case SeaDefence: return Arrays.asList(SeaDefence1, SeaDefence2);
            case GoldProduction : return Arrays.asList(GoldProduction1, GoldProduction2);
            case OilProduction: return Arrays.asList(OilProduction1, OilProduction2);
            case WoodProduction: return Arrays.asList(WoodProduction1, WoodProduction2);
            default: throw new UnsupportedOperationException();
        }
    }
}
