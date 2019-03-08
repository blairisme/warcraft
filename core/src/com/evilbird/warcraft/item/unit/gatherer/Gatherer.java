/*
 * Blair Butterworth (c) 2019
 *
 * This work is licensed under the MIT License. To view a copy of this
 * license, visit
 *
 *      https://opensource.org/licenses/MIT
 */

package com.evilbird.warcraft.item.unit.gatherer;

import com.evilbird.warcraft.item.common.resource.ResourceContainer;
import com.evilbird.warcraft.item.common.resource.ResourceIdentifier;
import com.evilbird.warcraft.item.unit.combatant.Combatant;

import javax.inject.Inject;
import java.util.HashMap;
import java.util.Map;

/**
 * Instances of this class represent a unit that can both fight and collect
 * resources.
 *
 * @author Blair Butterworth
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
