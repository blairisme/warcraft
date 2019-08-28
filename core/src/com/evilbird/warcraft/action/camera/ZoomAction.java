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
import com.evilbird.engine.device.Device;
import com.evilbird.engine.device.DeviceDisplay;
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
    private final float zoomMin;
    private final float zoomMax;

    @Inject
    public ZoomAction(Device device) {
        this(device.getDeviceDisplay());
    }

    public ZoomAction(DeviceDisplay display) {
        setIdentifier(Zoom);
        float scaleFactor = display.getScaleFactor();
        zoomMin = 0.5f / scaleFactor;
        zoomMax = 2f / scaleFactor;
    }

    @Override
    public boolean act(float time) {
        UserInput input = getCause();
        Camera camera = (Camera)getItem();

        float current = camera.getZoom();
        float delta = input.getDelta().x;
        float scale = current + delta;
        float zoom = MathUtils.clamp(scale, zoomMin, zoomMax);

        camera.setZoom(zoom);
        return true;
    }
}
