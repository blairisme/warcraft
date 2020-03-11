/*
 * Copyright (c) 2020, Blair Butterworth
 *
 * This Source Code Form is subject to the terms of the Mozilla Public
 * License, v. 2.0. If a copy of the MPL was not distributed with this
 * file, You can obtain one at http://mozilla.org/MPL/2.0/.
 */

package com.evilbird.warcraft.behaviour.ai.operation.gather;

import com.evilbird.engine.behaviour.framework.branch.SubTree;
import com.evilbird.warcraft.behaviour.ai.operation.player.PlayerData;
import com.evilbird.warcraft.behaviour.ai.order.OperationOrder;
import com.evilbird.warcraft.object.data.player.Player;

import javax.inject.Inject;

/**
 * A behaviour tree that gatherers resources for an enemy player.
 *
 * @author Blair Butterworth
 */
public class GatherBehaviour extends SubTree<PlayerData, GatherData>
{
    @Inject
    public GatherBehaviour(GatherSequence gatherSequence) {
        super(gatherSequence);
    }

    @Override
    protected GatherData convertObject(PlayerData playerData) {
        Player player = playerData.getPlayer();
        OperationOrder playerOrder = playerData.getOrder();
        GatherOrder order = playerOrder.getGatherOrder();
        return new GatherData(player, order);
    }
}
