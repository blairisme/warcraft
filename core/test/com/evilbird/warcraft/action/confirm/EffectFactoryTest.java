/*
 * Copyright (c) 2019, Blair Butterworth
 *
 * This work is licensed under the MIT License. To view a copy of this
 * license, visit
 *
 *        https://opensource.org/licenses/MIT
 */

package com.evilbird.warcraft.action.confirm;

import com.evilbird.engine.action.ActionIdentifier;
import com.evilbird.engine.common.inject.InjectedPool;
import com.evilbird.test.testcase.ActionFactoryTestCase;
import com.evilbird.test.utils.MockInjectedPool;
import com.evilbird.warcraft.action.ActionProvider;
import org.junit.Before;

/**
 * Instances of this unit test validate the {@link ConfirmFactory} class.
 *
 * @author Blair Butterworth
 */
public class EffectFactoryTest extends ActionFactoryTestCase
{
    @Before
    public void setup() {
        super.setup();
        verifyIdentifiers = false;
    }

    @Override
    protected ActionProvider newFactory() {
        InjectedPool<ConfirmItem> itemPool = new MockInjectedPool<>(ConfirmItem.class);
        InjectedPool<ConfirmLocation> locationPool = new MockInjectedPool<>(ConfirmLocation.class);
        InjectedPool<ConfirmAttack> attackPool = new MockInjectedPool<>(ConfirmAttack.class);
        return new ConfirmFactory(itemPool, locationPool, attackPool);
    }

    @Override
    protected ActionIdentifier[] getIdentifiers() {
        return ConfirmActions.values();
    }
}