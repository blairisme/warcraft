/*
 * Blair Butterworth (c) 2019
 *
 * This work is licensed under the MIT License. To view a copy of this
 * license, visit
 *
 *      https://opensource.org/licenses/MIT
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