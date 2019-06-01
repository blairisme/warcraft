/*
 * Copyright (c) 2019, Blair Butterworth
 *
 * This work is licensed under the MIT License. To view a copy of this
 * license, visit
 *
 *        https://opensource.org/licenses/MIT
 */

package com.evilbird.warcraft.action.placeholder;

import com.evilbird.engine.action.ActionIdentifier;
import com.evilbird.engine.common.inject.InjectedPool;
import com.evilbird.test.testcase.ActionFactoryTestCase;
import com.evilbird.test.utils.MockInjectedPool;
import com.evilbird.warcraft.action.ActionProvider;

/**
 * Instances of this unit test validate the {@link PlaceholderFactory} class.
 *
 * @author Blair Butterworth
 */
public class PlaceholderFactoryTest extends ActionFactoryTestCase
{
    @Override
    protected ActionProvider newFactory() {
        InjectedPool<PlaceholderCancel> cancelPool = new MockInjectedPool<>(PlaceholderCancel.class);
        InjectedPool<PlaceholderCreate> createPool = new MockInjectedPool<>(PlaceholderCreate.class);
        InjectedPool<PlaceholderMove> movePool = new MockInjectedPool<>(PlaceholderMove.class);
        return new PlaceholderFactory(cancelPool, createPool, movePool);
    }

    @Override
    protected ActionIdentifier[] getIdentifiers() {
        return PlaceholderActions.values();
    }
}