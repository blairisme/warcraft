/*
 * Copyright (c) 2020, Blair Butterworth
 *
 * This Source Code Form is subject to the terms of the Mozilla Public
 * License, v. 2.0. If a copy of the MPL was not distributed with this
 * file, You can obtain one at http://mozilla.org/MPL/2.0/.
 */

package com.evilbird.warcraft.behaviour.ai.order.campaign;

import com.evilbird.warcraft.behaviour.ai.operation.production.ProductionOrder;
import com.evilbird.warcraft.behaviour.ai.order.OperationOrder;
import com.evilbird.warcraft.common.WarcraftFaction;
import com.evilbird.warcraft.data.product.Product;
import com.evilbird.warcraft.object.unit.UnitType;
import org.apache.commons.lang3.tuple.Pair;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

/**
 * A test suite that validates logic in campaign ai operation order classes.
 *
 * @author Blair Butterworth
 */
public abstract class CampaignTestCase
{
    private OperationOrder order;
    private WarcraftFaction faction;

    @Before
    public void setup() {
        order = getCampaign();
        faction = getFaction();
    }

    public abstract OperationOrder getCampaign();

    public abstract WarcraftFaction getFaction();

    @Test
    public void testFactionOperations() {
        ProductionOrder productionOrder = order.getProductionOrder();
        for (Pair<Product, Integer> element: productionOrder.getSequence()) {
            Product product = element.getKey();

            if (product instanceof UnitType) {
                UnitType type = (UnitType)product;
                Assert.assertEquals(type.getFaction(), faction);
            }
        }
    }
}
