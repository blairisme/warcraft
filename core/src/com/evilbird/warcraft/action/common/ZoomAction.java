package com.evilbird.warcraft.action.common;

import com.badlogic.gdx.scenes.scene2d.Action;
import com.evilbird.engine.action.CompositeAction;
import com.evilbird.engine.action.ModifyAction;
import com.evilbird.engine.action.duration.ActionDuration;
import com.evilbird.engine.action.duration.InstantDuration;
import com.evilbird.engine.action.modifier.ActionModifier;
import com.evilbird.engine.action.modifier.ConstantModifier;
import com.evilbird.engine.action.modifier.ScaleModifier;
import com.evilbird.engine.action.value.ActionValue;
import com.evilbird.engine.action.value.ItemValue;
import com.evilbird.engine.device.UserInput;
import com.evilbird.engine.item.Item;
import com.evilbird.warcraft.item.data.camera.CameraProperties;

/**
 * Created by blair on 15/09/2017.
 */

public class ZoomAction extends Action
{
    private Zoomable zoomable;
    private UserInput input;

    @Override
    public boolean act(float delta)
    {
        return true;
    }


    private Action storeZoom(Item item)
    {
        ActionValue value = new ItemValue(item, CameraProperties.OriginalZoom);
        ActionModifier modifier = new ConstantModifier(new ItemValue(item, CameraProperties.Zoom));
        ActionDuration duration = new InstantDuration();
        return new ModifyAction(value, modifier, duration);
    }

    private Action resetZoom(Item item)
    {
        ActionValue value = new ItemValue(item, CameraProperties.Zoom);
        ActionModifier modifier = new ConstantModifier(new ItemValue(item, CameraProperties.OriginalZoom));
        ActionDuration duration = new InstantDuration();
        return new ModifyAction(value, modifier, duration);
    }

    private Action updateZoom(Item item, UserInput input)
    {
        ActionValue zoom = new ItemValue(item, CameraProperties.Zoom);
        ActionModifier modifier = new ScaleModifier(input.getDelta().x, 0.25f, 1.5f);
        ActionDuration duration = new InstantDuration();
        return new ModifyAction(zoom, modifier, duration);
    }
}
