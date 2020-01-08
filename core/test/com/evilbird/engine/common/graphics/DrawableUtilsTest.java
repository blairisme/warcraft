/*
 * Copyright (c) 2020, Blair Butterworth
 *
 * This Source Code Form is subject to the terms of the Mozilla Public
 * License, v. 2.0. If a copy of the MPL was not distributed with this
 * file, You can obtain one at http://mozilla.org/MPL/2.0/.
 */

package com.evilbird.engine.common.graphics;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.scenes.scene2d.utils.Drawable;
import com.evilbird.test.testcase.GameTestCase;
import org.junit.Assert;
import org.junit.Test;

/**
 * Instances of this unit test validate logic in the {@link DrawableUtils}
 * class.
 *
 * @author Blair Butterworth
 */
public class DrawableUtilsTest extends GameTestCase
{
    @Test
    public void getDrawableColorTest() {
        Drawable drawable = DrawableUtils.getDrawable(Colours.FOREST_GREEN);
        Assert.assertNotNull(drawable);
    }

    @Test (expected = NullPointerException.class)
    public void getDrawableColorNullTest() {
        DrawableUtils.getDrawable((Color)null);
    }
}