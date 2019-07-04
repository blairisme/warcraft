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
import static com.evilbird.warcraft.item.unit.UnitType.OilPlatform;

/**
 * Instances of this class create Human Oil Platform, a building that allows
 * oil to be gathered by oil tankers.
 *
 * @author Blair Butterworth
 */
public class OilPlatformFactory implements AssetProvider<Item>
{
    private static final GridPoint2 ICON = new GridPoint2(184, 380);

    private BuildingAssets assets;
    private BuildingBuilder builder;

    @Inject
    public OilPlatformFactory(Device device) {
        this(device.getAssetStorage());
    }

    public OilPlatformFactory(AssetManager manager) {
        this.assets = new BuildingAssets(manager, OilPlatform, ICON);
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
        result.setHealth(650);
        result.setHealthMaximum(650);
        result.setIdentifier(objectIdentifier("OilPlatform", result));
        result.setName("Oil Platform");
        result.setSight(tiles(3));
        result.setType(OilPlatform);
        return result;
    }
}
