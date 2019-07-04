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
import com.evilbird.warcraft.item.unit.building.Fort;

import javax.inject.Inject;

import static com.evilbird.engine.common.lang.TextIdentifier.objectIdentifier;
import static com.evilbird.warcraft.item.WarcraftItemConstants.tiles;
import static com.evilbird.warcraft.item.unit.UnitType.GuardTower;

/**
 * Instances of this class create {@link Building Guard Towers}, loading the
 * necessary assets and defining the appropriate attributes.
 *
 * @author Blair Butterworth
 */
public class GuardTowerFactory implements AssetProvider<Item>
{
    private static final GridPoint2 ICON = new GridPoint2(0, 570);

    private BuildingAssets assets;
    private BuildingBuilder builder;

    @Inject
    public GuardTowerFactory(Device device) {
        this(device.getAssetStorage());
    }

    public GuardTowerFactory(AssetManager manager) {
        this.assets = new BuildingAssets(manager, GuardTower, ICON);
        this.builder = new BuildingBuilder(assets);
    }

    @Override
    public void load() {
        assets.load();
    }

    @Override
    public Item get() {
        Fort result = builder.newFort();
        result.setAttackSpeed(1);
        result.setDefence(20);
        result.setDamageMinimum(4);
        result.setDamageMaximum(12);
        result.setHealth(130);
        result.setHealthMaximum(130);
        result.setIdentifier(objectIdentifier("GuardTower", result));
        result.setName("Guard Tower");
        result.setSight(tiles(9));
        result.setRange(tiles(6));
        result.setType(GuardTower);
        return result;
    }
}
