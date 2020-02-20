/*
 * Copyright (c) 2020, Blair Butterworth
 *
 * This Source Code Form is subject to the terms of the Mozilla Public
 * License, v. 2.0. If a copy of the MPL was not distributed with this
 * file, You can obtain one at http://mozilla.org/MPL/2.0/.
 */

package com.evilbird.warcraft.behaviour.ai.order;

import com.evilbird.warcraft.behaviour.ai.operation.gather.GatherOrder;
import com.evilbird.warcraft.behaviour.ai.operation.production.ProductionOrder;

/**
 * Defines the order in which AI players produce units, gather resources and
 * attack opposing players.
 *
 * @author Blair Butterworth
 */
public interface OperationOrder
{
    GatherOrder getGatherOrder();

    ProductionOrder getProductionOrder();
}
