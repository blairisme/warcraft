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
import com.evilbird.warcraft.state.WarcraftCampaign;
import org.junit.Assert;
import org.junit.Test;

/**
 * Instances of this unit test validate logic in the {@link WarcraftCampaign}
 * class.
 *
 * @author Blair Butterworth
 */
public class WarcraftCampaignTest
{
    @Test
    public void getSequenceIndexTest() {
        Assert.assertEquals(1, WarcraftCampaign.Human1.getIndex());
        Assert.assertEquals(3, WarcraftCampaign.Human3.getIndex());
        Assert.assertEquals(1, WarcraftCampaign.Orc1.getIndex());
        Assert.assertEquals(11, WarcraftCampaign.Orc11.getIndex());
    }

    @Test
    public void getIntroductionMenuTest() {
        Assert.assertEquals(IntroMenuType.Human1, WarcraftCampaign.Human1.getIntroductionMenu());
        Assert.assertEquals(IntroMenuType.Human14, WarcraftCampaign.Human14.getIntroductionMenu());
        Assert.assertEquals(IntroMenuType.Orc1, WarcraftCampaign.Orc1.getIntroductionMenu());
        Assert.assertEquals(IntroMenuType.Orc14, WarcraftCampaign.Orc14.getIntroductionMenu());
    }

    @Test
    public void getFilePathTest() {
        Assert.assertEquals("data/levels/human/campaign1.json", WarcraftCampaign.Human1.getFilePath());
        Assert.assertEquals("data/levels/human/campaign14.json", WarcraftCampaign.Human14.getFilePath());
        Assert.assertEquals("data/levels/orc/campaign1.json", WarcraftCampaign.Orc1.getFilePath());
        Assert.assertEquals("data/levels/orc/campaign14.json", WarcraftCampaign.Orc14.getFilePath());
    }

//    @Test
//    public void isHumanTest() {
//        Assert.assertTrue(WarcraftCampaign.Human1.isHuman());
//        Assert.assertTrue(WarcraftCampaign.Human14.isHuman());
//        Assert.assertFalse(WarcraftCampaign.Orc1.isHuman());
//        Assert.assertFalse(WarcraftCampaign.Orc14.isHuman());
//    }
//
//    @Test
//    public void isOrcTest() {
//        Assert.assertTrue(WarcraftCampaign.Orc1.isOrc());
//        Assert.assertTrue(WarcraftCampaign.Orc14.isOrc());
//        Assert.assertFalse(WarcraftCampaign.Human1.isOrc());
//        Assert.assertFalse(WarcraftCampaign.Human14.isOrc());
//    }
}