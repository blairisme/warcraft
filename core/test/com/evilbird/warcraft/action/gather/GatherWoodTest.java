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
import com.evilbird.warcraft.item.unit.UnitType;
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
        GatherWood action = new GatherWood(Mockito.mock(GatherReporter.class));
        action.setIdentifier(GatherActions.GatherWood);
        return action;
    }

    @Override
    protected Enum newIdentifier() {
        return GatherActions.GatherWood;
    }

    @Override
    protected Item newItem() {
        return TestGatherers.newTestGatherer(new TextIdentifier("item"), UnitType.Peasant);
    }
}