/*
 * Copyright (c) 2019, Blair Butterworth
 *
 * This work is licensed under the MIT License. To view a copy of this
 * license, visit
 *
 *        https://opensource.org/licenses/MIT
 */

package com.evilbird.warcraft.action.attack;

import com.evilbird.engine.action.ActionIdentifier;
import com.evilbird.engine.common.inject.InjectedPool;
import com.evilbird.test.testcase.ActionFactoryTestCase;
import com.evilbird.test.utils.MockInjectedPool;
import com.evilbird.warcraft.action.ActionProvider;
import org.junit.Before;

/**
 * Instances of this unit test validate the {@link AttackFactory} class.
 *
 * @author Blair Butterworth
 */
public class AttackFactoryTest extends ActionFactoryTestCase
{
    @Before
    public void setup() {
        super.setup();
        verifyIdentifiers = false;
    }

    @Override
    protected ActionProvider newFactory() {
        InjectedPool<AttackAction> attackPool = new MockInjectedPool<>(AttackAction.class);
        InjectedPool<AttackCancel> cancelPool = new MockInjectedPool<>(AttackCancel.class);
        return new AttackFactory(attackPool, cancelPool);
    }

    @Override
    protected ActionIdentifier[] getIdentifiers() {
        return AttackActions.values();
    }
}