/*
 * Copyright (c) 2019, Blair Butterworth
 *
 * This work is licensed under the MIT License. To view a copy of this
 * license, visit
 *
 *        https://opensource.org/licenses/MIT
 */

package com.evilbird.warcraft.item.unit.resource.neutral;

import com.badlogic.gdx.assets.AssetManager;
import com.evilbird.engine.common.lang.Identifier;
import com.evilbird.engine.device.Device;
import com.evilbird.warcraft.item.common.resource.ResourceType;
import com.evilbird.warcraft.item.unit.UnitType;
import com.evilbird.warcraft.item.unit.resource.Resource;
import com.evilbird.warcraft.item.unit.resource.ResourceFactoryBase;

import javax.inject.Inject;

import static com.evilbird.engine.common.lang.TextIdentifier.objectIdentifier;
import static com.evilbird.warcraft.item.unit.UnitType.GoldMine;

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
