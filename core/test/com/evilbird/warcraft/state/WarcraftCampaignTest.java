/*
 * Copyright (c) 2020, Blair Butterworth
 *
 * This Source Code Form is subject to the terms of the Mozilla Public
 * License, v. 2.0. If a copy of the MPL was not distributed with this
 * file, You can obtain one at http://mozilla.org/MPL/2.0/.
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

    @Test
    public void getNextStateTest() {
        Assert.assertEquals(WarcraftCampaign.Human2, WarcraftCampaign.Human1.getNextState());
        Assert.assertNull(WarcraftCampaign.Human14.getNextState());
        Assert.assertEquals(WarcraftCampaign.Orc2, WarcraftCampaign.Orc1.getNextState());
        Assert.assertNull(WarcraftCampaign.Orc14.getNextState());
    }
}