/*
 * Copyright (c) 2020, Blair Butterworth
 *
 * This Source Code Form is subject to the terms of the Mozilla Public
 * License, v. 2.0. If a copy of the MPL was not distributed with this
 * file, You can obtain one at http://mozilla.org/MPL/2.0/.
 */

package com.evilbird.warcraft.state;

import org.junit.Assert;
import org.junit.Test;

import static com.evilbird.warcraft.common.WarcraftFaction.Human;
import static com.evilbird.warcraft.common.WarcraftFaction.Orc;

/**
 * Instances of this unit test validate logic in the {@link WarcraftCampaignLevel}
 * class.
 *
 * @author Blair Butterworth
 */
public class WarcraftCampaignLevelTest
{
    @Test
    public void getFactionTest() {
        Assert.assertEquals(Human, WarcraftCampaignLevel.Human1.getFaction());
        Assert.assertEquals(Human, WarcraftCampaignLevel.Human14.getFaction());
        Assert.assertEquals(Orc, WarcraftCampaignLevel.Orc1.getFaction());
        Assert.assertEquals(Orc, WarcraftCampaignLevel.Orc14.getFaction());
    }

    @Test
    public void getFactionNameTest() {
        Assert.assertEquals("human", WarcraftCampaignLevel.Human1.getFactionName());
        Assert.assertEquals("human", WarcraftCampaignLevel.Human14.getFactionName());
        Assert.assertEquals("orc", WarcraftCampaignLevel.Orc1.getFactionName());
        Assert.assertEquals("orc", WarcraftCampaignLevel.Orc14.getFactionName());
    }

    @Test
    public void getFileNameTest() {
        Assert.assertEquals("campaign1.tmx", WarcraftCampaignLevel.Human1.getFileName());
        Assert.assertEquals("campaign14.tmx", WarcraftCampaignLevel.Human14.getFileName());
        Assert.assertEquals("campaign1.tmx", WarcraftCampaignLevel.Orc1.getFileName());
        Assert.assertEquals("campaign14.tmx", WarcraftCampaignLevel.Orc14.getFileName());
    }

    @Test
    public void getIndexTest() {
        Assert.assertEquals(1, WarcraftCampaignLevel.Human1.getIndex());
        Assert.assertEquals(3, WarcraftCampaignLevel.Human3.getIndex());
        Assert.assertEquals(1, WarcraftCampaignLevel.Orc1.getIndex());
        Assert.assertEquals(11, WarcraftCampaignLevel.Orc11.getIndex());
    }
}