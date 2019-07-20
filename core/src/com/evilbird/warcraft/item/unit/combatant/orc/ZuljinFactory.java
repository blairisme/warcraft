/*
 * Copyright (c) 2019, Blair Butterworth
 *
 * This work is licensed under the MIT License. To view a copy of this
 * license, visit
 *
 *        https://opensource.org/licenses/MIT
 */

package com.evilbird.warcraft.item.unit.combatant.orc;

import com.badlogic.gdx.assets.AssetManager;
import com.evilbird.engine.common.lang.Identifier;
import com.evilbird.engine.device.Device;
import com.evilbird.warcraft.item.unit.combatant.Combatant;

import javax.inject.Inject;

import static com.evilbird.engine.common.lang.TextIdentifier.objectIdentifier;
import static com.evilbird.warcraft.item.unit.UnitType.Zuljin;

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
    public Combatant get(Identifier type) {
        Combatant result = super.get(type);
        result.setType(Zuljin);
        result.setIdentifier(objectIdentifier("Zuljin", result));
        result.setName("Zuljin");
        result.setType(Zuljin);
        return result;
    }
}



