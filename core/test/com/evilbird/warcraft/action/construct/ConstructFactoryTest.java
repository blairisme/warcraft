/*
 * Copyright (c) 2019, Blair Butterworth
 *
 * This work is licensed under the MIT License. To view a copy of this
 * license, visit
 *
 *        https://opensource.org/licenses/MIT
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
public class ConstructFactoryTest extends ActionFactoryTestCase {

    @Override
    protected ActionProvider newFactory() {
        InjectedPool<ConstructSequence> constructPool = new MockInjectedPool<>(ConstructSequence.class);
        InjectedPool<ConstructCancel> cancelPool = new MockInjectedPool<>(ConstructCancel.class);
        return new ConstructFactory(constructPool, cancelPool);
    }

    @Override
    protected ActionIdentifier[] getIdentifiers() {
        return ConstructActions.values();
    }
}