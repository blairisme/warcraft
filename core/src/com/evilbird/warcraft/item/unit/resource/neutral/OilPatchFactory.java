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
import com.evilbird.engine.game.GameFactory;
import com.evilbird.warcraft.item.common.resource.ResourceType;
import com.evilbird.warcraft.item.unit.UnitType;
import com.evilbird.warcraft.item.unit.resource.Resource;
import com.evilbird.warcraft.item.unit.resource.ResourceAssets;
import com.evilbird.warcraft.item.unit.resource.ResourceBuilder;

import javax.inject.Inject;

import static com.evilbird.engine.common.lang.TextIdentifier.objectIdentifier;
import static com.evilbird.warcraft.item.unit.UnitType.OilPatch;

/**
 * Instances of this factory create Oil Patches, a {@link Resource}
 * specialization from which oil can be obtained.
 *
 * @author Blair Butterworth
 */
public class OilPatchFactory implements GameFactory<Resource>
{
    private ResourceAssets assets;
    private ResourceBuilder builder;

    @Inject
    public OilPatchFactory(Device device) {
        this(device.getAssetStorage());
    }

    public OilPatchFactory(AssetManager manager) {
        this.assets = new ResourceAssets(manager, OilPatch);
        this.builder = new ResourceBuilder(assets);
    }

    @Override
    public void load(Identifier context) {
        assets.load();
    }

    @Override
    public void unload(Identifier context) {
    }

    @Override
    public Resource get(Identifier type) {
        Resource result = builder.build();
        result.setHealth(2400);
        result.setHealthMaximum(2400);
        result.setName("Oil Patch");
        result.setType(UnitType.OilPatch);
        result.setResource(ResourceType.Oil, 2400);
        result.setIdentifier(objectIdentifier("OilPatch", result));
        return result;
    }
}
