/*
 * Copyright (c) 2019, Blair Butterworth
 *
 * This work is licensed under the MIT License. To view a copy of this
 * license, visit
 *
 *        https://opensource.org/licenses/MIT
 */

package com.evilbird.warcraft.action.camera;

import com.evilbird.engine.action.ActionIdentifier;
import com.evilbird.engine.common.inject.InjectedPool;
import com.evilbird.test.testcase.ActionFactoryTestCase;
import com.evilbird.test.utils.MockInjectedPool;
import com.evilbird.warcraft.action.ActionProvider;
import org.junit.Before;

/**
 * Instances of this unit test validate the {@link CameraFactory} class.
 *
 * @author Blair Butterworth
 */
public class CameraFactoryTest extends ActionFactoryTestCase
{
    @Before
    public void setup() {
        super.setup();
        verifyIdentifiers = false;
    }

    @Override
    protected ActionProvider newFactory() {
        InjectedPool<FocusAction> focusPool = new MockInjectedPool<>(FocusAction.class);
        InjectedPool<PanAction> panPool = new MockInjectedPool<>(PanAction.class);
        InjectedPool<ZoomAction> zoomPool = new MockInjectedPool<>(ZoomAction.class);
        return new CameraFactory(focusPool, panPool, zoomPool);
    }

    @Override
    protected ActionIdentifier[] getIdentifiers() {
        return CameraActions.values();
    }
}