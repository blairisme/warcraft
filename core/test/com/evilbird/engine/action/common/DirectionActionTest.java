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
import com.evilbird.test.data.item.TestItems;
import com.evilbird.test.verifier.EqualityVerifier;
import org.junit.Before;
import org.junit.Test;

/**
 * Instances of this unit test validate the {@link DirectionAction} class.
 *
 * @author Blair Butterworth
 */
public class DirectionActionTest
{
    private DirectionAction action;

    @Before
    public void setup() {
        action = new DirectionAction();
        action.setItem(TestItems.newItem("directionaction"));
    }

    @Test
    public void equalsTest() {
        EqualityVerifier.forClass(DirectionAction.class)
                .withMockedTransientFields(GameObject.class)
                .excludeTransientFields()
                .verify();
    }
}