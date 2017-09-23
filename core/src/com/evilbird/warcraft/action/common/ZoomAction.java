package com.evilbird.warcraft.action.common;

import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.scenes.scene2d.Action;
import com.evilbird.engine.device.UserInput;
import com.evilbird.warcraft.item.common.capability.Zoomable;

/**
 * Created by blair on 15/09/2017.
 */

public class ZoomAction extends Action
{
    private Zoomable zoomable;
    private UserInput input;

    public ZoomAction(Zoomable zoomable, UserInput input)
    {
        this.zoomable = zoomable;
        this.input = input;
    }

    @Override
    public boolean act(float time)
    {
        if (input.getCount() == 1)
        {
            storeZoom();
            updateZoom();
        }
        else
        {
            resetZoom();
            updateZoom();
        }
        return true;
    }

    private void storeZoom()
    {
        zoomable.setOriginalZoom(zoomable.getZoom());
    }

    private void resetZoom()
    {
        zoomable.setZoom(zoomable.getOriginalZoom());
    }

    private void updateZoom()
    {
        float value = zoomable.getZoom();
        float delta = input.getDelta().x;
        float scale =  value * delta;
        float zoom = MathUtils.clamp(scale, 0.25f, 1.5f);
        zoomable.setZoom(zoom);
    }
}
