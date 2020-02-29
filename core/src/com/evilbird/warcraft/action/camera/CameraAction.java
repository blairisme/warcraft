/*
 * Copyright (c) 2020, Blair Butterworth
 *
 * This Source Code Form is subject to the terms of the Mozilla Public
 * License, v. 2.0. If a copy of the MPL was not distributed with this
 * file, You can obtain one at http://mozilla.org/MPL/2.0/.
 */

package com.evilbird.warcraft.action.camera;

import com.evilbird.engine.action.framework.BasicAction;
import com.evilbird.engine.game.GameController;
import com.evilbird.engine.object.GameObject;
import com.evilbird.engine.object.GameObjectContainer;
import com.evilbird.engine.object.GameObjectContainerType;
import com.evilbird.engine.state.State;
import com.evilbird.warcraft.object.data.camera.Camera;
import com.evilbird.warcraft.object.data.camera.CameraType;

import static com.evilbird.engine.object.utility.GameObjectPredicates.withType;

/**
 * A base class for camera manipulation actions.
 *
 * @author Blair Butterworth
 */
public abstract class CameraAction extends BasicAction
{
    protected Camera getCamera() {
        GameObject subject = getSubject();
        if (subject != null) {
            return getCamera(subject);
        }
        return null;
    }

    private Camera getCamera(GameObject subject) {
        Camera camera = getSubjectCamera(subject);
        if (camera != null) {
            return camera;
        }
        camera = getContainerCamera(subject);
        if (camera != null) {
            return camera;
        }
        return null;
    }

    private Camera getSubjectCamera(GameObject subject) {
        if (subject instanceof Camera) {
            return (Camera)subject;
        }
        return null;
    }

    private Camera getContainerCamera(GameObject subject) {
        GameObjectContainer container = getWorldContainer(subject);
        if (container != null) {
            return (Camera)container.find(withType(CameraType.Camera));
        }
        return null;
    }

    protected GameObjectContainer getWorldContainer(GameObject subject) {
        GameObjectContainer container = subject.getRoot();
        if (container != null && container.getIdentifier() == GameObjectContainerType.Hud) {
            container = getWorldContainer(container);
        }
        return container;
    }

    private GameObjectContainer getWorldContainer(GameObjectContainer container) {
        GameController controller = container.getController();
        State state = controller.getState();
        return state.getWorld();
    }
}
