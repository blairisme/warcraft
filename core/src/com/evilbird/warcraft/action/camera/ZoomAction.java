/*
 * Blair Butterworth (c) 2018
 *
 * This work is licensed under the MIT License. To view a copy of this
 * license, visit
 *
 *      https://opensource.org/licenses/MIT
 */

package com.evilbird.warcraft.action.camera;

import com.badlogic.gdx.math.MathUtils;
import com.evilbird.engine.action.framework.BasicAction;
import com.evilbird.engine.common.lang.Zoomable;
import com.evilbird.engine.device.UserInput;

import javax.inject.Inject;

import static com.evilbird.warcraft.action.camera.CameraActions.Zoom;

/**
 * Instances of this Action apply the zoom delta specified in a
 * {@link UserInput} event to the given {@link Zoomable}.
 *
 * @author Blair Butterworth
 */
public class ZoomAction extends BasicAction
{
    @Inject
    public ZoomAction() {
        setIdentifier(Zoom);
    }

    @Override
    public boolean act(float time) {
        UserInput input = getCause();
        Zoomable zoomable = (Zoomable)getItem();

        if (input.getCount() == 1) {
            storeZoom(zoomable);
            updateZoom(zoomable, input);
        } else {
            resetZoom(zoomable);
            updateZoom(zoomable, input);
        }
        return true;
    }

    private void storeZoom(Zoomable zoomable) {
        zoomable.setOriginalZoom(zoomable.getZoom());
    }

    private void resetZoom(Zoomable zoomable) {
        zoomable.setZoom(zoomable.getOriginalZoom());
    }

    private void updateZoom(Zoomable zoomable, UserInput input) {
        float value = zoomable.getZoom();
        float delta = input.getDelta().x;
        float scale = value * delta;
        float zoom = MathUtils.clamp(scale, 0.25f, 1.5f);
        zoomable.setZoom(zoom);
    }
}
