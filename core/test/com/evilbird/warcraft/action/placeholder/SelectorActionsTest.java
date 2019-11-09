/*
 * Copyright (c) 2019, Blair Butterworth
 *
 * This work is licensed under the MIT License. To view a copy of this
 * license, visit
 *
 *        https://opensource.org/licenses/MIT
 */

package com.evilbird.warcraft.action.placeholder;

import com.evilbird.warcraft.action.selector.SelectorActions;
import com.evilbird.warcraft.item.selector.SelectorType;
import org.junit.Assert;
import org.junit.Test;

/**
 * Instances of this unit test validate the {@link SelectorActions} class.
 *
 * @author Blair Butterworth
 */
public class SelectorActionsTest
{
    @Test
    public void getPlaceholderTest() {
        for (SelectorActions action: SelectorActions.values()) {
            if (action != SelectorActions.SelectorCancel && action != SelectorActions.SelectorMove) {
                Assert.assertNotNull(action.getSelector());
            }
        }
    }

    @Test(expected = IllegalArgumentException.class)
    public void getPlaceholderInvalidTest() {
        SelectorActions.SelectorCancel.getSelector();
    }

    @Test
    public void forPlaceholderTest() {
        for (SelectorType buildingSelectorType : SelectorType.values()) {
            SelectorActions placeholderAction = SelectorActions.forSelector(buildingSelectorType);
            Assert.assertNotNull(placeholderAction);
            Assert.assertEquals(buildingSelectorType, placeholderAction.getSelector());
        }
    }
}