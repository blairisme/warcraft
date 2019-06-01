/*
 * Copyright (c) 2019, Blair Butterworth
 *
 * This work is licensed under the MIT License. To view a copy of this
 * license, visit
 *
 *        https://opensource.org/licenses/MIT
 */

package com.evilbird.warcraft.action.train;

import com.evilbird.engine.action.ActionIdentifier;
import com.evilbird.warcraft.item.unit.UnitType;

import static com.evilbird.warcraft.item.unit.UnitType.Footman;
import static com.evilbird.warcraft.item.unit.UnitType.Peasant;

/**
 * Defines options of specifying training action varieties.
 *
 * @author Blair Butterworth
 */
public enum TrainActions implements ActionIdentifier
{
    TrainFootman,
    TrainPeasant,
    TrainFootmanCancel,
    TrainPeasantCancel;

    public UnitType getUnitType() {
        switch (this) {
            case TrainFootman:
            case TrainFootmanCancel: return Footman;
            case TrainPeasant:
            case TrainPeasantCancel: return Peasant;
            default: throw new UnsupportedOperationException();
        }
    }
}
