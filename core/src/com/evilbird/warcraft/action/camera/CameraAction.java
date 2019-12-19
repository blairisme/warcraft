/*
 * Copyright (c) 2019, Blair Butterworth
 *
 * This work is licensed under the MIT License. To view a copy of this
 * license, visit
 *
 *        https://opensource.org/licenses/MIT
 */

package com.evilbird.warcraft.action.camera;

import com.evilbird.engine.action.framework.BasicAction;
import com.evilbird.engine.game.GameController;
import com.evilbird.engine.object.GameObject;
import com.evilbird.engine.object.GameObjectContainer;
import com.evilbird.engine.state.State;
import com.evilbird.warcraft.object.data.camera.Camera;
import com.evilbird.warcraft.object.data.camera.CameraType;
import com.evilbird.warcraft.object.display.UserInterfaceType;

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
        if (container != null && container.getIdentifier() == UserInterfaceType.Hud) {
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
