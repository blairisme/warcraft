/*
 * Copyright (c) 2019, Blair Butterworth
 *
 * This work is licensed under the MIT License. To view a copy of this
 * license, visit
 *
 *        https://opensource.org/licenses/MIT
 */

package com.evilbird.warcraft.action.camera;

import com.badlogic.gdx.math.Vector2;
import com.evilbird.engine.action.framework.BasicAction;
import com.evilbird.engine.object.GameObject;
import com.evilbird.engine.object.GameObjectContainer;

import javax.inject.Inject;

import static com.evilbird.engine.object.utility.GameObjectPredicates.withType;
import static com.evilbird.warcraft.action.camera.CameraActions.Focus;
import static com.evilbird.warcraft.item.data.camera.CameraType.Camera;

/**
 * Represents an action that when invoked will center the camera on the actions
 * subject.
 *
 * @author Blair Butterworth
 */
public class FocusAction extends BasicAction
{
    @Inject
    public FocusAction() {
        setIdentifier(Focus);
    }

    @Override
    public boolean act(float delta) {
        GameObject gameObject = getSubject();
        GameObjectContainer root = gameObject.getRoot();

        Vector2 size = gameObject.getSize();
        Vector2 position = gameObject.getPosition();

        position.x -= size.x / 2;
        position.y -= size.y / 2;

        GameObject camera = root.find(withType(Camera));
        camera.setPosition(position);

        return true;
    }
}
