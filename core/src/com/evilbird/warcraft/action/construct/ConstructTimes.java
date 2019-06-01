/*
 * Copyright (c) 2019, Blair Butterworth
 *
 * This work is licensed under the MIT License. To view a copy of this
 * license, visit
 *
 *        https://opensource.org/licenses/MIT
 */

package com.evilbird.warcraft.action.construct;

import com.evilbird.warcraft.item.unit.UnitType;

/**
 * Defines the time required to construct different types of buildings.
 *
 * @author Blair Butterworth
 */
public class ConstructTimes
{
    private ConstructTimes() {
    }

    public static float buildTime(UnitType unitType) {
        switch (unitType) {
            case Farm: return 30;
            case Barracks:
            case TownHall: return 90;
            default: throw new UnsupportedOperationException();
        }
    }
}
