/*
 * Copyright (c) 2020, Blair Butterworth
 *
 * This Source Code Form is subject to the terms of the Mozilla Public
 * License, v. 2.0. If a copy of the MPL was not distributed with this
 * file, You can obtain one at http://mozilla.org/MPL/2.0/.
 */

package com.evilbird.warcraft.object.unit.combatant.flying.neutral;

import com.badlogic.gdx.assets.AssetManager;
import com.evilbird.engine.common.lang.Identifier;
import com.evilbird.engine.device.Device;
import com.evilbird.warcraft.object.unit.combatant.flying.FlyingUnit;
import com.evilbird.warcraft.object.unit.combatant.flying.FlyingUnitFactory;

import javax.inject.Inject;

import static com.evilbird.engine.common.lang.TextIdentifier.objectIdentifier;
import static com.evilbird.warcraft.object.common.capability.TerrainType.Air;
import static com.evilbird.warcraft.object.common.query.GameObjectUtils.tiles;
import static com.evilbird.warcraft.object.unit.UnitType.EyeOfKilrogg;

/**
 * Instances of this factory create eyes of Kilrogg, neutral creatures
 * that are conjured by magic users.
 *
 * @author Blair Butterworth
 */
public class EyeOfKilroggFactory extends FlyingUnitFactory
{
    @Inject
    public EyeOfKilroggFactory(Device device) {
        this(device.getAssetStorage());
    }

    public EyeOfKilroggFactory(AssetManager manager) {
        super(manager, EyeOfKilrogg);
    }

    @Override
    public FlyingUnit get(Identifier type) {
        FlyingUnit result = builder.build();
        result.setHealth(100);
        result.setHealthMaximum(100);
        result.setIdentifier(objectIdentifier("EyeOfKilrogg", result));
        result.setMovementSpeed(8 * 14);
        result.setMovementCapability(Air);
        result.setSight(tiles(6));
        result.setType(EyeOfKilrogg);
        return result;
    }
}