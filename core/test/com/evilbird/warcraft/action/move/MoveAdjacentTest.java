/*
 * Copyright (c) 2019, Blair Butterworth
 *
 * This work is licensed under the MIT License. To view a copy of this
 * license, visit
 *
 *        https://opensource.org/licenses/MIT
 */

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
import com.evilbird.engine.action.common.ActionRecipient;
import com.evilbird.engine.item.Item;
import com.evilbird.test.data.item.TestBuildings;
import com.evilbird.test.data.item.TestCombatants;
import com.evilbird.test.testcase.GameTestCase;
import com.evilbird.test.verifier.EqualityVerifier;
import com.evilbird.warcraft.item.unit.building.Building;
import com.evilbird.warcraft.item.unit.combatant.Combatant;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

/**
 * Instances of this unit test validate the {@link MoveAdjacent} class.
 *
 * @author Blair Butterworth
 */
public class MoveAdjacentTest extends GameTestCase
{
    private MoveAdjacent action;
    private Combatant item;
    private Building target;

    @Before
    public void setup() {
        item = TestCombatants.newTestCombatant("item");
        target = TestBuildings.newTestBuilding("target");

        action = new MoveAdjacent(ActionRecipient.Subject, ActionRecipient.Target);
        action.setItem(item);
        action.setTarget(target);
    }

    @Test
    public void actTest() {
        item.setSize(32, 32);
        item.setPosition(1024, 1024);

        target.setSize(64, 64);
        target.setPosition(128, 128);

        Assert.assertTrue(action.act(1));
        Assert.assertEquals(new Vector2(96, 96), item.getPosition());
    }

    @Test
    public void equalsTest() {
        EqualityVerifier.forClass(MoveAdjacent.class)
            .withMockedTransientFields(Item.class)
            .excludeTransientFields()
            .verify();
    }
}