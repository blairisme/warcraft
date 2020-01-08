/*
 * Copyright (c) 2020, Blair Butterworth
 *
 * This Source Code Form is subject to the terms of the Mozilla Public
 * License, v. 2.0. If a copy of the MPL was not distributed with this
 * file, You can obtain one at http://mozilla.org/MPL/2.0/.
 */

package com.evilbird.warcraft.action.move;

import com.evilbird.engine.action.ActionIdentifier;
import com.evilbird.engine.common.inject.InjectedPool;
import com.evilbird.test.testcase.ActionFactoryTestCase;
import com.evilbird.test.utils.MockInjectedPool;
import com.evilbird.warcraft.action.ActionProvider;
import org.junit.Before;

/**
 * Instances of this unit test validate the {@link MoveFactory} class.
 *
 * @author Blair Butterworth
 */
public class MoveFactoryTest extends ActionFactoryTestCase
{
    @Before
    public void setup() {
        super.setup();
        verifyIdentifiers = false;
    }

    @Override
    protected ActionProvider newFactory() {
        InjectedPool<MoveCancel> cancelPool = new MockInjectedPool<>(MoveCancel.class);
        InjectedPool<MoveToItemAction> moveItemPool = new MockInjectedPool<>(MoveToItemAction.class);
        InjectedPool<MoveToVectorAction> moveLocationPool = new MockInjectedPool<>(MoveToVectorAction.class);
        InjectedPool<MoveWithinRangeAction> moveRangePool = new MockInjectedPool<>(MoveWithinRangeAction.class);
        return new MoveFactory(cancelPool, moveItemPool, moveLocationPool, moveRangePool);
    }

    @Override
    protected ActionIdentifier[] getIdentifiers() {
        return MoveActions.values();
    }
}