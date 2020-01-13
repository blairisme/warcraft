/*
 * Copyright (c) 2020, Blair Butterworth
 *
 * This Source Code Form is subject to the terms of the Mozilla Public
 * License, v. 2.0. If a copy of the MPL was not distributed with this
 * file, You can obtain one at http://mozilla.org/MPL/2.0/.
 */

package com.evilbird.warcraft.action.construct;

import com.evilbird.engine.action.Action;
import com.evilbird.engine.common.lang.TextIdentifier;
import com.evilbird.engine.object.GameObject;
import com.evilbird.test.data.item.TestBuildings;
import com.evilbird.test.data.item.TestGatherers;
import com.evilbird.test.testcase.ActionTestCase;
import com.evilbird.warcraft.action.common.exclusion.ItemExclusion;
import com.evilbird.warcraft.action.common.transfer.ResourceTransfer;
import com.evilbird.warcraft.action.death.DeathAction;
import com.evilbird.warcraft.object.unit.UnitType;
import com.evilbird.warcraft.object.unit.building.Building;
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
        ConstructEvents events = Mockito.mock(ConstructEvents.class);
        DeathAction death = Mockito.mock(DeathAction.class);
        ItemExclusion exclusion = Mockito.mock(ItemExclusion.class);
        ResourceTransfer resources = Mockito.mock(ResourceTransfer.class);
        ConstructCancel action = new ConstructCancel(events, death, exclusion, resources, preferences);
        action.setIdentifier(ConstructActions.ConstructBarracksCancel);
        return action;
    }

    @Override
    protected Enum newIdentifier() {
        return ConstructActions.ConstructBarracksCancel;
    }

    @Override
    protected GameObject newItem() {
        Building building = TestBuildings.newTestBuilding(new TextIdentifier("item"), UnitType.Barracks);
        building.setConstructionProgress(0.5f);
        return building;
    }

    @Override
    protected GameObject newTarget() {
        return TestGatherers.newTestGatherer("target");
    }

    @Test
    public void actTest(){
        /*
        GameObject builder = target;
        Building building = (Building) gameObject;
        building.setConstructor(builder);
        Player player = (Player)building.getParent();

        Assert.assertTrue(building.isConstructing());
        Assert.assertEquals(123, player.getResource(ResourceType.Gold), 1);

        Assert.assertFalse(action.act(1));
        Assert.assertFalse(building.isConstructing());
        Assert.assertNull(building.getConstructor());


        Assert.assertFalse(action.act(1));
        Assert.assertTrue(builder.getVisible());

        Assert.assertFalse(action.act(1));
        Assert.assertEquals(173, player.getResource(ResourceType.Gold), 1);

        Assert.assertFalse(action.act(15));
        */
    }
}