/*
 * Copyright (c) 2020, Blair Butterworth
 *
 * This Source Code Form is subject to the terms of the Mozilla Public
 * License, v. 2.0. If a copy of the MPL was not distributed with this
 * file, You can obtain one at http://mozilla.org/MPL/2.0/.
 */

package com.evilbird.warcraft.object.unit.combatant.spellcaster.orc;

import com.badlogic.gdx.assets.AssetManager;
import com.evilbird.engine.common.lang.Identifier;
import com.evilbird.engine.device.Device;
import com.evilbird.warcraft.object.unit.combatant.spellcaster.SpellCaster;

import javax.inject.Inject;

import static com.evilbird.engine.common.lang.TextIdentifier.objectIdentifier;
import static com.evilbird.warcraft.object.unit.UnitType.Chogall;

/**
 * Instances of this factory create the Orcish Ogre hero Chogall.
 *
 * @author Blair Butterworth
 */
public class ChogallFactory extends OgreMageFactory
{
    @Inject
    public ChogallFactory(Device device) {
        this(device.getAssetStorage());
    }

    public ChogallFactory(AssetManager manager) {
        super(manager);
    }

    @Override
    public SpellCaster get(Identifier type) {
        SpellCaster result = super.get(type);
        result.setIdentifier(objectIdentifier("Chogall", result));
        result.setType(Chogall);
        return result;
    }
}