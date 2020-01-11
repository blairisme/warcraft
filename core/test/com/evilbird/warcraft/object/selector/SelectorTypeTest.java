/*
 * Copyright (c) 2020, Blair Butterworth
 *
 * This Source Code Form is subject to the terms of the Mozilla Public
 * License, v. 2.0. If a copy of the MPL was not distributed with this
 * file, You can obtain one at http://mozilla.org/MPL/2.0/.
 */

package com.evilbird.warcraft.object.selector;

import com.evilbird.warcraft.common.WarcraftFaction;
import com.evilbird.warcraft.object.unit.UnitArchetype;
import com.evilbird.warcraft.object.unit.UnitType;
import org.junit.Assert;
import org.junit.Test;

import static com.evilbird.warcraft.common.WarcraftFaction.Neutral;

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
        for (UnitType type: UnitType.values()) {
            UnitArchetype archetype = type.getArchetype();
            WarcraftFaction faction = type.getFaction();

            if (archetype.isBuilding() && faction != Neutral) {
                SelectorType buildingSelectorType = SelectorType.forBuilding(type);
                Assert.assertNotNull(buildingSelectorType);
                Assert.assertEquals(type, buildingSelectorType.getBuilding());
            }
        }
    }
}