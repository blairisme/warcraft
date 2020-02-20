/*
 * Copyright (c) 2020, Blair Butterworth
 *
 * This Source Code Form is subject to the terms of the Mozilla Public
 * License, v. 2.0. If a copy of the MPL was not distributed with this
 * file, You can obtain one at http://mozilla.org/MPL/2.0/.
 */

package com.evilbird.warcraft.behaviour.ai.operation.production;

import com.evilbird.engine.behaviour.framework.tree.SubTree;
import com.evilbird.warcraft.behaviour.ai.operation.player.PlayerData;
import com.evilbird.warcraft.behaviour.ai.order.OperationOrder;
import com.evilbird.warcraft.object.data.player.Player;

import javax.inject.Inject;

/**
 * A behaviour tree that produces units, buildings and upgrades for an AI player.
 *
 * @author Blair Butterworth
 */
public class ProductionBehaviour extends SubTree<PlayerData, ProductionData>
{
    @Inject
    public ProductionBehaviour(ProductionSequence sequence) {
        super(sequence);
    }

    @Override
    protected ProductionData convertObject(PlayerData playerData) {
        Player player = playerData.getPlayer();
        OperationOrder playerOrder = playerData.getOrder();
        ProductionOrder productionOrder = playerOrder.getProductionOrder();
        return new ProductionData(player, productionOrder);
    }
}
