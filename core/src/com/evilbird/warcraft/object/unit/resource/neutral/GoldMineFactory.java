/*
 * Copyright (c) 2020, Blair Butterworth
 *
 * This Source Code Form is subject to the terms of the Mozilla Public
 * License, v. 2.0. If a copy of the MPL was not distributed with this
 * file, You can obtain one at http://mozilla.org/MPL/2.0/.
 */

package com.evilbird.warcraft.object.unit.resource.neutral;

import com.badlogic.gdx.assets.AssetManager;
import com.evilbird.engine.common.lang.Identifier;
import com.evilbird.engine.device.Device;
import com.evilbird.warcraft.data.resource.ResourceType;
import com.evilbird.warcraft.object.unit.UnitType;
import com.evilbird.warcraft.object.unit.resource.Resource;
import com.evilbird.warcraft.object.unit.resource.ResourceFactoryBase;

import javax.inject.Inject;

import static com.evilbird.engine.common.lang.TextIdentifier.objectIdentifier;
import static com.evilbird.warcraft.object.unit.UnitType.GoldMine;

/**
 * Instances of this factory create Gold Mines, a {@link Resource}
 * specialization from which gold can be obtained.
 *
 * @author Blair Butterworth
 */
public class GoldMineFactory extends ResourceFactoryBase
{
    @Inject
    public GoldMineFactory(Device device) {
        this(device.getAssetStorage());
    }

    public GoldMineFactory(AssetManager manager) {
        super(manager, GoldMine);
    }

    @Override
    public Resource get(Identifier type) {
        Resource result = builder.build();
        result.setHealth(2400);
        result.setHealthMaximum(2400);
        result.setType(UnitType.GoldMine);
        result.setResource(ResourceType.Gold, 2400);
        result.setIdentifier(objectIdentifier("GoldMine", result));
        return result;
    }
}
