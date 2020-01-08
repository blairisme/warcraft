/*
 * Copyright (c) 2020, Blair Butterworth
 *
 * This Source Code Form is subject to the terms of the Mozilla Public
 * License, v. 2.0. If a copy of the MPL was not distributed with this
 * file, You can obtain one at http://mozilla.org/MPL/2.0/.
 */

package com.evilbird.warcraft.menu.intro;

import com.evilbird.warcraft.state.WarcraftCampaign;
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
    public void getSequenceIndexTest() {
        Assert.assertEquals(1, IntroMenuType.Human1.getIndex());
        Assert.assertEquals(3, IntroMenuType.Human3.getIndex());
        Assert.assertEquals(1, IntroMenuType.Orc1.getIndex());
        Assert.assertEquals(11, IntroMenuType.Orc11.getIndex());
    }

    @Test
    public void getCampaignTest() {
        for (IntroMenuType type: IntroMenuType.values()) {
            WarcraftCampaign campaign = type.getCampaign();
            Assert.assertNotNull(campaign);
        }
    }
}