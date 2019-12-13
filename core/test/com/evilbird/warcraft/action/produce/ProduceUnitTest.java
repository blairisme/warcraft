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
import com.evilbird.engine.events.EventQueue;
import com.evilbird.engine.object.GameObject;
import com.evilbird.test.data.item.TestBuildings;
import com.evilbird.test.data.item.TestCombatants;
import com.evilbird.test.testcase.ActionTestCase;
import com.evilbird.warcraft.action.common.create.CreateEvents;
import com.evilbird.warcraft.action.common.transfer.ResourceTransfer;
import com.evilbird.warcraft.object.common.production.ProductionCosts;
import com.evilbird.warcraft.object.common.production.ProductionTimes;
import com.evilbird.warcraft.object.unit.UnitType;
import com.evilbird.warcraft.object.unit.building.Building;
import com.evilbird.warcraft.object.unit.combatant.Combatant;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mockito;

import static com.evilbird.warcraft.action.produce.ProduceUnitActions.TrainFootman;
import static com.evilbird.warcraft.data.resource.ResourceType.Food;
import static com.evilbird.warcraft.data.resource.ResourceType.Gold;
import static com.evilbird.warcraft.object.unit.UnitType.Footman;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

/**
 * Instances of this unit test validate the {@link ProduceUnit} class.
 *
 * @author Blair Butterworth
 */
public class ProduceUnitTest extends ActionTestCase
{
    private EventQueue reporter;
    private Building barracks;
    private Combatant footman;
    
    @Before
    public void setup() {
        reporter = Mockito.mock(EventQueue.class);
        super.setup();
        barracks = (Building) gameObject;
        footman = TestCombatants.newTestCombatant("footman");
        when(objectFactory.get(Footman)).thenReturn(footman);
    }

    @Override
    protected Action newAction() {
        CreateEvents createEvents = new CreateEvents(reporter);
        ProduceEvents produceEvents = new ProduceEvents(reporter);
        ResourceTransfer resources = Mockito.mock(ResourceTransfer.class);
        ProductionTimes times = Mockito.mock(ProductionTimes.class);
        ProductionCosts costs = Mockito.mock(ProductionCosts.class);
        ProduceUnit action = new ProduceUnit(
            createEvents, produceEvents, objectFactory, preferences, resources, costs, times);
        action.setIdentifier(TrainFootman);
        return action;
    }

    @Override
    protected Enum newIdentifier() {
        return TrainFootman;
    }

    @Override
    protected GameObject newItem() {
        return TestBuildings.newTestBuilding(new TextIdentifier("item"), UnitType.Barracks);
    }

    @Test
    public void actTest() {
        player.setResource(Food, 10);
        player.setResource(Gold, 1000);

        assertFalse(action.act(1));
        assertTrue(barracks.isProducing());
        verify(reporter).add(new ProduceEvent(barracks, ProduceStatus.Started));

        assertFalse(action.act(1));
        assertEquals(0.1f, barracks.getProductionProgress(), 0.1f);

        assertTrue(action.act(1f));
        assertFalse(barracks.isProducing());
        verify(reporter).add(new ProduceEvent(barracks, ProduceStatus.Complete));
    }
}
