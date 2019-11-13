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
import com.evilbird.engine.object.GameObject;
import com.evilbird.test.data.item.TestGatherers;
import com.evilbird.test.testcase.ActionTestCase;
import com.evilbird.warcraft.action.move.MoveToItemAction;
import com.evilbird.warcraft.object.unit.UnitType;
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
}