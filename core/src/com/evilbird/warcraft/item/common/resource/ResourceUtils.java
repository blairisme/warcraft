/*
 * Copyright (c) 2019, Blair Butterworth
 *
 * This work is licensed under the MIT License. To view a copy of this
 * license, visit
 *
 *        https://opensource.org/licenses/MIT
 */

package com.evilbird.warcraft.item.common.resource;

import java.util.ArrayList;
import java.util.Collection;

public class ResourceUtils
{
    private ResourceUtils() {
    }

    // TODO: Move into ResourceContainer interface
    public static Collection<ResourceQuantity> getResources(ResourceContainer container) {
        Collection<ResourceQuantity> resources = new ArrayList<>();
        for (ResourceType resource: ResourceType.values()) {
            resources.add(new ResourceQuantity(resource, container.getResource(resource)));
        }
        return resources;
    }
}
