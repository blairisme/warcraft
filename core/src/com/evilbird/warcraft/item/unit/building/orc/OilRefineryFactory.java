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
import static com.evilbird.warcraft.item.unit.UnitType.OilRefinery;

/**
 * Instances of this class create Orcish Oil Refinery, a building that provides
 * access to advanced ships.
 *
 * @author Blair Butterworth
 */
public class OilRefineryFactory implements AssetProvider<Item>
{
    private static final GridPoint2 ICON = new GridPoint2(46, 380);
    private static final GridPoint2 SIZE = new GridPoint2(96, 96);

    private BuildingAssets assets;
    private BuildingBuilder builder;

    @Inject
    public OilRefineryFactory(Device device) {
        this(device.getAssetStorage());
    }

    public OilRefineryFactory(AssetManager manager) {
        this.assets = new BuildingAssets(manager, OilRefinery, ICON, SIZE);
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
        result.setHealth(600);
        result.setHealthMaximum(600);
        result.setIdentifier(objectIdentifier("OilRefinery", result));
        result.setName("Oil Refinery");
        result.setSight(tiles(3));
        result.setType(OilRefinery);
        return result;
    }
}
