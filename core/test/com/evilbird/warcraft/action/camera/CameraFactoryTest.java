/*
 * Copyright (c) 2020, Blair Butterworth
 *
 * This Source Code Form is subject to the terms of the Mozilla Public
 * License, v. 2.0. If a copy of the MPL was not distributed with this
 * file, You can obtain one at http://mozilla.org/MPL/2.0/.
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