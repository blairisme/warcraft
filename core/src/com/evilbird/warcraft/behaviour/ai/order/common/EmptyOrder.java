/*
 * Copyright (c) 2020, Blair Butterworth
 *
 * This Source Code Form is subject to the terms of the Mozilla Public
 * License, v. 2.0. If a copy of the MPL was not distributed with this
 * file, You can obtain one at http://mozilla.org/MPL/2.0/.
 */

package com.evilbird.warcraft.behaviour.ai.order.common;

import com.evilbird.warcraft.behaviour.ai.operation.gather.GatherOrder;
import com.evilbird.warcraft.behaviour.ai.operation.production.ProductionOrder;
import com.evilbird.warcraft.behaviour.ai.order.OperationOrder;

/**
 * An {@link OperationOrder} implementation without any operations.
 *
 * @author Blair Butterworth
 */
public class EmptyOrder implements OperationOrder
{
    @Override
    public GatherOrder getGatherOrder() {
        return new GatherOrder();
    }

    @Override
    public ProductionOrder getProductionOrder() {
        return new ProductionOrder();
    }
}
