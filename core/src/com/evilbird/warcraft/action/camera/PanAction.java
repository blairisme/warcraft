/*
 * Copyright (c) 2020, Blair Butterworth
 *
 * This Source Code Form is subject to the terms of the Mozilla Public
 * License, v. 2.0. If a copy of the MPL was not distributed with this
 * file, You can obtain one at http://mozilla.org/MPL/2.0/.
 */

package com.evilbird.warcraft.action.camera;

import com.badlogic.gdx.math.Vector2;
import com.evilbird.engine.action.framework.AbstractAction;
import com.evilbird.engine.device.UserInput;
import com.evilbird.warcraft.object.data.camera.Camera;

import javax.inject.Inject;

import static com.evilbird.warcraft.action.camera.CameraActions.Pan;

/**
 * Represents an action that when invoked will pan the camera by the delta
 * specified in the given {@link UserInput cause}.
 *
 * @author Blair Butterworth
 */
public class PanAction extends AbstractAction
{
    @Inject
    public PanAction() {
        setIdentifier(Pan);
    }

    @Override
    public boolean act(float time) {
        UserInput cause = getCause();
        Camera camera = (Camera)getSubject();

        Vector2 delta = cause.getDelta();
        Vector2 scaled = delta.scl(camera.getZoom());

        Vector2 current = camera.getPosition();
        Vector2 result = current.add(scaled);
        camera.setPosition(result);

        return true;
    }
}
