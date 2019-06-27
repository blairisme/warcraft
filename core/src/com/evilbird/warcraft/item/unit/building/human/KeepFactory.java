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
import static com.evilbird.warcraft.item.unit.UnitType.Keep;

/**
 * Instances of this class create Human Keeps, the central building of the
 * human faction and one that creates gathering units: peasants.
 *
 * @author Blair Butterworth
 */
public class KeepFactory implements AssetProvider<Item>
{
    private static final GridPoint2 ICON = new GridPoint2(46, 494);
    private static final GridPoint2 SIZE = new GridPoint2(128, 128);

    private BuildingAssets assets;
    private BuildingBuilder builder;

    @Inject
    public KeepFactory(Device device) {
        this(device.getAssetStorage());
    }

    public KeepFactory(AssetManager manager) {
        this.assets = new BuildingAssets(manager, Keep, ICON, SIZE);
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
        result.setHealth(1400);
        result.setHealthMaximum(1400);
        result.setIdentifier(objectIdentifier("Keep", result));
        result.setName("Keep");
        result.setSight(tiles(6));
        result.setType(Keep);
        result.setResource(Food, 1);
        return result;
    }
}
