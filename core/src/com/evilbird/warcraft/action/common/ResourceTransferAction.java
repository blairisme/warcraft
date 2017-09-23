package com.evilbird.warcraft.action.common;

import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.scenes.scene2d.Action;
import com.evilbird.warcraft.item.common.capability.ResourceContainer;
import com.evilbird.warcraft.item.common.capability.ResourceIdentifier;

/**
 * Created by blair on 19/09/2017.
 */
public class ResourceTransferAction extends Action
{
    private ResourceContainer source;
    private ResourceContainer destination;
    private ResourceIdentifier type;
    private float amount;

    public ResourceTransferAction(
        ResourceContainer source,
        ResourceContainer destination,
        ResourceIdentifier type,
        float amount)
    {
        this.type = type;
        this.source = source;
        this.destination = destination;
        this.amount = amount;
    }

    @Override
    public boolean act(float time)
    {
        float sourceValue = source.getResource(type);
        float delta = sourceValue - amount < 0 ? sourceValue : amount;
        float newSource = sourceValue - delta;
        source.setResource(type, newSource);

        float destinationValue = destination.getResource(type);
        float newDestination = MathUtils.clamp(destinationValue + amount, 0f, Float.MAX_VALUE);
        destination.setResource(type, newDestination);

        return true;
    }
}
