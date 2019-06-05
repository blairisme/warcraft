/*
 * Blair Butterworth (c) 2019
 *
 * This work is licensed under the MIT License. To view a copy of this
 * license, visit
 *
 *      https://opensource.org/licenses/MIT
 */

package com.evilbird.warcraft.action.move;

import com.badlogic.gdx.math.Vector2;
import com.evilbird.engine.events.EventQueue;
import com.evilbird.test.data.item.TestCombatants;
import com.evilbird.test.testcase.GameTestCase;
import com.evilbird.warcraft.item.unit.combatant.Combatant;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mockito;

/**
 * Instances of this unit test validate the {@link MoveToItemAction} class.
 *
 * @author Blair Butterworth
 */
public class MoveToItemActionTest extends GameTestCase
{
    private MoveToItemAction action;
    private Combatant item;
    private Combatant target;

    @Before
    public void setup() {
        item = TestCombatants.newTestCombatant("moved");
        target = TestCombatants.newTestCombatant("destination");

        action = new MoveToItemAction();
        action.setItem(item);
        action.setTarget(target);
        action.setObserver(Mockito.mock(EventQueue.class));
    }

    @Test
    public void actTest() {
        item.setMovementSpeed(10);
        item.setPosition(0, 0);

        target.setPosition(32, 0);
        target.getRoot().getSpatialGraph().addOccupants(target);

        Assert.assertFalse(action.act(1));
        Assert.assertEquals(new Vector2(10, 0), item.getPosition());

        Assert.assertFalse(action.act(1));
        Assert.assertEquals(new Vector2(20, 0), item.getPosition());

        Assert.assertFalse(action.act(1));
        Assert.assertEquals(new Vector2(30, 0), item.getPosition());

        Assert.assertFalse(action.act(1));
        Assert.assertEquals(new Vector2(32, 0), item.getPosition());

        Assert.assertTrue(action.act(1));
    }
}