/*
 * Copyright (c) 2019, Blair Butterworth
 *
 * This work is licensed under the MIT License. To view a copy of this
 * license, visit
 *
 *        https://opensource.org/licenses/MIT
 */

package com.evilbird.warcraft.object.unit.combatant.orc;

import com.badlogic.gdx.assets.AssetManager;
import com.evilbird.engine.common.lang.Identifier;
import com.evilbird.engine.device.Device;
import com.evilbird.warcraft.object.unit.combatant.Combatant;

import javax.inject.Inject;

import static com.evilbird.engine.common.lang.TextIdentifier.objectIdentifier;
import static com.evilbird.warcraft.object.unit.UnitType.Chogall;

/**
 * Instances of this factory create the Orcish Ogre hero Chogall.
 *
 * @author Blair Butterworth
 */
public class ChogallFactory extends OgreFactory
{
    @Inject
    public ChogallFactory(Device device) {
        this(device.getAssetStorage());
    }

    public ChogallFactory(AssetManager manager) {
        super(manager);
    }

    @Override
    public Combatant get(Identifier type) {
        Combatant result = super.get(type);
        result.setIdentifier(objectIdentifier("Chogall", result));
        result.setType(Chogall);
        return result;
    }
}