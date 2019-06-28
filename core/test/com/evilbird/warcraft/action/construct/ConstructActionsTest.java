/*
 * Copyright (c) 2019, Blair Butterworth
 *
 * This work is licensed under the MIT License. To view a copy of this
 * license, visit
 *
 *        https://opensource.org/licenses/MIT
 */

package com.evilbird.warcraft.action.construct;

import org.junit.Assert;
import org.junit.Test;

import java.util.EnumSet;

/**
 * Instances of this unit test validate the {@link ConstructActions} class.
 *
 * @author Blair Butterworth
 */
public class ConstructActionsTest
{
    @Test
    public void getProductTest() {
        EnumSet<ConstructActions> actions = EnumSet.allOf(ConstructActions.class);
        for (ConstructActions action: actions) {
            Assert.assertNotNull(action.getProduct());
        }
    }
}