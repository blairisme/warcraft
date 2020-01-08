/*
 * Copyright (c) 2020, Blair Butterworth
 *
 * This Source Code Form is subject to the terms of the Mozilla Public
 * License, v. 2.0. If a copy of the MPL was not distributed with this
 * file, You can obtain one at http://mozilla.org/MPL/2.0/.
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