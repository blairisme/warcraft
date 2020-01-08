/*
 * Copyright (c) 2020, Blair Butterworth
 *
 * This Source Code Form is subject to the terms of the Mozilla Public
 * License, v. 2.0. If a copy of the MPL was not distributed with this
 * file, You can obtain one at http://mozilla.org/MPL/2.0/.
 */

package com.evilbird.warcraft.object.unit.combatant.melee.orc;

import com.badlogic.gdx.assets.AssetManager;
import com.evilbird.engine.common.lang.Identifier;
import com.evilbird.engine.device.Device;
import com.evilbird.warcraft.object.unit.combatant.Combatant;
import com.evilbird.warcraft.object.unit.combatant.melee.MeleeUnitFactory;

import javax.inject.Inject;

import static com.evilbird.engine.common.lang.TextIdentifier.objectIdentifier;
import static com.evilbird.warcraft.object.common.capability.TerrainType.Land;
import static com.evilbird.warcraft.object.common.query.GameObjectUtils.tiles;
import static com.evilbird.warcraft.object.unit.UnitType.GoblinSappers;

/**
 * Instances of this factory create Goblin Demolition units, Orcish single use
 * siege {@link Combatant Combatants}.
 *
 * @author Blair Butterworth
 */
public class GoblinSappersFactory extends MeleeUnitFactory
{
    @Inject
    public GoblinSappersFactory(Device device) {
        this(device.getAssetStorage());
    }

    public GoblinSappersFactory(AssetManager manager) {
        super(manager, GoblinSappers);
    }

    @Override
    public Combatant get(Identifier type) {
        Combatant result = builder.build();
        result.setAttackSpeed(0f);
        result.setArmour(2);
        result.setBasicDamage(50);
        result.setPiercingDamage(50);
        result.setHealth(60);
        result.setHealthMaximum(60);
        result.setIdentifier(objectIdentifier("GoblinSappers", result));
        result.setMovementSpeed(8 * 10);
        result.setMovementCapability(Land);
        result.setSight(tiles(4));
        result.setType(GoblinSappers);
        return result;
    }
}