/*
 * Copyright (c) 2020, Blair Butterworth
 *
 * This Source Code Form is subject to the terms of the Mozilla Public
 * License, v. 2.0. If a copy of the MPL was not distributed with this
 * file, You can obtain one at http://mozilla.org/MPL/2.0/.
 */

package com.evilbird.warcraft.object.unit.combatant.ranged.orc;

import com.badlogic.gdx.assets.AssetManager;
import com.evilbird.engine.common.lang.Identifier;
import com.evilbird.engine.device.Device;
import com.evilbird.warcraft.object.unit.combatant.RangedCombatant;

import javax.inject.Inject;

import static com.evilbird.engine.common.lang.TextIdentifier.objectIdentifier;
import static com.evilbird.warcraft.object.unit.UnitType.Zuljin;

/**
 * Instances of this factory create the Troll Axethrower hero Zuljin.
 *
 * @author Blair Butterworth
 */
public class ZuljinFactory extends TrollAxethrowerFactory
{
    @Inject
    public ZuljinFactory(Device device) {
        this(device.getAssetStorage());
    }

    public ZuljinFactory(AssetManager manager) {
        super(manager);
    }

    @Override
    public RangedCombatant get(Identifier type) {
        RangedCombatant result = super.get(type);
        result.setIdentifier(objectIdentifier("Zuljin", result));
        result.setType(Zuljin);
        return result;
    }
}



