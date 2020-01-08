/*
 * Copyright (c) 2020, Blair Butterworth
 *
 * This Source Code Form is subject to the terms of the Mozilla Public
 * License, v. 2.0. If a copy of the MPL was not distributed with this
 * file, You can obtain one at http://mozilla.org/MPL/2.0/.
 */

package com.evilbird.warcraft.action.construct;

import com.evilbird.engine.action.ActionIdentifier;
import com.evilbird.engine.common.inject.InjectedPool;
import com.evilbird.test.testcase.ActionFactoryTestCase;
import com.evilbird.test.utils.MockInjectedPool;
import com.evilbird.warcraft.action.ActionProvider;

/**
 * Instances of this unit test validate the {@link ConstructFactory} class.
 *
 * @author Blair Butterworth
 */
public class ConstructFactoryTest extends ActionFactoryTestCase
{
    @Override
    protected ActionProvider newFactory() {
        InjectedPool<ConstructSequence> constructPool = new MockInjectedPool<>(ConstructSequence.class);
        InjectedPool<ConstructCancel> cancelPool = new MockInjectedPool<>(ConstructCancel.class);
        InjectedPool<ConstructUpgrade> upgradePool = new MockInjectedPool<>(ConstructUpgrade.class);
        InjectedPool<ConstructUpgradeCancel> upgradeCancelPool = new MockInjectedPool<>(ConstructUpgradeCancel.class);
        return new ConstructFactory(constructPool, cancelPool, upgradePool, upgradeCancelPool);
    }

    @Override
    protected ActionIdentifier[] getIdentifiers() {
        return ConstructActions.values();
    }
}