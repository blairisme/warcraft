/*
 * Copyright (c) 2020, Blair Butterworth
 *
 * This Source Code Form is subject to the terms of the Mozilla Public
 * License, v. 2.0. If a copy of the MPL was not distributed with this
 * file, You can obtain one at http://mozilla.org/MPL/2.0/.
 */

package com.evilbird.warcraft.action.gather;

import com.evilbird.engine.action.Action;
import com.evilbird.engine.common.lang.TextIdentifier;
import com.evilbird.engine.object.GameObject;
import com.evilbird.test.data.item.TestGatherers;
import com.evilbird.test.testcase.ActionTestCase;
import com.evilbird.warcraft.action.move.MoveToItemAction;
import com.evilbird.warcraft.object.unit.UnitType;
import org.junit.Ignore;
import org.junit.Test;
import org.mockito.Mockito;

/**
 * Instances of this unit test validate the {@link GatherWood} class.
 *
 * @author Blair Butterworth
 */
public class GatherWoodTest extends ActionTestCase
{
    @Override
    protected Action newAction() {
        GatherDeposit deposit = Mockito.mock(GatherDeposit.class);
        GatherObtainWood gather = Mockito.mock(GatherObtainWood.class);
        MoveToItemAction moveToDepot = Mockito.mock(MoveToItemAction.class);
        MoveToItemAction moveToResource = Mockito.mock(MoveToItemAction.class);

        GatherWood action = new GatherWood(deposit, gather, moveToDepot, moveToResource);
        action.setIdentifier(GatherActions.GatherWood);
        return action;
    }

    @Override
    protected Enum newIdentifier() {
        return GatherActions.GatherWood;
    }

    @Override
    protected GameObject newItem() {
        return TestGatherers.newTestGatherer(new TextIdentifier("item"), UnitType.Peasant);
    }

    @Test
    @Ignore
    @Override
    public void serializeTest() {
    }
}