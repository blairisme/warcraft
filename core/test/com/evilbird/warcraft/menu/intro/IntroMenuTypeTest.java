/*
 * Copyright (c) 2019, Blair Butterworth
 *
 * This work is licensed under the MIT License. To view a copy of this
 * license, visit
 *
 *        https://opensource.org/licenses/MIT
 */

package com.evilbird.warcraft.menu.intro;

import org.junit.Assert;
import org.junit.Test;

/**
 * Instances of this unit test validate logic in the {@link IntroMenuType}
 * class.
 *
 * @author Blair Butterworth
 */
public class IntroMenuTypeTest
{
    @Test
    public void isHumanTest() {
        Assert.assertTrue(IntroMenuType.Human1.isHuman());
        Assert.assertTrue(IntroMenuType.Human14.isHuman());
        Assert.assertFalse(IntroMenuType.Orc1.isHuman());
        Assert.assertFalse(IntroMenuType.Orc14.isHuman());
    }

    @Test
    public void getSequenceIndexTest() {
        Assert.assertEquals(1, IntroMenuType.Human1.getIndex());
        Assert.assertEquals(3, IntroMenuType.Human3.getIndex());
        Assert.assertEquals(1, IntroMenuType.Orc1.getIndex());
        Assert.assertEquals(11, IntroMenuType.Orc11.getIndex());
    }
}