/*
 * Copyright (c) 2020, Blair Butterworth
 *
 * This Source Code Form is subject to the terms of the Mozilla Public
 * License, v. 2.0. If a copy of the MPL was not distributed with this
 * file, You can obtain one at http://mozilla.org/MPL/2.0/.
 */

package com.evilbird.warcraft.object.unit.building.neutral;

import com.badlogic.gdx.assets.AssetManager;
import com.evilbird.engine.common.lang.Identifier;
import com.evilbird.engine.device.Device;
import com.evilbird.warcraft.object.unit.building.Building;
import com.evilbird.warcraft.object.unit.building.BuildingFactoryBase;

import javax.inject.Inject;

import static com.evilbird.engine.common.lang.TextIdentifier.objectIdentifier;
import static com.evilbird.warcraft.object.common.query.GameObjectUtils.tiles;
import static com.evilbird.warcraft.object.unit.UnitType.DarkPortal;

/**
 * Instances of this class create {@link Building Dark Portals}, loading the
 * necessary assets and defining the appropriate attributes.
 *
 * @author Blair Butterworth
 */
public class DarkPortalFactory extends BuildingFactoryBase
{
    @Inject
    public DarkPortalFactory(Device device) {
        this(device.getAssetStorage());
    }

    public DarkPortalFactory(AssetManager manager) {
        super(manager, DarkPortal);
    }

    @Override
    public Building get(Identifier type) {
        Building result = builder.build();
        result.setHealth(10000);
        result.setHealthMaximum(10000);
        result.setIdentifier(objectIdentifier("DarkPortal", result));
        result.setSight(tiles(1));
        result.setType(DarkPortal);
        result.setSelectable(false);
        return result;
    }
}
