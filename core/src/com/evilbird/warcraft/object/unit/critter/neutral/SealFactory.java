/*
 * Copyright (c) 2020, Blair Butterworth
 *
 * This Source Code Form is subject to the terms of the Mozilla Public
 * License, v. 2.0. If a copy of the MPL was not distributed with this
 * file, You can obtain one at http://mozilla.org/MPL/2.0/.
 */

package com.evilbird.warcraft.object.unit.critter.neutral;

import com.badlogic.gdx.assets.AssetManager;
import com.evilbird.engine.common.lang.Identifier;
import com.evilbird.engine.device.Device;
import com.evilbird.warcraft.object.unit.critter.Critter;
import com.evilbird.warcraft.object.unit.critter.CritterFactoryBase;

import javax.inject.Inject;

import static com.evilbird.engine.common.lang.TextIdentifier.objectIdentifier;
import static com.evilbird.warcraft.object.common.capability.TerrainType.Land;
import static com.evilbird.warcraft.object.common.query.GameObjectUtils.tiles;
import static com.evilbird.warcraft.object.unit.UnitType.Seal;

/**
 * Represents a seal, an animal that roams around winter game worlds.
 *
 * @author Blair Butterworth
 */
public class SealFactory extends CritterFactoryBase
{
    @Inject
    public SealFactory(Device device) {
        this(device.getAssetStorage());
    }

    public SealFactory(AssetManager manager) {
        super(manager, Seal);
    }

    @Override
    public Critter get(Identifier type) {
        Critter result = builder.build();
        result.setArmour(0);
        result.setHealth(5);
        result.setHealthMaximum(5);
        result.setIdentifier(objectIdentifier("Seal", result));
        result.setMovementSpeed(7 * 10);
        result.setMovementCapability(Land);
        result.setSight(tiles(2));
        result.setType(Seal);
        return result;
    }
}