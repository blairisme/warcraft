/*
 * Copyright (c) 2020, Blair Butterworth
 *
 * This Source Code Form is subject to the terms of the Mozilla Public
 * License, v. 2.0. If a copy of the MPL was not distributed with this
 * file, You can obtain one at http://mozilla.org/MPL/2.0/.
 */

package com.evilbird.warcraft.data.resource;

import com.evilbird.engine.object.GameObject;

import java.util.ArrayList;
import java.util.Collection;

/**
 * Implementors of this interface represent a container for the storage of
 * resources.
 *
 * @author Blair Butterworth
 */
public interface ResourceContainer extends GameObject
{
    /**
     * Returns the value of a resource held in the {@code ResourceContainer}.
     */
    float getResource(ResourceType resource);

    default Collection<ResourceQuantity> getResources() {
        Collection<ResourceQuantity> resources = new ArrayList<>();
        for (ResourceType resource: ResourceType.values()) {
            resources.add(new ResourceQuantity(resource, getResource(resource)));
        }
        return resources;
    }

    default boolean hasResources(Iterable<ResourceQuantity> resources) {
        for (ResourceQuantity resource: resources) {
            if (getResource(resource.getType()) < resource.getValue()){
                return false;
            }
        }
        return true;
    }

    /**
     * Sets the value of a resource held in the {@code ResourceContainer}.
     */
    void setResource(ResourceType resource, float value);

    default void setResources(Iterable<com.evilbird.warcraft.data.resource.ResourceQuantity> resources) {
        for (ResourceQuantity resource: resources) {
            setResource(resource.getType(), resource.getValue());
        }
    }
}
