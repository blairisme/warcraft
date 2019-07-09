/*
 * Copyright (c) 2019, Blair Butterworth
 *
 * This work is licensed under the MIT License. To view a copy of this
 * license, visit
 *
 *        https://opensource.org/licenses/MIT
 */

package com.evilbird.warcraft.action.placeholder;

import com.evilbird.warcraft.item.ui.placement.PlaceholderType;
import org.junit.Assert;
import org.junit.Test;

/**
 * Instances of this unit test validate the {@link PlaceholderActions} class.
 *
 * @author Blair Butterworth
 */
public class PlaceholderActionsTest
{
    @Test
    public void getPlaceholderTest() {
        for (PlaceholderActions action: PlaceholderActions.values()) {
            if (action != PlaceholderActions.PlaceholderCancel && action != PlaceholderActions.PlaceholderMove) {
                Assert.assertNotNull(action.getPlaceholder());
            }
        }
    }

    @Test(expected = IllegalArgumentException.class)
    public void getPlaceholderInvalidTest() {
        PlaceholderActions.PlaceholderCancel.getPlaceholder();
    }

    @Test
    public void forPlaceholderTest() {
        for (PlaceholderType placeholderType: PlaceholderType.values()) {
            PlaceholderActions placeholderAction = PlaceholderActions.forPlaceholder(placeholderType);
            Assert.assertNotNull(placeholderAction);
            Assert.assertEquals(placeholderType, placeholderAction.getPlaceholder());
        }
    }
}