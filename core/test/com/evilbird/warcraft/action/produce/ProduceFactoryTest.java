/*
 * Copyright (c) 2020, Blair Butterworth
 *
 * This Source Code Form is subject to the terms of the Mozilla Public
 * License, v. 2.0. If a copy of the MPL was not distributed with this
 * file, You can obtain one at http://mozilla.org/MPL/2.0/.
 */

package com.evilbird.warcraft.action.produce;

import com.evilbird.engine.action.ActionIdentifier;
import com.evilbird.engine.common.inject.InjectedPool;
import com.evilbird.test.testcase.ActionFactoryTestCase;
import com.evilbird.test.utils.MockInjectedPool;
import com.evilbird.warcraft.action.ActionProvider;

/**
 * Instances of this unit test validate the {@link ProduceFactory} class.
 *
 * @author Blair Butterworth
 */
public class ProduceFactoryTest extends ActionFactoryTestCase
{
    @Override
    protected ActionProvider newFactory() {
        InjectedPool<ProduceUnit> unitPool = new MockInjectedPool<>(ProduceUnit.class);
        InjectedPool<ProduceUpgrade> upgradePool = new MockInjectedPool<>(ProduceUpgrade.class);
        InjectedPool<ProduceUpgradeType> typePool = new MockInjectedPool<>(ProduceUpgradeType.class);
        InjectedPool<ProduceUnitCancel> cancelUnitPool = new MockInjectedPool<>(ProduceUnitCancel.class);
        InjectedPool<ProduceUpgradeCancel> cancelUpgradePool = new MockInjectedPool<>(ProduceUpgradeCancel.class);
        return new ProduceFactory(unitPool, upgradePool, typePool, cancelUnitPool, cancelUpgradePool);
    }

    @Override
    protected ActionIdentifier[] getIdentifiers() {
        return ProduceUnitActions.values();
    }
}