/*
 * Blair Butterworth (c) 2019
 *
 * This work is licensed under the MIT License. To view a copy of this
 * license, visit
 *
 *      https://opensource.org/licenses/MIT
 */

package com.evilbird.warcraft.action.gather;

import com.evilbird.engine.action.Action;
import com.evilbird.engine.common.lang.TextIdentifier;
import com.evilbird.engine.item.Item;
import com.evilbird.test.data.item.TestGatherers;
import com.evilbird.test.testcase.ActionTestCase;
import com.evilbird.warcraft.action.move.MoveToItemAction;
import com.evilbird.warcraft.item.unit.UnitType;
import org.mockito.Mockito;

/**
 * Instances of this unit test validate the {@link GatherWood} class.
 *
 * @author Blair Butterworth
 */
public class GatherGoldTest extends ActionTestCase
{
    @Override
    protected Action newAction() {
        GatherDeposit deposit = Mockito.mock(GatherDeposit.class);
        GatherObtainGold gather = Mockito.mock(GatherObtainGold.class);
        MoveToItemAction moveToDepot = Mockito.mock(MoveToItemAction.class);
        MoveToItemAction moveToResource = Mockito.mock(MoveToItemAction.class);

        GatherGold action = new GatherGold(deposit, gather, moveToDepot, moveToResource);
        action.setIdentifier(GatherActions.GatherGold);
        return action;
    }

    @Override
    protected Enum newIdentifier() {
        return GatherActions.GatherGold;
    }

    @Override
    protected Item newItem() {
        return TestGatherers.newTestGatherer(new TextIdentifier("item"), UnitType.Peasant);
    }
}