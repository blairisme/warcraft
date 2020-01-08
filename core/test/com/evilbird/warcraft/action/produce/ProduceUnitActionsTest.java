/*
 * Copyright (c) 2020, Blair Butterworth
 *
 * This Source Code Form is subject to the terms of the Mozilla Public
 * License, v. 2.0. If a copy of the MPL was not distributed with this
 * file, You can obtain one at http://mozilla.org/MPL/2.0/.
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