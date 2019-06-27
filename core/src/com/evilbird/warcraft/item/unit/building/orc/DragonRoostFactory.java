/*
 * Copyright (c) 2019, Blair Butterworth
 *
 * This work is licensed under the MIT License. To view a copy of this
 * license, visit
 *
 *        https://opensource.org/licenses/MIT
 */

package com.evilbird.warcraft.item.unit.building.orc;

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
import static com.evilbird.warcraft.item.unit.UnitType.DragonRoost;

/**
 * Instances of this class create Dragon Roosts, allowing for the creation
 * of Dragons.
 *
 * @author Blair Butterworth
 */
public class DragonRoostFactory implements AssetProvider<Item>
{
    private static final GridPoint2 ICON = new GridPoint2(138, 532);
    private static final GridPoint2 SIZE = new GridPoint2(96, 96);

    private BuildingAssets assets;
    private BuildingBuilder builder;

    @Inject
    public DragonRoostFactory(Device device) {
        this(device.getAssetStorage());
    }

    public DragonRoostFactory(AssetManager manager) {
        this.assets = new BuildingAssets(manager, DragonRoost, ICON, SIZE);
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
        result.setHealth(500);
        result.setHealthMaximum(500);
        result.setIdentifier(objectIdentifier("DragonRoost", result));
        result.setName("Dragon Roost");
        result.setSight(tiles(3));
        result.setType(DragonRoost);
        return result;
    }
}
