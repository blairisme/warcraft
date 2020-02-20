/*
 * Copyright (c) 2020, Blair Butterworth
 *
 * This Source Code Form is subject to the terms of the Mozilla Public
 * License, v. 2.0. If a copy of the MPL was not distributed with this
 * file, You can obtain one at http://mozilla.org/MPL/2.0/.
 */

package com.evilbird.warcraft.behaviour.ai.operation.gather;

import com.evilbird.engine.common.collection.Maps;
import com.evilbird.engine.object.GameObject;
import com.evilbird.warcraft.data.resource.ResourceType;
import org.apache.commons.lang3.tuple.Pair;

import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static com.evilbird.warcraft.data.resource.ResourceType.Gold;

/**
 * Specifies the order in which a player gathers resources.
 *
 * @author Blair Butterworth
 */
public class GatherOrder
{
    private List<Pair<ResourceType, Integer>> order;

    @SafeVarargs
    public GatherOrder(Pair<ResourceType, Integer>... entries) {
        this.order = Arrays.asList(entries);
    }

    public List<Pair<ResourceType, Integer>> getSequence() {
        return Collections.unmodifiableList(order);
    }

    /**
     * Returns the next product that should be produced.
     */
    public ResourceType getNextResource(Collection<GameObject> gatherers) {
        Map<ResourceType, Integer> types = getTypeCount(gatherers);
        for (Pair<ResourceType, Integer> entry: order) {
            int orderCount = entry.getValue();
            int gathererCount = Maps.getOrDefault(types, entry.getKey(), 0);
            if (gathererCount < orderCount) {
                return entry.getKey();
            }
        }
        return Gold;
    }

    private Map<ResourceType, Integer> getTypeCount(Collection<GameObject> objects) {
        Map<ResourceType, Integer> result = new HashMap<>();
        for (GameObject object: objects) {
            ResourceType type = GatherOperations.getGatheringType(object);
            if (type != null) {
                int oldValue = Maps.getOrDefault(result, type, 0);
                int newValue = oldValue + 1;
                result.put(type, newValue);
            }
        }
        return result;
    }
}
