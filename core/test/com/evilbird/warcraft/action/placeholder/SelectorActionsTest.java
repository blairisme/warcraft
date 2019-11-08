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
import com.evilbird.warcraft.item.ui.placement.PlaceholderType;
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
                Assert.assertNotNull(action.getPlaceholder());
            }
        }
    }

    @Test(expected = IllegalArgumentException.class)
    public void getPlaceholderInvalidTest() {
        SelectorActions.SelectorCancel.getPlaceholder();
    }

    @Test
    public void forPlaceholderTest() {
        for (PlaceholderType placeholderType: PlaceholderType.values()) {
            SelectorActions placeholderAction = SelectorActions.forPlaceholder(placeholderType);
            Assert.assertNotNull(placeholderAction);
            Assert.assertEquals(placeholderType, placeholderAction.getPlaceholder());
        }
    }
}