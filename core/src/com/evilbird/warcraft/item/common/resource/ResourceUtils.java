/*
 * Blair Butterworth (c) 2019
 *
 * This work is licensed under the MIT License. To view a copy of this
 * license, visit
 *
 *      https://opensource.org/licenses/MIT
 */

package com.evilbird.warcraft.item.common.resource;

import java.util.HashMap;
import java.util.Map;

/**
 * Instances of this class provide common resource utility functions.
 *
 * @author Blair Butterworth
 */
public class ResourceUtils
{
    public static Map<ResourceIdentifier, Float> negate(Map<ResourceIdentifier, Float> resources) {
        return scale(resources, -1);
    }

    public static Map<ResourceIdentifier, Float> scale(Map<ResourceIdentifier, Float> resources, float factor) {
        Map<ResourceIdentifier, Float> result = new HashMap<>();
        for (Map.Entry<ResourceIdentifier, Float> resourcePair: resources.entrySet()) {
            result.put(resourcePair.getKey(), resourcePair.getValue() * factor);
        }
        return result;
    }
}
