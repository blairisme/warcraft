/*
 * Copyright (c) 2019, Blair Butterworth
 *
 * This work is licensed under the MIT License. To view a copy of this
 * license, visit
 *
 *        https://opensource.org/licenses/MIT
 */

package com.evilbird.warcraft.item.unit.building.neutral;

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
import static com.evilbird.warcraft.item.WarcraftItemConstants.TILE_WIDTH;
import static com.evilbird.warcraft.item.unit.UnitType.CircleOfPower;

/**
 * Instances of this class create {@link Building Circles of Power}, loading the
 * necessary assets and defining the appropriate attributes.
 *
 * @author Blair Butterworth
 */
public class CircleOfPowerFactory implements AssetProvider<Item>
{
    private static final GridPoint2 ICON = new GridPoint2(46, 608);

    private BuildingAssets assets;
    private BuildingBuilder builder;

    @Inject
    public CircleOfPowerFactory(Device device) {
        this(device.getAssetStorage());
    }

    public CircleOfPowerFactory(AssetManager manager) {
        this.assets = new BuildingAssets(manager, CircleOfPower, ICON);
        this.builder = new BuildingBuilder(assets);
    }

    @Override
    public void load() {
        assets.load();
    }

    @Override
    public Item get() {
        Building result = builder.build();
        result.setHealth(10000);
        result.setHealthMaximum(10000);
        result.setIdentifier(objectIdentifier("CircleOfPower", result));
        result.setName("Circle of Power");
        result.setSight(TILE_WIDTH);
        result.setType(CircleOfPower);
        result.setSelectable(false);
        return result;
    }
}
