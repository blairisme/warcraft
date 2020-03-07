/*
 * Copyright (c) 2020, Blair Butterworth
 *
 * This Source Code Form is subject to the terms of the Mozilla Public
 * License, v. 2.0. If a copy of the MPL was not distributed with this
 * file, You can obtain one at http://mozilla.org/MPL/2.0/.
 */

package com.evilbird.warcraft.action.camera;

import com.badlogic.gdx.math.Vector2;
import com.evilbird.engine.action.ActionResult;
import com.evilbird.engine.object.GameObject;

import javax.inject.Inject;

import static com.evilbird.engine.action.ActionResult.Complete;
import static com.evilbird.warcraft.action.camera.CameraActions.Focus;

/**
 * Represents an action that when invoked will center the camera on the actions
 * subject.
 *
 * @author Blair Butterworth
 */
public class FocusAction extends CameraAction
{
    @Inject
    public FocusAction() {
        setIdentifier(Focus);
    }

    @Override
    public ActionResult act(float time) {
        GameObject gameObject = getSubject();
        Vector2 size = gameObject.getSize();
        Vector2 position = gameObject.getPosition();

        position.x -= size.x / 2;
        position.y -= size.y / 2;

        GameObject camera = getCamera();
        camera.setPosition(position);

        return Complete;
    }
}
