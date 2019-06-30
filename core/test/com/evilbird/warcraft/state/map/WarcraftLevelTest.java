/*
 * Copyright (c) 2019, Blair Butterworth
 *  
 * This work is licensed under the MIT License. To view a copy of this
 * license, visit
 *  
 *        https://opensource.org/licenses/MIT
 */

package com.evilbird.warcraft.state.map;

import org.junit.Assert;
import org.junit.Test;

/**
 * Instances of this unit test validate logic in the {@link WarcraftLevel}
 * class.
 *
 * @author Blair Butterworth
 */
public class WarcraftLevelTest
{
    @Test
    public void getSequenceIndexTest() {
        Assert.assertEquals(1, WarcraftLevel.Human1.getIndex());
        Assert.assertEquals(3, WarcraftLevel.Human3.getIndex());
        Assert.assertEquals(1, WarcraftLevel.Orc1.getIndex());
        Assert.assertEquals(11, WarcraftLevel.Orc11.getIndex());
    }

    @Test
    public void getFilePathTest() {
        Assert.assertEquals("data/levels/human/campaign1.tmx", WarcraftLevel.Human1.getFilePath());
        Assert.assertEquals("data/levels/human/campaign14.tmx", WarcraftLevel.Human14.getFilePath());
        Assert.assertEquals("data/levels/orc/campaign1.tmx", WarcraftLevel.Orc1.getFilePath());
        Assert.assertEquals("data/levels/orc/campaign14.tmx", WarcraftLevel.Orc14.getFilePath());
    }

    @Test
    public void isHumanTest() {
        Assert.assertTrue(WarcraftLevel.Human1.isHuman());
        Assert.assertTrue(WarcraftLevel.Human14.isHuman());
        Assert.assertFalse(WarcraftLevel.Orc1.isHuman());
        Assert.assertFalse(WarcraftLevel.Orc14.isHuman());
    }

    @Test
    public void isOrcTest() {
        Assert.assertTrue(WarcraftLevel.Orc1.isOrc());
        Assert.assertTrue(WarcraftLevel.Orc14.isOrc());
        Assert.assertFalse(WarcraftLevel.Human1.isOrc());
        Assert.assertFalse(WarcraftLevel.Human14.isOrc());
    }
}