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
import com.evilbird.engine.common.lang.Identifier;
import com.evilbird.engine.device.Device;
import com.evilbird.warcraft.item.unit.building.Building;
import com.evilbird.warcraft.item.unit.building.BuildingFactoryBase;

import javax.inject.Inject;

import static com.evilbird.engine.common.lang.TextIdentifier.objectIdentifier;
import static com.evilbird.warcraft.item.common.query.GameObjectUtils.tiles;
import static com.evilbird.warcraft.item.unit.UnitType.Metalworks;

/**
 * Instances of this class create Orcish Metalworks, a building that provides
 * upgrades for ships.
 *
 * @author Blair Butterworth
 */
public class MetalworksFactory extends BuildingFactoryBase
{
    @Inject
    public MetalworksFactory(Device device) {
        this(device.getAssetStorage());
    }

    public MetalworksFactory(AssetManager manager) {
        super(manager, Metalworks);
    }

    @Override
    public Building get(Identifier type) {
        Building result = builder.build();
        result.setArmour(20);
        result.setHealth(750);
        result.setHealthMaximum(750);
        result.setIdentifier(objectIdentifier("Metalworks", result));
        result.setSight(tiles(3));
        result.setType(Metalworks);
        return result;
    }
}
