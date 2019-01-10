package com.evilbird.warcraft.item.unit.gatherer;

import com.evilbird.warcraft.item.common.capability.ResourceContainer;
import com.evilbird.warcraft.item.common.capability.ResourceIdentifier;
import com.evilbird.warcraft.item.unit.combatant.Combatant;

import javax.inject.Inject;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by blair on 21/09/2017.
 */
public class Gatherer extends Combatant implements ResourceContainer
{
    private Map<ResourceIdentifier, Float> resources;

    @Inject
    public Gatherer() {
        resources = new HashMap<>();
    }

    @Override
    public float getResource(ResourceIdentifier resource) {
        Float result = resources.get(resource);
        return result != null ? result : 0f;
    }

    @Override
    public Map<ResourceIdentifier, Float> getResources() {
        return resources;
    }

    @Override
    public void setResource(ResourceIdentifier type, float value) {
        this.resources.put(type, value);
    }

    @Override
    public void setResources(Map<ResourceIdentifier, Float> resources) {
        this.resources = resources;
    }
}
