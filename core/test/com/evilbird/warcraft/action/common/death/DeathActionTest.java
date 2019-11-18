/*
 * Copyright (c) 2019, Blair Butterworth
 *
 * This work is licensed under the MIT License. To view a copy of this
 * license, visit
 *
 *        https://opensource.org/licenses/MIT
 */

package com.evilbird.warcraft.action.common.death;

import com.evilbird.engine.events.EventQueue;
import com.evilbird.test.data.item.TestBuildings;
import com.evilbird.test.data.item.TestCombatants;
import com.evilbird.test.testcase.GameTestCase;
import com.evilbird.warcraft.action.common.remove.DeathAction;
import com.evilbird.warcraft.common.WarcraftPreferences;
import com.evilbird.warcraft.object.data.player.Player;
import com.evilbird.warcraft.object.unit.UnitAnimation;
import com.evilbird.warcraft.object.unit.UnitSound;
import com.evilbird.warcraft.object.unit.UnitType;
import com.evilbird.warcraft.object.unit.building.Building;
import com.evilbird.warcraft.object.unit.combatant.Combatant;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mockito;

import static com.evilbird.warcraft.object.unit.UnitAnimation.Idle;
import static com.evilbird.warcraft.object.unit.UnitSound.Ready;

/**
 * Instances of this unit test validate the {@link DeathAction} class.
 *
 * @author Blair Butterworth
 */
public class DeathActionTest extends GameTestCase
{
    private DeathAction action;
    private EventQueue events;
    private WarcraftPreferences preferences;

    @Before
    public void setup() {
        super.setup();
        preferences = Mockito.mock(WarcraftPreferences.class);
        events = new EventQueue();
        action = new DeathAction(events, objectFactory, preferences);
    }

    @Test
    public void buildingTest() {
        Building item = TestBuildings.newTestBuilding("barracks", UnitType.Barracks);
        Player parent = (Player)item.getParent();

        parent.clearObjects();
        parent.addObject(item);
        action.setSubject(item);

        item.setAnimation(Idle);
        item.setSound(Ready, 1);
        item.setSelected(true);
        item.setSelectable(true);

        action.act(0.1f);
        Assert.assertEquals(UnitAnimation.Death, item.getAnimation());
        Assert.assertEquals(UnitSound.Die, item.getSound());
        Assert.assertFalse(item.getSelected());
        Assert.assertFalse(item.getSelectable());

        action.act(1);
        Assert.assertTrue(parent.containsObject(item));

        action.act(30);
        Assert.assertFalse(parent.containsObject(item));
    }

    @Test
    public void meleeCombatantTest() {
        Combatant item = TestCombatants.newTestCombatant("footman");
        Player parent = (Player)item.getParent();

        parent.clearObjects();
        parent.addObject(item);
        action.setSubject(item);

        item.setAnimation(Idle);
        item.setSound(Ready, 1);
        item.setSelected(true);
        item.setSelectable(true);

        action.act(0.1f);
        Assert.assertEquals(UnitAnimation.Death, item.getAnimation());
        Assert.assertEquals(UnitSound.Die, item.getSound());
        Assert.assertFalse(item.getSelected());
        Assert.assertFalse(item.getSelectable());

        action.act(1);
        Assert.assertEquals(UnitAnimation.Decompose, item.getAnimation());
        Assert.assertTrue(parent.containsObject(item));

        action.act(30);
        Assert.assertFalse(parent.containsObject(item));
    }

    @Test
    public void resetTest() {
        meleeCombatantTest();
        action.reset();
        meleeCombatantTest();
    }
}