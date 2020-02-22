/*
 * Copyright (c) 2020, Blair Butterworth
 *
 * This Source Code Form is subject to the terms of the Mozilla Public
 * License, v. 2.0. If a copy of the MPL was not distributed with this
 * file, You can obtain one at http://mozilla.org/MPL/2.0/.
 */

package com.evilbird.warcraft.behaviour.ai.operation.invade;

import com.evilbird.warcraft.object.data.player.Player;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

/**
 * Specifies the order in which an enemy player invades the teams that oppose
 * it.
 *
 * @author Blair Butterworth
 */
public class InvasionOrder
{
    private List<InvasionWave> order;

    public InvasionOrder(InvasionWave ... entries) {
        this.order = Arrays.asList(entries);
    }

    public List<InvasionWave> getSequence() {
        return Collections.unmodifiableList(order);
    }

    /**
     * Returns details about the next invasion wave.
     */
    public InvasionWave getNextWave(Player player) {



//        Map<ResourceType, Integer> types = getTypeCount(gatherers);
//        for (Pair<ResourceType, Integer> entry: order) {
//            int orderCount = entry.getValue();
//            int gathererCount = Maps.getOrDefault(types, entry.getKey(), 0);
//            if (gathererCount < orderCount) {
//                return entry.getKey();
//            }
//        }
//        return Gold;

        return null;
    }
}
