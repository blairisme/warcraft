/*
 * Blair Butterworth (c) 2019
 *
 * This work is licensed under the MIT License. To view a copy of this
 * license, visit
 *
 *      https://opensource.org/licenses/MIT
 */

package com.evilbird.warcraft.action.camera;

import com.badlogic.gdx.math.Vector2;
import com.evilbird.engine.action.framework.BasicAction;
import com.evilbird.engine.common.lang.Identifier;
import com.evilbird.engine.common.lang.Positionable;

import javax.inject.Inject;

import static com.evilbird.warcraft.action.camera.CameraActions.Pan;

public class PanAction extends BasicAction
{
    @Inject
    public PanAction() {
        setIdentifier(Pan);
    }

    @Override
    public boolean act(float time) {
        Positionable positionable = getItem();
        Vector2 delta = getCause().getDelta();

        Vector2 value = positionable.getPosition();
        Vector2 difference = new Vector2(delta.x, delta.y);
        Vector2 result = value.add(difference);
        positionable.setPosition(result);

        return true;
    }
}
