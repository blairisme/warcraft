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
import com.evilbird.test.data.item.TestItems;
import com.evilbird.test.verifier.EqualityVerifier;
import org.junit.Before;
import org.junit.Test;

/**
 * Instances of this unit test validate the {@link VisibleAction} class.
 *
 * @author Blair Butterworth
 */
public class VisibleActionTest
{
    private VisibleAction action;

    @Before
    public void setup() {
        action = new VisibleAction(true);
        action.setItem(TestItems.newItem("visibleaction"));
    }

    @Test
    public void equalsTest() {
        EqualityVerifier.forClass(VisibleAction.class)
            .withMockedTransientFields(Item.class)
            .excludeTransientFields()
            .verify();
    }
}