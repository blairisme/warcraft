/*
 * Blair Butterworth (c) 2019
 *
 * This work is licensed under the MIT License. To view a copy of this
 * license, visit
 *
 *      https://opensource.org/licenses/MIT
 */

package com.evilbird.engine.action.common;

import com.evilbird.engine.object.GameObject;
import com.evilbird.engine.object.AnimatedObject;
import com.evilbird.test.data.item.TestCombatants;
import com.evilbird.test.testcase.GameTestCase;
import com.evilbird.test.verifier.EqualityVerifier;
import com.evilbird.warcraft.object.unit.UnitAnimation;
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
    private AnimatedObject item;

    @Before
    public void setup() {
        item = TestCombatants.newTestCombatant("animateactiontest");
        item.setAnimation(UnitAnimation.Idle);

        action = new AnimateAction(UnitAnimation.Build);
        action.setItem(item);
    }

    @Test
    public void actTest() {
        Assert.assertTrue(action.act(1));
        Assert.assertEquals(UnitAnimation.Build, item.getAnimation());
    }

    @Test
    public void equalsTest() {
        EqualityVerifier.forClass(AnimateAction.class)
                .withMockedTransientFields(GameObject.class)
                .excludeTransientFields()
                .verify();
    }
}