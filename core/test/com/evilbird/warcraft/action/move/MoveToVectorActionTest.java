/*
 * Blair Butterworth (c) 2019
 *
 * This work is licensed under the MIT License. To view a copy of this
 * license, visit
 *
 *      https://opensource.org/licenses/MIT
 */

package com.evilbird.warcraft.action.move;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.math.Vector2;
import com.evilbird.engine.device.UserInput;
import com.evilbird.engine.events.EventQueue;
import com.evilbird.engine.object.GameObjectContainer;
import com.evilbird.test.data.item.TestCombatants;
import com.evilbird.test.testcase.GameTestCase;
import com.evilbird.warcraft.object.data.player.Player;
import com.evilbird.warcraft.object.unit.combatant.Combatant;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mockito;

import static com.evilbird.engine.device.UserInputType.Action;
import static com.evilbird.test.data.item.TestItemRoots.newTestRoot;
import static com.evilbird.test.data.item.TestPlayers.newTestPlayer;

/**
 * Instances of this unit test validate the {@link MoveToVectorAction} class.
 *
 * @author Blair Butterworth
 */
public class MoveToVectorActionTest extends GameTestCase
{
    private MoveToVectorAction action;
    private Player player;
    private GameObjectContainer root;
    private Combatant item;
    private Combatant target;

    @Before
    public void setup() {
        root = newTestRoot("root");
        player = newTestPlayer("parent");

        item = TestCombatants.newTestCombatant("moved");
        target = TestCombatants.newTestCombatant("destination");

        action = new MoveToVectorAction(Mockito.mock(EventQueue.class));
        action.setSubject(item);
        action.setTarget(target);
    }

    @Test
    public void actTest() {
        item.setMovementSpeed(10);
        item.setPosition(0, 0);
        action.setCause(new UserInput(Action, new Vector2(40, Gdx.graphics.getHeight() - 1), 1));

        Assert.assertFalse(action.act(1));

        Assert.assertFalse(action.act(1));
        Assert.assertEquals(new Vector2(0, 0), item.getPosition());

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