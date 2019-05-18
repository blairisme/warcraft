/*
 * Copyright (c) 2019, Blair Butterworth
 *
 * This work is licensed under the MIT License. To view a copy of this
 * license, visit
 *
 *        https://opensource.org/licenses/MIT
 */

package com.evilbird.warcraft.action.train;

import com.evilbird.warcraft.item.unit.UnitType;

/**
 * Defines the time required to create different types of units.
 *
 * @author Blair Butterworth
 */
public class TrainTimes
{
    private TrainTimes() {
    }

    public static float trainTime(UnitType unitType) {
        switch (unitType) {
            case Peasant:
            case Footman: return 20;
            default: throw new UnsupportedOperationException();
        }
    }
}
