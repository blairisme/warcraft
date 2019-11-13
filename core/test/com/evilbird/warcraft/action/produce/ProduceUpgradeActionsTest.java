/*
 * Copyright (c) 2019, Blair Butterworth
 *
 * This work is licensed under the MIT License. To view a copy of this
 * license, visit
 *
 *        https://opensource.org/licenses/MIT
 */

package com.evilbird.warcraft.action.produce;

import com.evilbird.warcraft.object.common.upgrade.Upgrade;
import org.junit.Assert;
import org.junit.Test;

import java.util.EnumSet;

/**
 * Instances of this unit test validate the {@link ProduceUpgradeActions} class.
 *
 * @author Blair Butterworth
 */
public class ProduceUpgradeActionsTest
{
    @Test
    public void getProductTest() {
        EnumSet<ProduceUpgradeActions> actions = EnumSet.allOf(ProduceUpgradeActions.class);
        for (ProduceUpgradeActions action: actions) {
            Assert.assertNotNull(action.getProduct());
        }
    }

    @Test
    public void forProductTest() {
        for (Upgrade upgrade: Upgrade.values()) {
            if (upgrade != Upgrade.None) {
                Assert.assertNotNull(ProduceUpgradeActions.forProduct(upgrade));
            }
        }
    }

    @Test
    public void forProductCancelTest() {
        for (Upgrade upgrade: Upgrade.values()) {
            if (upgrade != Upgrade.None) {
                Assert.assertNotNull(ProduceUpgradeActions.forProductCancel(upgrade));
            }
        }
    }
}