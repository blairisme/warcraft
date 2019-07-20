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
import static com.evilbird.warcraft.item.unit.UnitType.Encampment;

/**
 * Instances of this class create Great Halls, an Orcish {@link Building} that
 * creates Orcish gathering units.
 *
 * @author Blair Butterworth
 */
public class EncampmentFactory extends BuildingFactoryBase
{
    @Inject
    public EncampmentFactory(Device device) {
        this(device.getAssetStorage());
    }

    public EncampmentFactory(AssetManager manager) {
        super(manager, Encampment);
    }

    @Override
    public Building get(Identifier type) {
        Building result = builder.build();
        result.setDefence(20);
        result.setHealth(800);
        result.setHealthMaximum(800);
        result.setIdentifier(objectIdentifier("Encampment", result));
        result.setName("Barracks");
        result.setSight(tiles(3));
        result.setType(Encampment);
        return result;
    }
}