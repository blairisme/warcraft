/*
 * Copyright (c) 2019, Blair Butterworth
 *
 * This work is licensed under the MIT License. To view a copy of this
 * license, visit
 *
 *        https://opensource.org/licenses/MIT
 */

package com.evilbird.warcraft.action.train;

import com.evilbird.engine.action.ActionIdentifier;
import com.evilbird.engine.common.inject.InjectedPool;
import com.evilbird.test.testcase.ActionFactoryTestCase;
import com.evilbird.test.utils.MockInjectedPool;
import com.evilbird.warcraft.action.ActionProvider;

/**
 * Instances of this unit test validate the {@link TrainFactory} class.
 *
 * @author Blair Butterworth
 */
public class TrainFactoryTest extends ActionFactoryTestCase
{
    @Override
    protected ActionProvider newFactory() {
        InjectedPool<TrainSequence> trainPool = new MockInjectedPool<>(TrainSequence.class);
        InjectedPool<TrainCancel> cancelPool = new MockInjectedPool<>(TrainCancel.class);
        return new TrainFactory(trainPool, cancelPool);
    }

    @Override
    protected ActionIdentifier[] getIdentifiers() {
        return TrainActions.values();
    }
}