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
import com.badlogic.gdx.math.GridPoint2;
import com.evilbird.engine.common.inject.AssetProvider;
import com.evilbird.engine.device.Device;
import com.evilbird.engine.item.Item;
import com.evilbird.warcraft.item.common.resource.ResourceType;
import com.evilbird.warcraft.item.unit.UnitType;
import com.evilbird.warcraft.item.unit.resource.Resource;
import com.evilbird.warcraft.item.unit.resource.ResourceAssets;
import com.evilbird.warcraft.item.unit.resource.ResourceBuilder;

import javax.inject.Inject;

import static com.evilbird.engine.common.lang.TextIdentifier.objectIdentifier;
import static com.evilbird.warcraft.item.unit.UnitType.GoldMine;

/**
 * Instances of this factory create Gold Mines, a {@link Resource}
 * specialization from which gold can be obtained.
 *
 * @author Blair Butterworth
 */
public class GoldMineFactory implements AssetProvider<Item>
{
    private static final GridPoint2 ICON = new GridPoint2(184, 532);

    private ResourceAssets assets;
    private ResourceBuilder builder;

    @Inject
    public GoldMineFactory(Device device) {
        this(device.getAssetStorage());
    }

    public GoldMineFactory(AssetManager manager) {
        this.assets = new ResourceAssets(manager, GoldMine, ICON);
        this.builder = new ResourceBuilder(assets);
    }

    @Override
    public void load() {
        assets.load();
    }

    @Override
    public Item get() {
        Resource result = builder.build();
        result.setHealth(2400);
        result.setHealthMaximum(2400);
        result.setName("Gold Mine");
        result.setType(UnitType.GoldMine);
        result.setResource(ResourceType.Gold, 2400);
        result.setIdentifier(objectIdentifier("GoldMine", result));
        return result;
    }
}
