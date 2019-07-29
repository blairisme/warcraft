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
import com.evilbird.engine.common.lang.Identifier;
import com.evilbird.engine.device.Device;
import com.evilbird.warcraft.item.unit.building.Building;
import com.evilbird.warcraft.item.unit.building.BuildingFactoryBase;

import javax.inject.Inject;

import static com.evilbird.engine.common.lang.TextIdentifier.objectIdentifier;
import static com.evilbird.warcraft.item.WarcraftItemConstants.tiles;
import static com.evilbird.warcraft.item.unit.UnitType.Shipyard;

/**
 * Instances of this class create Human Shipyards, a building that allows for
 * the construction of ships.
 *
 * @author Blair Butterworth
 */
public class ShipyardFactory extends BuildingFactoryBase
{
    @Inject
    public ShipyardFactory(Device device) {
        this(device.getAssetStorage());
    }

    public ShipyardFactory(AssetManager manager) {
        super(manager, Shipyard);
    }

    @Override
    public Building get(Identifier type) {
        Building result = builder.build();
        result.setArmour(20);
        result.setHealth(1100);
        result.setHealthMaximum(1100);
        result.setIdentifier(objectIdentifier("Shipyard", result));
        result.setName("Shipyard");
        result.setSight(tiles(3));
        result.setType(Shipyard);
        return result;
    }
}
