package com.evilbird.warcraft.item.unit.resource;

import com.evilbird.warcraft.item.common.capability.ResourceIdentifier;
import com.evilbird.warcraft.item.common.capability.ResourceContainer;
import com.evilbird.warcraft.item.unit.Unit;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

/**
 * Instances of this class TODO:Finish
 *
 * @author Blair Butterworth
 */
public class Resource extends Unit implements ResourceContainer
{
    private ResourceType resourceType;
    private float resourceValue;

    public Resource()
    {
        resourceType = null;
        resourceValue = 0f;
    }

    public ResourceType getResourceType() {
        return resourceType;
    }

    @Override
    public float getResource(ResourceIdentifier resource) {
        if (resourceType == resource) {
            return resourceValue;
        }
        return 0f;
    }

    @Override
    public void setResource(ResourceIdentifier resource, float value) {
        resourceType = (ResourceType)resource;
        resourceValue = value;
    }
}
