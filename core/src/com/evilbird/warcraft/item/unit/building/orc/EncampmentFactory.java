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
import static com.evilbird.warcraft.item.WarcraftItemConstants.TILE_WIDTH;
import static com.evilbird.warcraft.item.unit.UnitType.Encampment;

/**
 * Instances of this class create Great Halls, an Orcish {@link Building} that
 * creates Orcish gathering units.
 *
 * @author Blair Butterworth
 */
public class EncampmentFactory implements AssetProvider<Item>
{
    private static final GridPoint2 ICON = new GridPoint2(138, 304);
    private static final GridPoint2 SIZE = new GridPoint2(96, 96);

    private BuildingAssets assets;
    private BuildingBuilder builder;

    @Inject
    public EncampmentFactory(Device device) {
        this(device.getAssetStorage());
    }

    public EncampmentFactory(AssetManager manager) {
        this.assets = new BuildingAssets(manager, Encampment, ICON, SIZE);
        this.builder = new BuildingBuilder(assets);
    }

    @Override
    public void load() {
        assets.load();
    }

    @Override
    public Item get() {
        Building result = builder.build();
        result.setHealth(800);
        result.setHealthMaximum(800);
        result.setIdentifier(objectIdentifier("Encampment", result));
        result.setName("Barracks");
        result.setSight(TILE_WIDTH);
        result.setType(Encampment);
        return result;
    }
}