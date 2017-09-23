package com.evilbird.warcraft.item.common.capability;

/**
 * Created by blair on 19/09/2017.
 */

public interface ResourceContainer
{
    public float getResource(ResourceIdentifier resource);

    public void setResource(ResourceIdentifier resource, float value);
}
