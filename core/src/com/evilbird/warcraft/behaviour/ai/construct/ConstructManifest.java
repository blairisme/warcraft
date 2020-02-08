/*
 * Copyright (c) 2020, Blair Butterworth
 *
 * This Source Code Form is subject to the terms of the Mozilla Public
 * License, v. 2.0. If a copy of the MPL was not distributed with this
 * file, You can obtain one at http://mozilla.org/MPL/2.0/.
 */

package com.evilbird.warcraft.behaviour.ai.construct;

import com.evilbird.engine.common.collection.Maps;
import com.evilbird.engine.object.GameObject;
import com.evilbird.warcraft.object.unit.UnitType;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

/**
 * A record of those buildings owned by a player, listed by type and
 * accompanied by the quantity of each building type.
 *
 * @author Blair Butterworth
 */
public class ConstructManifest
{
    private Map<UnitType, Integer> records;

    /**
     * Constructs a new instance of this class given a list of buildings owned
     * by a player.
     */
    public ConstructManifest(Collection<GameObject> buildings) {
        records = new HashMap<>();
        addBuildingQuantities(buildings);
    }

    /**
     * Determines if the manifest contains the given building type and has at
     * least the given quantity of buildings of that type.
     */
    public boolean hasAtLeast(UnitType type, int quantity) {
        if (records.containsKey(type)) {
            return records.get(type) >= quantity;
        }
        return false;
    }

    private void addBuildingQuantities(Collection<GameObject> buildings) {
        for (GameObject building: buildings) {
            addBuildingQuantity(building);
        }
    }

    private void addBuildingQuantity(GameObject building) {
        UnitType type = (UnitType)building.getType();
        records.put(type, Maps.getOrDefault(records, type, 0) + 1);
    }
}
