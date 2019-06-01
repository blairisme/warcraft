/*
 * Blair Butterworth (c) 2019
 *
 * This work is licensed under the MIT License. To view a copy of this
 * license, visit
 *
 *      https://opensource.org/licenses/MIT
 */

package com.evilbird.engine.action.common;

import com.evilbird.engine.item.Item;
import com.evilbird.engine.item.specialized.AnimatedItem;
import com.evilbird.test.data.item.TestCombatants;
import com.evilbird.test.testcase.GameTestCase;
import com.evilbird.test.verifier.EqualityVerifier;
import com.evilbird.warcraft.item.unit.UnitAnimation;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

/**
 * Instances of this unit test validate the {@link AnimateAction} class.
 *
 * @author Blair Butterworth
 */
public class AnimateActionTest extends GameTestCase
{
    private AnimateAction action;
    private AnimatedItem item;

    @Before
    public void setup() {
        item = TestCombatants.newTestCombatant("animateactiontest");
        item.setAnimation(UnitAnimation.Idle);

        action = new AnimateAction(UnitAnimation.MeleeAttack);
        action.setItem(item);
    }

    @Test
    public void actTest() {
        Assert.assertTrue(action.act(1));
        Assert.assertEquals(UnitAnimation.MeleeAttack, item.getAnimation());
    }

    @Test
    public void equalsTest() {
        EqualityVerifier.forClass(AnimateAction.class)
                .withMockedTransientFields(Item.class)
                .excludeTransientFields()
                .verify();
    }
}