/*
 * Copyright (c) 2020, Blair Butterworth
 *
 * This Source Code Form is subject to the terms of the Mozilla Public
 * License, v. 2.0. If a copy of the MPL was not distributed with this
 * file, You can obtain one at http://mozilla.org/MPL/2.0/.
 */

package com.evilbird.warcraft.action.camera;

import com.badlogic.gdx.math.MathUtils;
import com.evilbird.engine.action.ActionResult;
import com.evilbird.engine.action.framework.BasicAction;
import com.evilbird.engine.common.lang.Zoomable;
import com.evilbird.engine.device.Device;
import com.evilbird.engine.device.DeviceDisplay;
import com.evilbird.engine.device.UserInput;
import com.evilbird.warcraft.object.data.camera.Camera;

import javax.inject.Inject;

import static com.evilbird.engine.action.ActionResult.Complete;
import static com.evilbird.warcraft.action.camera.CameraActions.Zoom;

/**
 * Instances of this Action apply the zoom delta specified in a
 * {@link UserInput} event to the given {@link Zoomable}.
 *
 * @author Blair Butterworth
 */
public class ZoomAction extends BasicAction
{
    private final transient float zoomMin;
    private final transient float zoomMax;

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
    public ActionResult act(float time) {
        UserInput input = getCause();
        Camera camera = (Camera)getSubject();

        float current = camera.getZoom();
        float delta = input.getDelta().x;
        float scale = current + delta;
        float zoom = MathUtils.clamp(scale, zoomMin, zoomMax);

        camera.setZoom(zoom);
        return Complete;
    }
}
