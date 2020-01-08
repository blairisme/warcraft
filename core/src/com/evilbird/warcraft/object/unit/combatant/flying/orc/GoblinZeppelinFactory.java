/*
 * Copyright (c) 2020, Blair Butterworth
 *
 * This Source Code Form is subject to the terms of the Mozilla Public
 * License, v. 2.0. If a copy of the MPL was not distributed with this
 * file, You can obtain one at http://mozilla.org/MPL/2.0/.
 */

package com.evilbird.warcraft.object.unit.combatant.flying.orc;

import com.badlogic.gdx.assets.AssetManager;
import com.evilbird.engine.common.lang.Identifier;
import com.evilbird.engine.device.Device;
import com.evilbird.warcraft.object.unit.combatant.flying.FlyingUnit;
import com.evilbird.warcraft.object.unit.combatant.flying.FlyingUnitFactory;

import javax.inject.Inject;

import static com.evilbird.engine.common.lang.TextIdentifier.objectIdentifier;
import static com.evilbird.warcraft.object.common.capability.TerrainType.Air;
import static com.evilbird.warcraft.object.common.query.GameObjectUtils.tiles;
import static com.evilbird.warcraft.object.unit.UnitType.GoblinZeppelin;

/**
 * Instances of this factory create Goblin Zeppelins, Orcish flying scouts.
 *
 * @author Blair Butterworth
 */
public class GoblinZeppelinFactory extends FlyingUnitFactory
{
    @Inject
    public GoblinZeppelinFactory(Device device) {
        this(device.getAssetStorage());
    }

    public GoblinZeppelinFactory(AssetManager manager) {
        super(manager, GoblinZeppelin);
    }

    @Override
    public FlyingUnit get(Identifier type) {
        FlyingUnit result = builder.build();
        result.setHealth(40);
        result.setHealthMaximum(40);
        result.setIdentifier(objectIdentifier("GoblinZeppelin", result));
        result.setMovementSpeed(8 * 10);
        result.setMovementCapability(Air);
        result.setSight(tiles(5));
        result.setType(GoblinZeppelin);
        return result;
    }
}