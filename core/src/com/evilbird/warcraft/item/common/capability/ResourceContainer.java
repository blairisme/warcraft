package com.evilbird.warcraft.item.common.capability;

import java.util.Map;

public interface ResourceContainer
{
    public float getResource(ResourceIdentifier resource);

    public Map<ResourceIdentifier, Float> getResources();

    public void setResource(ResourceIdentifier resource, float value);

    public void setResources(Map<ResourceIdentifier, Float> resources);
}
