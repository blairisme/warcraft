/*
 * Blair Butterworth (c) 2019
 *
 * This work is licensed under the MIT License. To view a copy of this
 * license, visit
 *
 *      https://opensource.org/licenses/MIT
 */

package com.evilbird.warcraft.action.train;

import com.evilbird.engine.action.Action;
import com.evilbird.engine.common.lang.TextIdentifier;
import com.evilbird.engine.events.EventQueue;
import com.evilbird.engine.item.Item;
import com.evilbird.test.data.item.TestBuildings;
import com.evilbird.test.testcase.ActionTestCase;
import com.evilbird.warcraft.action.produce.ProduceUnit;
import com.evilbird.warcraft.item.unit.UnitType;
import com.evilbird.warcraft.item.unit.building.Building;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;
import org.mockito.Mockito;

import static com.evilbird.warcraft.action.produce.ProduceActions.TrainFootman;
import static com.evilbird.warcraft.action.produce.ProductionTimes.productionTime;

/**
 * Instances of this unit test validate the {@link ProduceUnit} class.
 *
 * @author Blair Butterworth
 */
public class ProduceUnitTest extends ActionTestCase
{
    private EventQueue reporter;

    @Before
    public void setup() {
        reporter = Mockito.mock(EventQueue.class);
        super.setup();
    }

    @Override
    protected Action newAction() {
        ProduceUnit action = new ProduceUnit(reporter);
        action.setIdentifier(TrainFootman);
        return action;
    }

    @Override
    protected Enum newIdentifier() {
        return TrainFootman;
    }

    @Override
    protected Item newItem() {
        return TestBuildings.newTestBuilding(new TextIdentifier("item"), UnitType.Barracks);
    }

    @Test
    @Ignore
    public void actTest() {
        Building subject = (Building)item;

        Assert.assertFalse(action.act(1));
        //Mockito.verify(reporter).onTransfer(player, Gold, 123, 0);
        //Mockito.verify(reporter).onProductionStarted(subject);

        Assert.assertFalse(action.act(1));
        Assert.assertEquals(0.05f, subject.getProductionProgress(), 0.1f);

        Assert.assertFalse(action.act(1));
        Assert.assertEquals(0.1f, subject.getProductionProgress(), 0.1f);

        Assert.assertFalse(action.act(productionTime(UnitType.Footman) + 10));
        Assert.assertFalse(subject.isProducing());

        Assert.assertFalse(action.act(1));
//        Mockito.verify(reporter).onCreate(Mockito.any(Combatant.class));

        Assert.assertTrue(action.act(1));
    }
}
