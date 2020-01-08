/*
 * Copyright (c) 2020, Blair Butterworth
 *
 * This Source Code Form is subject to the terms of the Mozilla Public
 * License, v. 2.0. If a copy of the MPL was not distributed with this
 * file, You can obtain one at http://mozilla.org/MPL/2.0/.
 */

package com.evilbird.warcraft.action.camera;

import com.evilbird.engine.action.Action;
import com.evilbird.engine.common.lang.TextIdentifier;
import com.evilbird.engine.object.GameObject;
import com.evilbird.test.testcase.ActionTestCase;
import com.evilbird.warcraft.object.data.camera.Camera;

import static com.evilbird.test.data.assets.TestDevices.newTestDevice;

/**
 * Instances of this unit test validate the {@link PanAction} class.
 *
 * @author Blair Butterworth
 */
public class PanActionTest extends ActionTestCase
{
    @Override
    protected Action newAction() {
        return new PanAction();
    }

    @Override
    protected Enum newIdentifier() {
        return CameraActions.Pan;
    }

    @Override
    protected GameObject newItem() {
        Camera camera = new Camera(newTestDevice());
        camera.setIdentifier(new TextIdentifier("item"));
        return camera;
    }
}