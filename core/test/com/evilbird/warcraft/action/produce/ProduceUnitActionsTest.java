/*
 * Copyright (c) 2019, Blair Butterworth
 *
 * This work is licensed under the MIT License. To view a copy of this
 * license, visit
 *
 *        https://opensource.org/licenses/MIT
 */

package com.evilbird.warcraft.action.produce;

import org.junit.Assert;
import org.junit.Test;

import java.util.EnumSet;

/**
 * Instances of this unit test validate the {@link ProduceUnitActions} class.
 *
 * @author Blair Butterworth
 */
public class ProduceUnitActionsTest
{
    @Test
    public void getProductTest() {
        EnumSet<ProduceUnitActions> actions = EnumSet.allOf(ProduceUnitActions.class);
        for (ProduceUnitActions action: actions) {
            Assert.assertNotNull(action.getProduct());
        }
    }
}