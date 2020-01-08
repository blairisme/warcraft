/*
 * Copyright (c) 2020, Blair Butterworth
 *
 * This Source Code Form is subject to the terms of the Mozilla Public
 * License, v. 2.0. If a copy of the MPL was not distributed with this
 * file, You can obtain one at http://mozilla.org/MPL/2.0/.
 */

package com.evilbird.warcraft.action.construct;

import com.evilbird.warcraft.object.unit.UnitType;
import org.junit.Assert;
import org.junit.Test;

/**
 * Instances of this unit test validate the {@link ConstructActions} class.
 *
 * @author Blair Butterworth
 */
public class ConstructActionsTest
{
    @Test
    public void getProductTest() {
        for (ConstructActions action: ConstructActions.values()) {
            Assert.assertNotNull(action.getProduct());
        }
    }

    @Test
    public void forProductTest() {
        for (UnitType unitType: UnitType.values()) {
            if (unitType.isBuilding() && !unitType.isNeutral()) {
                ConstructActions action = ConstructActions.forProduct(unitType);
                Assert.assertNotNull(action);
                Assert.assertEquals(unitType, action.getProduct());
            }
        }
    }
}