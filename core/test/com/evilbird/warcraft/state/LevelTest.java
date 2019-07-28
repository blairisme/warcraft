/*
 * Copyright (c) 2019, Blair Butterworth
 *  
 * This work is licensed under the MIT License. To view a copy of this
 * license, visit
 *  
 *        https://opensource.org/licenses/MIT
 */

package com.evilbird.warcraft.state;

import org.junit.Assert;
import org.junit.Test;

import static com.evilbird.warcraft.common.WarcraftFaction.Human;
import static com.evilbird.warcraft.common.WarcraftFaction.Orc;

/**
 * Instances of this unit test validate logic in the {@link Level}
 * class.
 *
 * @author Blair Butterworth
 */
public class LevelTest
{
    @Test
    public void getFactionTest() {
        Assert.assertEquals(Human, Level.Human1.getFaction());
        Assert.assertEquals(Human, Level.Human14.getFaction());
        Assert.assertEquals(Orc, Level.Orc1.getFaction());
        Assert.assertEquals(Orc, Level.Orc14.getFaction());
    }

    @Test
    public void getFactionNameTest() {
        Assert.assertEquals("human", Level.Human1.getFactionName());
        Assert.assertEquals("human", Level.Human14.getFactionName());
        Assert.assertEquals("orc", Level.Orc1.getFactionName());
        Assert.assertEquals("orc", Level.Orc14.getFactionName());
    }

    @Test
    public void getFileNameTest() {
        Assert.assertEquals("campaign1.tmx", Level.Human1.getFileName());
        Assert.assertEquals("campaign14.tmx", Level.Human14.getFileName());
        Assert.assertEquals("campaign1.tmx", Level.Orc1.getFileName());
        Assert.assertEquals("campaign14.tmx", Level.Orc14.getFileName());
    }

    @Test
    public void getIndexTest() {
        Assert.assertEquals(1, Level.Human1.getIndex());
        Assert.assertEquals(3, Level.Human3.getIndex());
        Assert.assertEquals(1, Level.Orc1.getIndex());
        Assert.assertEquals(11, Level.Orc11.getIndex());
    }
}