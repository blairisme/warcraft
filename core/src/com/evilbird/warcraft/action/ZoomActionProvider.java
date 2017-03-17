package com.evilbird.warcraft.action;

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

import javax.inject.Inject;

/**
 * Instances of this class TODO:Finish
 *
 * @author Blair Butterworth
 */
public class ZoomActionProvider implements ActionProvider
{
    @Inject
    public ZoomActionProvider()
    {
    }

    @Override
    public Action get(ActionType action, Item item, Item target, UserInput input)
    {
        return get(item, input);
    }

    public Action get(Item item, UserInput input)
    {
        if (input.getCount() == 1)
        {
            Action storeZoom = storeZoom(item);
            Action updateZoom = updateZoom(item, input);
            return new CompositeAction(storeZoom, updateZoom);
        }
        else
        {
            Action resetZoom = resetZoom(item);
            Action updateZoom = updateZoom(item, input);
            return new CompositeAction(resetZoom, updateZoom);
        }
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
