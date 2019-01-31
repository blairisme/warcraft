/*
 * Blair Butterworth (c) 2018
 *
 * This work is licensed under the MIT License. To view a copy of this
 * license, visit
 *
 *      https://opensource.org/licenses/MIT
 */

package com.evilbird.warcraft.item.unit.resource;

import com.evilbird.warcraft.item.common.capability.ResourceContainer;
import com.evilbird.warcraft.item.common.capability.ResourceIdentifier;
import com.evilbird.warcraft.item.unit.Unit;

import java.util.HashMap;
import java.util.Map;

public class Resource extends Unit implements ResourceContainer
{
    private Map<ResourceIdentifier, Float> resources;

    public Resource() {
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
