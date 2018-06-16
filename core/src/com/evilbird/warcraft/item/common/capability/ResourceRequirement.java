package com.evilbird.warcraft.item.common.capability;

import java.util.Map;

public interface ResourceRequirement
{
    public Map<ResourceIdentifier, Float> getResourceRequirements();
}
