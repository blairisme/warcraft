/*
 * Copyright (c) 2019, Blair Butterworth
 *
 * This work is licensed under the MIT License. To view a copy of this
 * license, visit
 *
 *        https://opensource.org/licenses/MIT
 */

package com.evilbird.warcraft.object.unit.combatant.melee.human;

import com.badlogic.gdx.assets.AssetManager;
import com.evilbird.engine.common.lang.Identifier;
import com.evilbird.engine.device.Device;
import com.evilbird.warcraft.object.unit.combatant.Combatant;

import javax.inject.Inject;

import static com.evilbird.engine.common.lang.TextIdentifier.objectIdentifier;
import static com.evilbird.warcraft.object.unit.UnitType.AnduinLothar;

/**
 * Instances of this factory create the Human hero Anduin Lothar.
 *
 * @author Blair Butterworth
 */
public class AnduinLotharFactory extends KnightFactory
{
    @Inject
    public AnduinLotharFactory(Device device) {
        this(device.getAssetStorage());
    }

    public AnduinLotharFactory(AssetManager manager) {
        super(manager);
    }

    @Override
    public Combatant get(Identifier type) {
        Combatant result = super.get(type);
        result.setIdentifier(objectIdentifier("AnduinLothar", result));
        result.setType(AnduinLothar);
        return result;
    }
}



