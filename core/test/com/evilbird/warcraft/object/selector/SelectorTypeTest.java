/*
 * Copyright (c) 2020, Blair Butterworth
 *
 * This Source Code Form is subject to the terms of the Mozilla Public
 * License, v. 2.0. If a copy of the MPL was not distributed with this
 * file, You can obtain one at http://mozilla.org/MPL/2.0/.
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