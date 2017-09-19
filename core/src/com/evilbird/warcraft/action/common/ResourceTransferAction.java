package com.evilbird.warcraft.action.common;

import com.badlogic.gdx.scenes.scene2d.Action;
import com.evilbird.warcraft.item.unit.resource.ResourceContainer;

/**
 * Created by blair on 19/09/2017.
 */

public class ResourceTransferAction extends Action
{
    private ResourceContainer source;
    private ResourceContainer destination;

    public ResourceTransferAction(ResourceContainer source, ResourceContainer destination)
    {
        this.source = source;
        this.destination = destination;
    }

    @Override
    public boolean act(float delta)
    {
        /*
         ActionValue value = new ItemValue(item, type);
        ActionModifier modifier = new DeltaModifier(-1f, DeltaType.PerSecond);
        ActionDuration duration = new TimeDuration(10f);
        return new ModifyAction(value, modifier, duration);
        */
        return true;
    }
}
