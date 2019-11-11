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
 * Instances of this unit test validate the {@link DisableAction} class.
 *
 * @author Blair Butterworth
 */
public class DisableActionTest
{
    private DisableAction action;

    @Before
    public void setup() {
        action = new DisableAction(true);
        action.setItem(TestItems.newItem("disableaction"));
    }

    @Test
    public void equalsTest() {
        EqualityVerifier.forClass(DisableAction.class)
            .withMockedTransientFields(GameObject.class)
            .excludeTransientFields()
            .verify();
    }
}