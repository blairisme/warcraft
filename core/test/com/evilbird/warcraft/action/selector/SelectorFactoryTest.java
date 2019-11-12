/*
 * Copyright (c) 2019, Blair Butterworth
 *
 * This work is licensed under the MIT License. To view a copy of this
 * license, visit
 *
 *        https://opensource.org/licenses/MIT
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