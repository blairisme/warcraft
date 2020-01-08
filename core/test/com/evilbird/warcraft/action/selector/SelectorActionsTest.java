/*
 * Copyright (c) 2020, Blair Butterworth
 *
 * This Source Code Form is subject to the terms of the Mozilla Public
 * License, v. 2.0. If a copy of the MPL was not distributed with this
 * file, You can obtain one at http://mozilla.org/MPL/2.0/.
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