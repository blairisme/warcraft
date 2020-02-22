/*
 * Copyright (c) 2020, Blair Butterworth
 *
 * This Source Code Form is subject to the terms of the Mozilla Public
 * License, v. 2.0. If a copy of the MPL was not distributed with this
 * file, You can obtain one at http://mozilla.org/MPL/2.0/.
 */

package com.evilbird.warcraft.behaviour.ai.operation.invade;

import com.evilbird.engine.behaviour.framework.tree.SubTree;
import com.evilbird.warcraft.behaviour.ai.operation.player.PlayerData;
import com.evilbird.warcraft.behaviour.ai.order.OperationOrder;
import com.evilbird.warcraft.object.data.player.Player;

import javax.inject.Inject;

/**
 * A behaviour tree that instructs groups of idle attackers to attack an
 * enemy.
 *
 * @author Blair Butterworth
 */
public class InvasionBehaviour extends SubTree<PlayerData, InvasionData>
{
    @Inject
    public InvasionBehaviour(InvasionSequence invasionSequence) {
        super(invasionSequence);
    }

    @Override
    protected InvasionData convertObject(PlayerData playerData) {
        Player player = playerData.getPlayer();
        OperationOrder operationOrder = playerData.getOrder();
        InvasionOrder invasionOrder = operationOrder.getInvasionOrder();
        return new InvasionData(player, invasionOrder);
    }
}
