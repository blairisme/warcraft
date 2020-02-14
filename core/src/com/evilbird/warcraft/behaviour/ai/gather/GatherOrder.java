/*
 * Copyright (c) 2020, Blair Butterworth
 *
 * This Source Code Form is subject to the terms of the Mozilla Public
 * License, v. 2.0. If a copy of the MPL was not distributed with this
 * file, You can obtain one at http://mozilla.org/MPL/2.0/.
 */

package com.evilbird.warcraft.behaviour.ai.gather;

import com.evilbird.engine.common.collection.Maps;
import com.evilbird.engine.object.GameObject;
import com.evilbird.warcraft.data.resource.ResourceType;
import com.evilbird.warcraft.object.data.player.Player;
import org.apache.commons.lang3.tuple.Pair;

import java.util.Arrays;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static com.evilbird.warcraft.data.resource.ResourceType.Gold;
import static com.evilbird.warcraft.data.resource.ResourceType.Wood;

/**
 * Specifies the order in which a player gathers resources.
 *
 * @author Blair Butterworth
 */
//TODO: Add support for simplified campaign gather orders
public class GatherOrder
{
    /**
     * Specifies the resource gathering order for Human and Orc players.
     */
    public static GatherOrder GENERAL = new GatherOrder(
        Pair.of(Gold, 2),
        Pair.of(Wood, 1),
        Pair.of(Gold, 4),
        Pair.of(Wood, 2));

    /**
     * Returns the resource gathering order appropriate for the given player.
     */
    public static GatherOrder forPlayer(Player player) {
        return GENERAL;
    }

    private List<Pair<ResourceType, Integer>> order;

    @SafeVarargs
    private GatherOrder(Pair<ResourceType, Integer> ... entries) {
        this.order = Arrays.asList(entries);
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
