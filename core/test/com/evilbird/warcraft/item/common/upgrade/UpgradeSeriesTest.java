/*
 * Copyright (c) 2019, Blair Butterworth
 *
 * This work is licensed under the MIT License. To view a copy of this
 * license, visit
 *
 *        https://opensource.org/licenses/MIT
 */

package com.evilbird.warcraft.item.common.upgrade;

import org.junit.Assert;
import org.junit.Test;

import java.util.List;
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