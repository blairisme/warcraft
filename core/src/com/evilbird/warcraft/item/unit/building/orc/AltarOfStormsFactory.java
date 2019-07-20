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
import static com.evilbird.warcraft.item.unit.UnitType.AltarOfStorms;

/**
 * Instances of this class create Orcish Altar of Storms, a building that
 * allows for the creation of Ogre Mages.
 *
 * @author Blair Butterworth
 */
public class AltarOfStormsFactory extends BuildingFactoryBase
{
    @Inject
    public AltarOfStormsFactory(Device device) {
        this(device.getAssetStorage());
    }

    public AltarOfStormsFactory(AssetManager manager) {
        super(manager, AltarOfStorms);
    }

    @Override
    public Building get(Identifier type) {
        Building result = builder.build();
        result.setDefence(20);
        result.setHealth(700);
        result.setHealthMaximum(700);
        result.setIdentifier(objectIdentifier("AltarOfStorms", result));
        result.setName("Altar of Storms");
        result.setSight(tiles(3));
        result.setType(AltarOfStorms);
        return result;
    }
}
