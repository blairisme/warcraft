/*
 * Copyright (c) 2020, Blair Butterworth
 *
 * This Source Code Form is subject to the terms of the Mozilla Public
 * License, v. 2.0. If a copy of the MPL was not distributed with this
 * file, You can obtain one at http://mozilla.org/MPL/2.0/.
 */

package com.evilbird.warcraft.object.common.value;

import com.evilbird.warcraft.data.upgrade.Upgrade;
import com.evilbird.warcraft.data.upgrade.UpgradeSeries;
import org.junit.Assert;
import org.junit.Test;

import java.util.Set;

/**
 * Instances of this unit test validate the {@link UpgradeSeries} class.
 *
 * @author Blair Butterworth
 */
public class UpgradeSeriesTest
{
    @Test
    public void getUpgradesTest() {
        for (UpgradeSeries series: UpgradeSeries.values()) {
            if (series != UpgradeSeries.None) {
                Set<Upgrade> upgrades = series.getUpgrades();
                Assert.assertNotNull(upgrades);
                Assert.assertFalse(upgrades.isEmpty());
                Assert.assertNotNull(upgrades.iterator().next());
            }
        }
    }
}