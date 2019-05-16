/*
 * Copyright (c) 2019, Blair Butterworth
 *
 * This work is licensed under the MIT License. To view a copy of this
 * license, visit
 *
 *        https://opensource.org/licenses/MIT
 */

package com.evilbird.warcraft.action.gather;

import com.evilbird.engine.action.ActionIdentifier;
import com.evilbird.engine.common.inject.InjectedPool;
import com.evilbird.test.testcase.ActionFactoryTestCase;
import com.evilbird.test.utils.MockInjectedPool;
import com.evilbird.warcraft.action.ActionProvider;
import org.junit.Before;

/**
 * Instances of this unit test validate the {@link GatherFactory} class.
 *
 * @author Blair Butterworth
 */
public class GatherFactoryTest extends ActionFactoryTestCase
{
    @Before
    public void setup() {
        super.setup();
        verifyIdentifiers = false;
    }

    @Override
    protected ActionProvider newFactory() {
        InjectedPool<GatherGold> goldPool = new MockInjectedPool<>(GatherGold.class);
        InjectedPool<GatherWood> woodPool = new MockInjectedPool<>(GatherWood.class);
        InjectedPool<GatherCancel> cancelPool = new MockInjectedPool<>(GatherCancel.class);
        return new GatherFactory(goldPool, woodPool, cancelPool);
    }

    @Override
    protected ActionIdentifier[] getIdentifiers() {
        return GatherActions.values();
    }
}