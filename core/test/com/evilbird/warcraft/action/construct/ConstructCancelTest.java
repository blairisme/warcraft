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
import com.evilbird.engine.item.Item;
import com.evilbird.test.data.item.TestBuildings;
import com.evilbird.test.testcase.ActionTestCase;
import com.evilbird.warcraft.item.data.player.Player;
import com.evilbird.warcraft.item.unit.UnitType;
import com.evilbird.warcraft.item.unit.building.Building;
import com.evilbird.warcraft.item.unit.resource.ResourceType;
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
        ConstructCancel action = new ConstructCancel(Mockito.mock(ConstructReporter.class));
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

    @Test
    public void actTest(){
        Building building = (Building)item;
        Player player = (Player)building.getParent();

        Assert.assertTrue(building.isConstructing());
        Assert.assertEquals(123, player.getResource(ResourceType.Gold), 1);

        Assert.assertTrue(action.act(0));

        Assert.assertFalse(building.isConstructing());
        Assert.assertEquals(173, player.getResource(ResourceType.Gold), 1);
    }

}