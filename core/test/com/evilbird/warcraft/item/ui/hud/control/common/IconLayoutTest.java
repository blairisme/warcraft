/*
 * Copyright (c) 2019, Blair Butterworth
 *
 * This work is licensed under the MIT License. To view a copy of this
 * license, visit
 *
 *        https://opensource.org/licenses/MIT
 */

package com.evilbird.warcraft.item.ui.hud.control.common;

import com.badlogic.gdx.math.GridPoint2;
import com.evilbird.warcraft.item.unit.UnitType;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

/**
 * Instances of this unit test validate the {@link IconLayout} class.
 *
 * @author Blair Butterworth
 */
public class IconLayoutTest
{
    private IconLayout layout;

    @Before
    public void setup() {
        layout = new IconLayout();
    }

    @Test
    public void getLocationTest() {
        GridPoint2 expected = new GridPoint2(92, 0);
        GridPoint2 actual = layout.getLocation(UnitType.Footman);
        Assert.assertEquals(expected, actual);
    }

    @Test
    public void getUnitTypeLocationTest() {
        for (UnitType type: UnitType.values()) {
            if ((type.isHuman() || type.isOrc()) && !type.isSpecial()) {
                GridPoint2 location = layout.getLocation(type);
                Assert.assertNotNull("Icon missing for: " + type, location);
            }
        }
    }
}
