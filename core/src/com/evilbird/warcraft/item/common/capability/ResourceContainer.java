package com.evilbird.warcraft.item.common.capability;

public interface ResourceContainer
{
    public float getResource(ResourceIdentifier resource);

    public void setResource(ResourceIdentifier resource, float value);
}
