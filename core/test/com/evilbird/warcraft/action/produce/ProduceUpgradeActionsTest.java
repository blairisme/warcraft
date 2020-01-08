/*
 * Copyright (c) 2020, Blair Butterworth
 *
 * This Source Code Form is subject to the terms of the Mozilla Public
 * License, v. 2.0. If a copy of the MPL was not distributed with this
 * file, You can obtain one at http://mozilla.org/MPL/2.0/.
 */

package com.evilbird.warcraft.action.produce;

import com.evilbird.warcraft.data.upgrade.Upgrade;
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