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
import com.evilbird.engine.device.UserInput;
import com.evilbird.warcraft.item.common.capability.Zoomable;

import javax.inject.Inject;

/**
 * Instances of this Action apply the zoom delta specified in a
 * {@link UserInput} event to the given {@link Zoomable}.
 *
 * @author Blair Butterworth
 */
public class ZoomAction extends BasicAction
{
    private Zoomable zoomable;
    private UserInput input;

    @Inject
    public ZoomAction() {
    }

    public ZoomAction(Zoomable zoomable, UserInput input) {
        this.zoomable = zoomable;
        this.input = input;
    }

    public void setZoomable(Zoomable zoomable) {
        this.zoomable = zoomable;
    }

    public void setZoomEvent(UserInput input) {
        this.input = input;
    }

    @Override
    public boolean act(float time) {
        if (input.getCount() == 1) {
            storeZoom();
            updateZoom();
        } else {
            resetZoom();
            updateZoom();
        }
        return true;
    }

    private void storeZoom() {
        zoomable.setOriginalZoom(zoomable.getZoom());
    }

    private void resetZoom() {
        zoomable.setZoom(zoomable.getOriginalZoom());
    }

    private void updateZoom() {
        float value = zoomable.getZoom();
        float delta = input.getDelta().x;
        float scale = value * delta;
        float zoom = MathUtils.clamp(scale, 0.25f, 1.5f);
        zoomable.setZoom(zoom);
    }
}
