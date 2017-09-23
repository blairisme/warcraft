package com.evilbird.warcraft.item.unit.resource;

import com.evilbird.warcraft.item.common.capability.ResourceIdentifier;
import com.evilbird.warcraft.item.common.capability.ResourceContainer;
import com.evilbird.warcraft.item.unit.Unit;

import java.util.HashMap;
import java.util.Map;

/**
 * Instances of this class TODO:Finish
 *
 * @author Blair Butterworth
 */
public class Resource extends Unit implements ResourceContainer
{
    private Map<ResourceIdentifier, Float> resources;

    public Resource()
    {
        resources = new HashMap<ResourceIdentifier, Float>();
    }

    @Override
    public float getResource(ResourceIdentifier resource)
    {
        Float result = resources.get(resource);
        return result != null ? result : 0f;
    }

    @Override
    public void setResource(ResourceIdentifier resource, float value)
    {
        this.resources.put(resource, value);
    }
}
