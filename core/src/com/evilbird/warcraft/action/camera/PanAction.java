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
import com.evilbird.engine.device.Device;
import com.evilbird.engine.device.DeviceDisplay;
import com.evilbird.engine.device.UserInput;
import com.evilbird.warcraft.item.data.camera.Camera;

import javax.inject.Inject;

import static com.evilbird.warcraft.action.camera.CameraActions.Pan;

/**
 * Represents an action that when invoked will pan the camera by the delta
 * specified in the given {@link UserInput cause}.
 *
 * @author Blair Butterworth
 */
public class PanAction extends BasicAction
{
    private transient final float panSensitivity;

    @Inject
    public PanAction(Device device) {
        this(device.getDeviceDisplay());
    }

    public PanAction(DeviceDisplay display) {
        setIdentifier(Pan);
        panSensitivity = display.getScaleFactor();
    }

    @Override
    public boolean act(float time) {
        UserInput cause = getCause();
        Camera camera = (Camera)getItem();

        Vector2 delta = cause.getDelta();
        Vector2 zoomed = delta.scl(camera.getZoom());
        Vector2 scaled = zoomed.scl(panSensitivity);

        Vector2 current = camera.getPosition();
        Vector2 result = current.add(scaled);
        camera.setPosition(result);

        return true;
    }
}
