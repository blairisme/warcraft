/*
 * Copyright (c) 2019, Blair Butterworth
 *
 * This work is licensed under the MIT License. To view a copy of this
 * license, visit
 *
 *        https://opensource.org/licenses/MIT
 */

package com.evilbird.warcraft.action.selector;

import com.evilbird.warcraft.object.selector.SelectorType;
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
    public void getSelectorTest() {
        for (SelectorActions action: SelectorActions.values()) {
            if (action.isShowAction()) {
                Assert.assertNotNull(action.getSelector());
            }
        }
    }

    @Test(expected = IllegalArgumentException.class)
    public void getSelectorInvalidTest() {
        SelectorActions.HideSelector.getSelector();
    }

    @Test
    public void forSelector() {
        for (SelectorType buildingSelectorType : SelectorType.values()) {
            SelectorActions placeholderAction = SelectorActions.forSelector(buildingSelectorType);
            Assert.assertNotNull(placeholderAction);
            Assert.assertEquals(buildingSelectorType, placeholderAction.getSelector());
        }
    }
}