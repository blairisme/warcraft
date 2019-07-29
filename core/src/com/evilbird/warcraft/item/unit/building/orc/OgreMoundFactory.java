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
import static com.evilbird.warcraft.item.WarcraftItemConstants.tiles;
import static com.evilbird.warcraft.item.unit.UnitType.OgreMound;

/**
 * Instances of this class create Orcish Ogre Mounds, a building that allows
 * for the creation of Ogres.
 *
 * @author Blair Butterworth
 */
public class OgreMoundFactory extends BuildingFactoryBase
{
    @Inject
    public OgreMoundFactory(Device device) {
        this(device.getAssetStorage());
    }

    public OgreMoundFactory(AssetManager manager) {
        super(manager, OgreMound);
    }

    @Override
    public Building get(Identifier type) {
        Building result = builder.build();
        result.setArmour(20);
        result.setHealth(500);
        result.setHealthMaximum(500);
        result.setIdentifier(objectIdentifier("OgreMound", result));
        result.setName("Ogre Mound");
        result.setSight(tiles(3));
        result.setType(OgreMound);
        return result;
    }
}
