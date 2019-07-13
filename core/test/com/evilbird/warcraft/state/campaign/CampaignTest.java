/*
 * Copyright (c) 2019, Blair Butterworth
 *
 * This work is licensed under the MIT License. To view a copy of this
 * license, visit
 *
 *        https://opensource.org/licenses/MIT
 */

package com.evilbird.warcraft.state.campaign;

import com.evilbird.warcraft.menu.intro.IntroMenuType;
import org.junit.Assert;
import org.junit.Test;

/**
 * Instances of this unit test validate logic in the {@link Campaign}
 * class.
 *
 * @author Blair Butterworth
 */
public class CampaignTest
{
    @Test
    public void getSequenceIndexTest() {
        Assert.assertEquals(1, Campaign.Human1.getIndex());
        Assert.assertEquals(3, Campaign.Human3.getIndex());
        Assert.assertEquals(1, Campaign.Orc1.getIndex());
        Assert.assertEquals(11, Campaign.Orc11.getIndex());
    }

    @Test
    public void getIntroductionMenuTest() {
        Assert.assertEquals(IntroMenuType.Human1, Campaign.Human1.getIntroductionMenu());
        Assert.assertEquals(IntroMenuType.Human14, Campaign.Human14.getIntroductionMenu());
        Assert.assertEquals(IntroMenuType.Orc1, Campaign.Orc1.getIntroductionMenu());
        Assert.assertEquals(IntroMenuType.Orc14, Campaign.Orc14.getIntroductionMenu());
    }

    @Test
    public void getFilePathTest() {
        Assert.assertEquals("data/levels/human/campaign1.json", Campaign.Human1.getFilePath());
        Assert.assertEquals("data/levels/human/campaign14.json", Campaign.Human14.getFilePath());
        Assert.assertEquals("data/levels/orc/campaign1.json", Campaign.Orc1.getFilePath());
        Assert.assertEquals("data/levels/orc/campaign14.json", Campaign.Orc14.getFilePath());
    }

    @Test
    public void isHumanTest() {
        Assert.assertTrue(Campaign.Human1.isHuman());
        Assert.assertTrue(Campaign.Human14.isHuman());
        Assert.assertFalse(Campaign.Orc1.isHuman());
        Assert.assertFalse(Campaign.Orc14.isHuman());
    }

    @Test
    public void isOrcTest() {
        Assert.assertTrue(Campaign.Orc1.isOrc());
        Assert.assertTrue(Campaign.Orc14.isOrc());
        Assert.assertFalse(Campaign.Human1.isOrc());
        Assert.assertFalse(Campaign.Human14.isOrc());
    }
}