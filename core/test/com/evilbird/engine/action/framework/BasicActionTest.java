/*
 * Blair Butterworth (c) 2019
 *
 * This work is licensed under the MIT License. To view a copy of this
 * license, visit
 *
 *      https://opensource.org/licenses/MIT
 */

package com.evilbird.engine.action.framework;

import com.badlogic.gdx.utils.Pool;
import com.evilbird.engine.object.GameObject;
import com.evilbird.test.data.action.TestBasicAction;
import com.evilbird.test.data.action.TestBasicActions;
import com.evilbird.test.verifier.EqualityVerifier;
import org.junit.Before;
import org.junit.Test;

/**
 * Instances of this unit test validate the {@link BasicAction} class.
 *
 * @author Blair Butterworth
 */
public class BasicActionTest
{
    private BasicAction action;

    @Before
    public void setup() {
        action = TestBasicActions.newBasicAction();
    }

    @Test
    public void equalsTest() {
        EqualityVerifier.forClass(TestBasicAction.class)
            .withMockedTransientFields(GameObject.class)
            .withMockedType(Pool.class)
            .excludeTransientFields()
            .verify();
    }
}