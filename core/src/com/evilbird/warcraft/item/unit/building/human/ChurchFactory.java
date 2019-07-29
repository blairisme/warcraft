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
import static com.evilbird.warcraft.item.unit.UnitType.Church;

/**
 * Instances of this class create Human Churches, a building that allows
 * for the creation of Paladins.
 *
 * @author Blair Butterworth
 */
public class ChurchFactory extends BuildingFactoryBase
{
    @Inject
    public ChurchFactory(Device device) {
        this(device.getAssetStorage());
    }

    public ChurchFactory(AssetManager manager) {
        super(manager, Church);
    }

    @Override
    public Building get(Identifier type) {
        Building result = builder.build();
        result.setArmour(20);
        result.setHealth(700);
        result.setHealthMaximum(700);
        result.setIdentifier(objectIdentifier("Church", result));
        result.setSight(tiles(3));
        result.setType(Church);
        return result;
    }
}
