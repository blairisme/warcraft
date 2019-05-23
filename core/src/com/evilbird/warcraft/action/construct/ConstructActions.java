/*
 * Copyright (c) 2019, Blair Butterworth
 *
 * This work is licensed under the MIT License. To view a copy of this
 * license, visit
 *
 *        https://opensource.org/licenses/MIT
 */

package com.evilbird.warcraft.action.construct;

import com.evilbird.engine.action.ActionIdentifier;
import com.evilbird.warcraft.item.unit.UnitType;

import static com.evilbird.warcraft.item.unit.UnitType.*;

/**
 * Defines options of specifying construction action varieties.
 *
 * @author Blair Butterworth
 */
public enum ConstructActions implements ActionIdentifier
{
    ConstructBarracks,
    ConstructFarm,
    ConstructTownHall,
    ConstructBarracksCancel,
    ConstructFarmCancel,
    ConstructTownHallCancel;

    public UnitType getUnitType() {
        switch (this) {
            case ConstructBarracks:
            case ConstructBarracksCancel: return Barracks;
            case ConstructFarm:
            case ConstructFarmCancel: return Farm;
            case ConstructTownHall:
            case ConstructTownHallCancel: return TownHall;
            default: throw new UnsupportedOperationException();
        }
    }
}
