/*
 * Copyright (c) 2019, Blair Butterworth
 *
 * This work is licensed under the MIT License. To view a copy of this
 * license, visit
 *
 *        https://opensource.org/licenses/MIT
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
        InjectedPool<MoveToItemSequence> moveItemPool = new MockInjectedPool<>(MoveToItemSequence.class);
        InjectedPool<MoveToVectorSequence> moveLocationPool = new MockInjectedPool<>(MoveToVectorSequence.class);
        return new MoveFactory(cancelPool, moveItemPool, moveLocationPool);
    }

    @Override
    protected ActionIdentifier[] getIdentifiers() {
        return MoveActions.values();
    }
}