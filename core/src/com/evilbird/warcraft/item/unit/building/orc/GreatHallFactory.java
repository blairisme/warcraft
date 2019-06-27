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
import static com.evilbird.warcraft.item.common.resource.ResourceType.Food;
import static com.evilbird.warcraft.item.unit.UnitType.GreatHall;

/**
 * Instances of this class create Great Halls, an Orcish {@link Building} that
 * creates peons.
 *
 * @author Blair Butterworth
 */
public class GreatHallFactory implements AssetProvider<Item>
{
    private static final GridPoint2 ICON = new GridPoint2(46, 304);
    private static final GridPoint2 SIZE = new GridPoint2(128, 128);

    private BuildingAssets assets;
    private BuildingBuilder builder;

    @Inject
    public GreatHallFactory(Device device) {
        this(device.getAssetStorage());
    }

    public GreatHallFactory(AssetManager manager) {
        this.assets = new BuildingAssets(manager, GreatHall, ICON, SIZE);
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
        result.setHealth(1200);
        result.setHealthMaximum(1200);
        result.setIdentifier(objectIdentifier("GreatHall", result));
        result.setName("Great Hall");
        result.setSight(tiles(4));
        result.setType(GreatHall);
        result.setResource(Food, 1);
        return result;
    }
}