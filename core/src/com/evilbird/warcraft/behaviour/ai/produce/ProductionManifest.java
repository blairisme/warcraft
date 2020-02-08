/*
 * Copyright (c) 2020, Blair Butterworth
 *
 * This Source Code Form is subject to the terms of the Mozilla Public
 * License, v. 2.0. If a copy of the MPL was not distributed with this
 * file, You can obtain one at http://mozilla.org/MPL/2.0/.
 */

package com.evilbird.warcraft.behaviour.ai.produce;

import com.evilbird.engine.common.collection.Maps;
import com.evilbird.engine.object.GameObject;
import com.evilbird.warcraft.object.unit.UnitType;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

/**
 * A record of the buildings and units owned by a player, listed by type and
 * accompanied by the quantity of each building type.
 *
 * @author Blair Butterworth
 */
public class ProductionManifest
{
    private Map<UnitType, Integer> records;

    /**
     * Constructs a new instance of this class given a list of units owned
     * by a player.
     */
    public ProductionManifest(Collection<GameObject> objects) {
        records = new HashMap<>();
        updateRecords(objects);
    }

    /**
     * Determines if the manifest contains the given unit type and has at
     * least the given quantity of units of that type.
     */
    public boolean hasAtLeast(UnitType type, int quantity) {
        if (records.containsKey(type)) {
            return records.get(type) >= quantity;
        }
        return false;
    }

    private void updateRecords(Collection<GameObject> objects) {
        for (GameObject object: objects) {
            updateRecord(object);
        }
    }

    private void updateRecord(GameObject object) {
        UnitType type = (UnitType)object.getType();
        records.put(type, Maps.getOrDefault(records, type, 0) + 1);
    }
}
