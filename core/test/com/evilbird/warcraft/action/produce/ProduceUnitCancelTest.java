/*
 * Copyright (c) 2019, Blair Butterworth
 *
 * This work is licensed under the MIT License. To view a copy of this
 * license, visit
 *
 *        https://opensource.org/licenses/MIT
 */

package com.evilbird.warcraft.action.produce;

import com.evilbird.engine.action.Action;
import com.evilbird.engine.common.lang.TextIdentifier;
import com.evilbird.engine.item.Item;
import com.evilbird.test.data.item.TestBuildings;
import com.evilbird.test.testcase.ActionTestCase;
import com.evilbird.warcraft.action.common.transfer.ResourceTransfer;
import com.evilbird.warcraft.item.common.production.ProductionCosts;
import com.evilbird.warcraft.item.unit.UnitType;
import org.mockito.Mockito;

/**
 * Instances of this unit test validate the {@link ProduceUnitCancel} class.
 *
 * @author Blair Butterworth
 */
public class ProduceUnitCancelTest extends ActionTestCase
{
    @Override
    protected Action newAction() {
        ProduceEvents events = Mockito.mock(ProduceEvents.class);
        ResourceTransfer resources = Mockito.mock(ResourceTransfer.class);
        ProductionCosts costs = Mockito.mock(ProductionCosts.class);
        ProduceUnitCancel action = new ProduceUnitCancel(events, resources, costs);
        action.setIdentifier(ProduceUnitActions.TrainFootmanCancel);
        return action;
    }

    @Override
    protected Enum newIdentifier() {
        return ProduceUnitActions.TrainFootmanCancel;
    }

    @Override
    protected Item newItem() {
        return TestBuildings.newTestBuilding(new TextIdentifier("item"), UnitType.Barracks);
    }
}