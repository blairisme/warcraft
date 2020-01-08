/*
 * Copyright (c) 2020, Blair Butterworth
 *
 * This Source Code Form is subject to the terms of the Mozilla Public
 * License, v. 2.0. If a copy of the MPL was not distributed with this
 * file, You can obtain one at http://mozilla.org/MPL/2.0/.
 */

package com.evilbird.warcraft.action.selector;

import com.evilbird.engine.action.ActionIdentifier;
import com.evilbird.engine.common.inject.InjectedPool;
import com.evilbird.test.testcase.ActionFactoryTestCase;
import com.evilbird.test.utils.MockInjectedPool;
import com.evilbird.warcraft.action.ActionProvider;

/**
 * Instances of this unit test validate the {@link SelectorFactory} class.
 *
 * @author Blair Butterworth
 */
public class SelectorFactoryTest extends ActionFactoryTestCase
{
    @Override
    protected ActionProvider newFactory() {
        InjectedPool<SelectorArea> area = new MockInjectedPool<>(SelectorArea.class);
        InjectedPool<SelectorCancel> cancelPool = new MockInjectedPool<>(SelectorCancel.class);
        InjectedPool<SelectorCreate> createPool = new MockInjectedPool<>(SelectorCreate.class);
        InjectedPool<SelectorMove> movePool = new MockInjectedPool<>(SelectorMove.class);
        return new SelectorFactory(area, cancelPool, createPool, movePool);
    }

    @Override
    protected ActionIdentifier[] getIdentifiers() {
        return SelectorActions.values();
    }
}