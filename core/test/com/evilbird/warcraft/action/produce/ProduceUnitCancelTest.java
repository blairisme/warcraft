/*
 * Copyright (c) 2020, Blair Butterworth
 *
 * This Source Code Form is subject to the terms of the Mozilla Public
 * License, v. 2.0. If a copy of the MPL was not distributed with this
 * file, You can obtain one at http://mozilla.org/MPL/2.0/.
 */

package com.evilbird.warcraft.action.produce;

import com.evilbird.engine.action.Action;
import com.evilbird.engine.common.lang.TextIdentifier;
import com.evilbird.engine.object.GameObject;
import com.evilbird.test.data.item.TestBuildings;
import com.evilbird.test.testcase.ActionTestCase;
import com.evilbird.warcraft.action.common.transfer.ResourceTransfer;
import com.evilbird.warcraft.object.unit.UnitType;
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
        ProduceUnitCancel action = new ProduceUnitCancel(events, resources, preferences);
        action.setIdentifier(ProduceUnitActions.TrainFootmanCancel);
        return action;
    }

    @Override
    protected Enum newIdentifier() {
        return ProduceUnitActions.TrainFootmanCancel;
    }

    @Override
    protected GameObject newItem() {
        return TestBuildings.newTestBuilding(new TextIdentifier("item"), UnitType.Barracks);
    }
}