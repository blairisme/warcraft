/*
 * Copyright (c) 2020, Blair Butterworth
 *
 * This Source Code Form is subject to the terms of the Mozilla Public
 * License, v. 2.0. If a copy of the MPL was not distributed with this
 * file, You can obtain one at http://mozilla.org/MPL/2.0/.
 */

package com.evilbird.warcraft.behaviour.ai.order.scenario;

import com.evilbird.warcraft.behaviour.ai.operation.gather.GatherOrder;
import com.evilbird.warcraft.behaviour.ai.operation.production.ProductionOrder;
import com.evilbird.warcraft.behaviour.ai.order.OperationOrder;
import org.apache.commons.lang3.tuple.Pair;

import static com.evilbird.warcraft.data.resource.ResourceType.Gold;
import static com.evilbird.warcraft.data.resource.ResourceType.Oil;
import static com.evilbird.warcraft.data.resource.ResourceType.Wood;
import static com.evilbird.warcraft.object.unit.UnitType.Encampment;
import static com.evilbird.warcraft.object.unit.UnitType.GreatHall;
import static com.evilbird.warcraft.object.unit.UnitType.Peon;
import static com.evilbird.warcraft.object.unit.UnitType.PigFarm;
import static com.evilbird.warcraft.object.unit.UnitType.TrollLumberMill;
import static com.evilbird.warcraft.object.unit.UnitType.TrollTanker;

/**
 * Defines the order of AI operations employed by enemy Orc players in
 * scenarios on easy mode.
 *
 * @author Blair Butterworth
 */
public class OrcEasy implements OperationOrder
{
    @Override
    public GatherOrder getGatherOrder() {
        return new GatherOrder(
            Pair.of(Gold, 2),
            Pair.of(Wood, 1),
            Pair.of(Gold, 4),
            Pair.of(Wood, 2),
            Pair.of(Oil, 1));
    }

    @Override
    public ProductionOrder getProductionOrder() {
        return new ProductionOrder(
            Pair.of(GreatHall, 1),
            Pair.of(PigFarm, 1),
            Pair.of(Peon, 2),
            Pair.of(Encampment, 1),
            Pair.of(TrollLumberMill, 1),
            Pair.of(TrollTanker, 1));
    }
}
