/*
 * Copyright (c) 2019, Blair Butterworth
 *
 * This work is licensed under the MIT License. To view a copy of this
 * license, visit
 *
 *        https://opensource.org/licenses/MIT
 */

package com.evilbird.warcraft.item.ui.placement;

import com.evilbird.warcraft.item.unit.UnitType;
import org.junit.Assert;
import org.junit.Test;

/**
 * Instances of this unit test validate the {@link PlaceholderType} class.
 *
 * @author Blair Butterworth
 */
public class PlaceholderTypeTest
{
    @Test
    public void getBuildingTest() {
        for (PlaceholderType placeholderType: PlaceholderType.values()) {
            Assert.assertNotNull(placeholderType.getBuilding());
        }
    }

    @Test
    public void forBuildingTest() {
        for (UnitType unitType: UnitType.values()) {
            if (unitType.isBuilding()) {
                PlaceholderType placeholderType = PlaceholderType.forBuilding(unitType);
                Assert.assertNotNull(placeholderType);
                Assert.assertEquals(unitType, placeholderType.getBuilding());
            }
        }
    }
}