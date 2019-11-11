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
import static com.evilbird.warcraft.item.common.upgrade.Upgrade.OilProduction1;
import static com.evilbird.warcraft.item.unit.UnitType.OilRefinery;

/**
 * <p>
 * Instances of this class create Orcish Oil Refineries, a building that provides
 * access to advanced ships.
 * </p>
 * <p>
 * These large steel-shod buildings are designed to refine raw oil for use in
 * the construction and maintenance of the Alliance fleets as well as the
 * creation of unconventional war machines. Like the Shipyard, a Refinery is
 * built along the coastline so that Tankers can deliver their cargo directly
 * to its doors. A Refinery allows oil to be processed with far greater
 * efficiency, increasing the amount of usable oil garnered from every load of
 * crude.
 * </p>
 *
 * @author Blair Butterworth
 */
public class OilRefineryFactory extends BuildingFactoryBase
{
    @Inject
    public OilRefineryFactory(Device device) {
        this(device.getAssetStorage());
    }

    public OilRefineryFactory(AssetManager manager) {
        super(manager, OilRefinery);
    }

    @Override
    public Building get(Identifier type) {
        Building result = builder.build();
        result.setArmour(20);
        result.setHealth(600);
        result.setHealthMaximum(600);
        result.setIdentifier(objectIdentifier("OilRefinery", result));
        result.setSight(tiles(3));
        result.setType(OilRefinery);
        result.setUpgrade(OilProduction1);
        return result;
    }
}
