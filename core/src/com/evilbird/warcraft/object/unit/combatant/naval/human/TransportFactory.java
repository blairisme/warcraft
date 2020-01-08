/*
 * Copyright (c) 2020, Blair Butterworth
 *
 * This Source Code Form is subject to the terms of the Mozilla Public
 * License, v. 2.0. If a copy of the MPL was not distributed with this
 * file, You can obtain one at http://mozilla.org/MPL/2.0/.
 */

package com.evilbird.warcraft.object.unit.combatant.naval.human;

import com.badlogic.gdx.assets.AssetManager;
import com.evilbird.engine.common.lang.Identifier;
import com.evilbird.engine.device.Device;
import com.evilbird.warcraft.object.unit.combatant.RangedCombatant;
import com.evilbird.warcraft.object.unit.combatant.naval.NavalUnitFactory;

import javax.inject.Inject;

import static com.evilbird.engine.common.lang.TextIdentifier.objectIdentifier;
import static com.evilbird.warcraft.object.common.capability.TerrainType.ShallowWater;
import static com.evilbird.warcraft.object.common.query.GameObjectUtils.tiles;
import static com.evilbird.warcraft.object.unit.UnitType.Transport;

/**
 * Instances of this factory create Elven Destroyers, Human seaborne transport
 * units.
 *
 * @author Blair Butterworth
 */
public class TransportFactory extends NavalUnitFactory
{
    @Inject
    public TransportFactory(Device device) {
        this(device.getAssetStorage());
    }

    public TransportFactory(AssetManager manager) {
        super(manager, Transport);
    }

    @Override
    public RangedCombatant get(Identifier type) {
        RangedCombatant result = builder.build();
        result.setAttackSpeed(0);
        result.setAttackRange(tiles(1));
        result.setArmour(0);
        result.setPiercingDamage(0);
        result.setBasicDamage(0);
        result.setHealth(150);
        result.setHealthMaximum(150);
        result.setIdentifier(objectIdentifier("Transport", result));
        result.setMovementSpeed(8 * 10);
        result.setMovementCapability(ShallowWater);
        result.setSight(tiles(4));
        result.setType(Transport);
        return result;
    }
}