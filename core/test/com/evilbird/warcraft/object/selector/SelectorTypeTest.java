/*
 * Copyright (c) 2019, Blair Butterworth
 *
 * This work is licensed under the MIT License. To view a copy of this
 * license, visit
 *
 *        https://opensource.org/licenses/MIT
 */

package com.evilbird.warcraft.object.selector;

import com.evilbird.warcraft.object.unit.UnitType;
import org.junit.Assert;
import org.junit.Test;

/**
 * Instances of this unit test validate the {@link SelectorType} class.
 *
 * @author Blair Butterworth
 */
public class SelectorTypeTest
{
    @Test
    public void getBuildingTest() {
        for (SelectorType buildingSelectorType : SelectorType.buildingSelectors()) {
            Assert.assertNotNull(buildingSelectorType.getBuilding());
        }
    }

    @Test
    public void forBuildingTest() {
        for (UnitType unitType: UnitType.values()) {
            if (unitType.isBuilding() && !unitType.isNeutral()) {
                SelectorType buildingSelectorType = SelectorType.forBuilding(unitType);
                Assert.assertNotNull(buildingSelectorType);
                Assert.assertEquals(unitType, buildingSelectorType.getBuilding());
            }
        }
    }
}