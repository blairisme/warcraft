/*
 * Copyright (c) 2019, Blair Butterworth
 *  
 * This work is licensed under the MIT License. To view a copy of this
 * license, visit
 *  
 *        https://opensource.org/licenses/MIT
 */

package com.evilbird.warcraft.state.map;

import com.evilbird.warcraft.state.Level;
import org.junit.Assert;
import org.junit.Test;

/**
 * Instances of this unit test validate logic in the {@link Level}
 * class.
 *
 * @author Blair Butterworth
 */
public class LevelTest
{
    @Test
    public void getSequenceIndexTest() {
        Assert.assertEquals(1, Level.Human1.getIndex());
        Assert.assertEquals(3, Level.Human3.getIndex());
        Assert.assertEquals(1, Level.Orc1.getIndex());
        Assert.assertEquals(11, Level.Orc11.getIndex());
    }

    @Test
    public void getFilePathTest() {
        Assert.assertEquals("data/levels/human/campaign1.tmx", Level.Human1.getFilePath());
        Assert.assertEquals("data/levels/human/campaign14.tmx", Level.Human14.getFilePath());
        Assert.assertEquals("data/levels/orc/campaign1.tmx", Level.Orc1.getFilePath());
        Assert.assertEquals("data/levels/orc/campaign14.tmx", Level.Orc14.getFilePath());
    }

    @Test
    public void isHumanTest() {
        Assert.assertTrue(Level.Human1.isHuman());
        Assert.assertTrue(Level.Human14.isHuman());
        Assert.assertFalse(Level.Orc1.isHuman());
        Assert.assertFalse(Level.Orc14.isHuman());
    }

    @Test
    public void isOrcTest() {
        Assert.assertTrue(Level.Orc1.isOrc());
        Assert.assertTrue(Level.Orc14.isOrc());
        Assert.assertFalse(Level.Human1.isOrc());
        Assert.assertFalse(Level.Human14.isOrc());
    }
}