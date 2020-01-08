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
import com.evilbird.warcraft.object.unit.combatant.spellcaster.SpellCasterFactory;

import javax.inject.Inject;

import static com.evilbird.engine.common.lang.TextIdentifier.objectIdentifier;
import static com.evilbird.warcraft.object.common.capability.TerrainType.Land;
import static com.evilbird.warcraft.object.common.query.GameObjectUtils.tiles;
import static com.evilbird.warcraft.object.unit.UnitType.Ogre;
import static com.evilbird.warcraft.object.unit.UnitType.OgreMage;

/**
 * Instances of this factory create Ogre Mages, an upgraded Orge version.
 *
 * @author Blair Butterworth
 */
public class OgreMageFactory extends SpellCasterFactory
{
    @Inject
    public OgreMageFactory(Device device) {
        this(device.getAssetStorage());
    }

    public OgreMageFactory(AssetManager manager) {
        super(manager, Ogre, OgreMage);
    }

    @Override
    public SpellCaster get(Identifier type) {
        SpellCaster result = builder.build();
        result.setAttackSpeed(1);
        result.setArmour(4);
        result.setPiercingDamage(8);
        result.setBasicDamage(12);
        result.setHealth(90);
        result.setHealthMaximum(90);
        result.setIdentifier(objectIdentifier("OgreMage", result));
        result.setMana(100f);
        result.setManaMaximum(255f);
        result.setManaRegeneration(1f);
        result.setMovementSpeed(8 * 13);
        result.setMovementCapability(Land);
        result.setSight(tiles(5));
        result.setType(OgreMage);
        return result;
    }
}