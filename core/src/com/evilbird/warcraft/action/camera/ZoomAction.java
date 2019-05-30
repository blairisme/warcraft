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
import com.evilbird.warcraft.item.data.camera.Camera;

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
    private static final float ZOOM_SENSITIVITY = 10f;
    private static final float ZOOM_MAX = 1.5f;
    private static final float ZOOM_MIN = 0.25f;

    @Inject
    public ZoomAction() {
        setIdentifier(Zoom);
    }

    @Override
    public boolean act(float time) {
        UserInput input = getCause();
        Camera camera = (Camera)getItem();

        if (input.getCount() == 1) {
            storeZoom(camera);
            updateZoom(camera, input);
        } else {
            resetZoom(camera);
            updateZoom(camera, input);
        }
        return true;
    }

    private void storeZoom(Camera camera) {
        camera.setOriginalZoom(camera.getZoom());
    }

    private void resetZoom(Camera camera) {
        camera.setZoom(camera.getOriginalZoom());
    }

    private void updateZoom(Camera camera, UserInput input) {
        float current = camera.getZoom();
        float delta = input.getDelta().x / ZOOM_SENSITIVITY;
        float scale = current + delta;
        float zoom = MathUtils.clamp(scale, ZOOM_MIN, ZOOM_MAX);
        camera.setZoom(zoom);
    }
}
