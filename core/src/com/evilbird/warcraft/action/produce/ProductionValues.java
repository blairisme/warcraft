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
 * Defines the units and upgrades that result from {@link ProduceActions}.
 *
 * @author Blair Butterworth
 */
public class ProductionValues
{
    private ProductionValues() {
    }

    public static UnitType getProduct(ProduceActions action) {
        switch (action) {
            case TrainFootman:
            case TrainFootmanCancel: return UnitType.Footman;
            case TrainPeasant:
            case TrainPeasantCancel: return UnitType.Peasant;
            default: throw new UnsupportedOperationException("Unknown production action");
        }
    }

    public static PlayerUpgrade getUpgrade(ProduceActions action) {
        switch (action) {
            case UpgradeArrowDamage:
            case UpgradeArrowDamageCancel: return PlayerUpgrade.ArrowDamage;
            default: throw new UnsupportedOperationException("Unknown production action");
        }
    }

    public static int getUpgradeValue(ProduceActions action) {
        switch (action) {
            case UpgradeArrowDamage:
            case UpgradeArrowDamageCancel: return 1;
            default: throw new UnsupportedOperationException("Unknown production action");
        }
    }
}