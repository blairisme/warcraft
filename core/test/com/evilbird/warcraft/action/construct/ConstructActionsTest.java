/*
 * Copyright (c) 2019, Blair Butterworth
 *
 * This work is licensed under the MIT License. To view a copy of this
 * license, visit
 *
 *        https://opensource.org/licenses/MIT
 */

package com.evilbird.warcraft.action.construct;

import com.evilbird.warcraft.item.unit.UnitType;
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
            if (unitType.isBuilding()) {
                ConstructActions action = ConstructActions.forProduct(unitType);
                Assert.assertNotNull(action);
                Assert.assertEquals(unitType, action.getProduct());
            }
        }
    }
}