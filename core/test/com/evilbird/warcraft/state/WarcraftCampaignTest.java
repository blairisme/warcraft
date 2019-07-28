/*
 * Copyright (c) 2019, Blair Butterworth
 *
 * This work is licensed under the MIT License. To view a copy of this
 * license, visit
 *
 *        https://opensource.org/licenses/MIT
 */

package com.evilbird.warcraft.state;

import com.evilbird.warcraft.menu.intro.IntroMenuType;
import org.junit.Assert;
import org.junit.Test;

import static com.evilbird.warcraft.common.WarcraftFaction.Human;
import static com.evilbird.warcraft.common.WarcraftFaction.Orc;

/**
 * Instances of this unit test validate logic in the {@link WarcraftCampaign}
 * class.
 *
 * @author Blair Butterworth
 */
public class WarcraftCampaignTest
{
    @Test
    public void getFactionTest() {
        Assert.assertEquals(Human, WarcraftCampaign.Human1.getFaction());
        Assert.assertEquals(Human, WarcraftCampaign.Human14.getFaction());
        Assert.assertEquals(Orc, WarcraftCampaign.Orc1.getFaction());
        Assert.assertEquals(Orc, WarcraftCampaign.Orc14.getFaction());
    }

    @Test
    public void getFactionNameTest() {
        Assert.assertEquals("human", WarcraftCampaign.Human1.getFactionName());
        Assert.assertEquals("human", WarcraftCampaign.Human14.getFactionName());
        Assert.assertEquals("orc", WarcraftCampaign.Orc1.getFactionName());
        Assert.assertEquals("orc", WarcraftCampaign.Orc14.getFactionName());
    }

    @Test
    public void getFileNameTest() {
        Assert.assertEquals("campaign1.json", WarcraftCampaign.Human1.getFileName());
        Assert.assertEquals("campaign14.json", WarcraftCampaign.Human14.getFileName());
        Assert.assertEquals("campaign1.json", WarcraftCampaign.Orc1.getFileName());
        Assert.assertEquals("campaign14.json", WarcraftCampaign.Orc14.getFileName());
    }

    @Test
    public void getIndexTest() {
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
}