/*
 * Copyright (c) 2019, Blair Butterworth
 *
 * This work is licensed under the MIT License. To view a copy of this
 * license, visit
 *
 *        https://opensource.org/licenses/MIT
 */

package com.evilbird.warcraft.action.produce;

import com.evilbird.warcraft.item.data.player.PlayerUpgrade;
import com.evilbird.warcraft.item.unit.UnitType;

/**
 * Defines the time required to produce units and upgrades.
 *
 * @author Blair Butterworth
 */
public class ProductionTimes
{
    private ProductionTimes() {
    }

    public static float productionTime(UnitType unitType) {
        switch (unitType) {
            case Peasant:
            case Footman: return 20;
            default: throw new UnsupportedOperationException("Unknown production type");
        }
    }

    public static float productionTime(PlayerUpgrade upgrade) {
        switch (upgrade) {
            case ArrowDamage: return 20;
            default: throw new UnsupportedOperationException("Unknown upgrade type");
        }
    }
}
