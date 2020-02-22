/*
 * Copyright (c) 2020, Blair Butterworth
 *
 * This Source Code Form is subject to the terms of the Mozilla Public
 * License, v. 2.0. If a copy of the MPL was not distributed with this
 * file, You can obtain one at http://mozilla.org/MPL/2.0/.
 */

package com.evilbird.warcraft.behaviour.ai.order.campaign;

import com.evilbird.warcraft.behaviour.ai.operation.gather.GatherOrder;
import com.evilbird.warcraft.behaviour.ai.operation.invade.InvasionOrder;
import com.evilbird.warcraft.behaviour.ai.operation.production.ProductionOrder;
import com.evilbird.warcraft.behaviour.ai.order.OperationOrder;
import org.apache.commons.lang3.tuple.Pair;

import static com.evilbird.warcraft.data.resource.ResourceType.Oil;
import static com.evilbird.warcraft.object.unit.UnitType.OilRig;
import static com.evilbird.warcraft.object.unit.UnitType.TrollTanker;

/**
 * Defines the order of AI operations employed by enemy players in Human
 * campaign level 3.
 *
 * @author Blair Butterworth
 */
public class HumanCampaign3 implements OperationOrder
{
    @Override
    public GatherOrder getGatherOrder() {
        return new GatherOrder(
            Pair.of(Oil, 1));
    }

    @Override
    public ProductionOrder getProductionOrder() {
        return new ProductionOrder(
            Pair.of(TrollTanker, 1),
            Pair.of(OilRig, 1));
    }

    @Override
    public InvasionOrder getInvasionOrder() {
        return new InvasionOrder();
    }
}
