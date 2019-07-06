/*
 * Copyright (c) 2019, Blair Butterworth
 *
 * This work is licensed under the MIT License. To view a copy of this
 * license, visit
 *
 *        https://opensource.org/licenses/MIT
 */

package com.evilbird.warcraft.item.ui.hud.control.common;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.scenes.scene2d.utils.Drawable;
import com.evilbird.test.data.device.TestTextures;
import com.evilbird.warcraft.item.ui.hud.control.ControlPaneFactory;
import com.evilbird.warcraft.item.unit.UnitType;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

/**
 * Instances of this unit test validate the {@link ControlPaneFactory} class.
 *
 * @author Blair Butterworth
 */
public class IconSetTest
{
    private IconSet iconSet;

    @Before
    public void setup() {
        Texture texture = TestTextures.newTextureMock();
        iconSet = new IconSet(texture);
    }

    @Test
    public void getByUnitTypeTest() {
        Drawable icon = iconSet.get(UnitType.Footman);
        Assert.assertNotNull(icon);
    }
}