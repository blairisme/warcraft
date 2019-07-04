/*
 * Copyright (c) 2019, Blair Butterworth
 *
 * This work is licensed under the MIT License. To view a copy of this
 * license, visit
 *
 *        https://opensource.org/licenses/MIT
 */

package com.evilbird.warcraft.item.unit.building.human;

import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.math.GridPoint2;
import com.evilbird.engine.common.inject.AssetProvider;
import com.evilbird.engine.device.Device;
import com.evilbird.engine.item.Item;
import com.evilbird.warcraft.item.unit.building.Building;
import com.evilbird.warcraft.item.unit.building.BuildingAssets;
import com.evilbird.warcraft.item.unit.building.BuildingBuilder;

import javax.inject.Inject;

import static com.evilbird.engine.common.lang.TextIdentifier.objectIdentifier;
import static com.evilbird.warcraft.item.WarcraftItemConstants.tiles;
import static com.evilbird.warcraft.item.common.resource.ResourceType.Food;
import static com.evilbird.warcraft.item.unit.UnitType.Farm;

/**
 * Instances of this class create {@link Building Farms}, loading the
 * necessary assets and defining the appropriate attributes.
 *
 * @author Blair Butterworth
 */
public class FarmFactory implements AssetProvider<Item>
{
    private static final GridPoint2 ICON = new GridPoint2(138, 266);

    private BuildingAssets assets;
    private BuildingBuilder builder;

    @Inject
    public FarmFactory(Device device) {
        this(device.getAssetStorage());
    }

    public FarmFactory(AssetManager manager) {
        this.assets = new BuildingAssets(manager, Farm, ICON);
        this.builder = new BuildingBuilder(assets);
    }

    @Override
    public void load() {
        assets.load();
    }

    @Override
    public Item get() {
        Building result = builder.build();
        result.setDefence(20);
        result.setHealth(400);
        result.setHealthMaximum(400);
        result.setIdentifier(objectIdentifier("Farm", result));
        result.setName("Farm");
        result.setSight(tiles(3));
        result.setType(Farm);
        result.setResource(Food, 4);
        return result;
    }
}
