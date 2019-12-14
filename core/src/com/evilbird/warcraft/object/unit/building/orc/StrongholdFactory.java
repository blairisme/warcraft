/*
 * Copyright (c) 2019, Blair Butterworth
 *
 * This work is licensed under the MIT License. To view a copy of this
 * license, visit
 *
 *        https://opensource.org/licenses/MIT
 */

package com.evilbird.warcraft.object.unit.building.orc;

import com.badlogic.gdx.assets.AssetManager;
import com.evilbird.engine.common.lang.Identifier;
import com.evilbird.engine.device.Device;
import com.evilbird.warcraft.object.unit.building.Building;
import com.evilbird.warcraft.object.unit.building.BuildingFactoryBase;

import javax.inject.Inject;

import static com.evilbird.engine.common.lang.TextIdentifier.objectIdentifier;
import static com.evilbird.warcraft.data.resource.ResourceType.Food;
import static com.evilbird.warcraft.data.upgrade.Upgrade.GoldProduction1;
import static com.evilbird.warcraft.object.common.query.GameObjectUtils.tiles;
import static com.evilbird.warcraft.object.unit.UnitType.Stronghold;

/**
 * Instances of this class create Orcish Stronghold, the central building of
 * the Orcish faction and one that creates gathering units: Peons.
 *
 * @author Blair Butterworth
 */
public class StrongholdFactory extends BuildingFactoryBase
{
    @Inject
    public StrongholdFactory(Device device) {
        this(device.getAssetStorage());
    }

    public StrongholdFactory(AssetManager manager) {
        super(manager, Stronghold);
    }

    @Override
    public Building get(Identifier type) {
        Building result = builder.build();
        result.setArmour(20);
        result.setHealth(1400);
        result.setHealthMaximum(1400);
        result.setIdentifier(objectIdentifier("Stronghold", result));
        result.setSight(tiles(6));
        result.setType(Stronghold);
        result.setResource(Food, 1);
        result.setUpgrade(GoldProduction1);
        return result;
    }
}
