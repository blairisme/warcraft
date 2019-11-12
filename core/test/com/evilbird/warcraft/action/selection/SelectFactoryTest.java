/*
 * Copyright (c) 2019, Blair Butterworth
 *
 * This work is licensed under the MIT License. To view a copy of this
 * license, visit
 *
 *        https://opensource.org/licenses/MIT
 */

package com.evilbird.warcraft.action.selection;

import com.evilbird.engine.action.ActionIdentifier;
import com.evilbird.engine.common.inject.InjectedPool;
import com.evilbird.test.testcase.ActionFactoryTestCase;
import com.evilbird.test.utils.MockInjectedPool;
import com.evilbird.warcraft.action.ActionProvider;

/**
 * Instances of this unit test validate the {@link SelectFactory} class.
 *
 * @author Blair Butterworth
 */
public class SelectFactoryTest extends ActionFactoryTestCase
{
    @Override
    protected ActionProvider newFactory() {
        InjectedPool<DeselectAction> deselectPool = new MockInjectedPool<>(DeselectAction.class);
        InjectedPool<SelectInvert> invertPool = new MockInjectedPool<>(SelectInvert.class);
        InjectedPool<SelectFlash> flashPool = new MockInjectedPool<>(SelectFlash.class);
        return new SelectFactory(deselectPool, invertPool, flashPool);
    }

    @Override
    protected ActionIdentifier[] getIdentifiers() {
        return SelectActions.values();
    }
}