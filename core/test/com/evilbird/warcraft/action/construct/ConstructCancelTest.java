/*
 * Blair Butterworth (c) 2019
 *
 * This work is licensed under the MIT License. To view a copy of this
 * license, visit
 *
 *      https://opensource.org/licenses/MIT
 */

package com.evilbird.warcraft.action.construct;

import com.evilbird.engine.action.Action;
import com.evilbird.engine.common.lang.TextIdentifier;
import com.evilbird.engine.events.EventQueue;
import com.evilbird.engine.item.Item;
import com.evilbird.test.data.item.TestBuildings;
import com.evilbird.test.data.item.TestGatherers;
import com.evilbird.test.testcase.ActionTestCase;
import com.evilbird.warcraft.action.death.DeathAction;
import com.evilbird.warcraft.item.common.resource.ResourceType;
import com.evilbird.warcraft.item.data.player.Player;
import com.evilbird.warcraft.item.unit.UnitType;
import com.evilbird.warcraft.item.unit.building.Building;
import org.junit.Assert;
import org.junit.Test;
import org.mockito.Mockito;

/**
 * Instances of this unit test validate the {@link ConstructCancel} class.
 *
 * @author Blair Butterworth
 */
public class ConstructCancelTest extends ActionTestCase
{
    @Override
    protected Action newAction() {
        ConstructCancel action = new ConstructCancel(Mockito.mock(EventQueue.class), Mockito.mock(DeathAction.class));
        action.setIdentifier(ConstructActions.ConstructBarracksCancel);
        return action;
    }

    @Override
    protected Enum newIdentifier() {
        return ConstructActions.ConstructBarracksCancel;
    }

    @Override
    protected Item newItem() {
        Building building = TestBuildings.newTestBuilding(new TextIdentifier("item"), UnitType.Barracks);
        building.setConstructionProgress(0.5f);
        return building;
    }

    @Override
    protected Item newTarget() {
        return TestGatherers.newTestGatherer("target");
    }

    @Test
    public void actTest(){
        Item builder = target;
        Building building = (Building)item;
        building.setAssociatedItem(builder);
        Player player = (Player)building.getParent();

        Assert.assertTrue(building.isConstructing());
        Assert.assertEquals(123, player.getResource(ResourceType.Gold), 1);

        Assert.assertFalse(action.act(1));
        Assert.assertFalse(building.isConstructing());
        Assert.assertNull(building.getAssociatedItem());

        /*
        Assert.assertFalse(action.act(1));
        Assert.assertTrue(builder.getVisible());

        Assert.assertFalse(action.act(1));
        Assert.assertEquals(173, player.getResource(ResourceType.Gold), 1);

        Assert.assertFalse(action.act(15));
        */
    }
}