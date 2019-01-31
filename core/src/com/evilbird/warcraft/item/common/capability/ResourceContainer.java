/*
 * Blair Butterworth (c) 2019
 *
 * This work is licensed under the MIT License. To view a copy of this
 * license, visit
 *
 *      https://opensource.org/licenses/MIT
 */

package com.evilbird.warcraft.item.common.capability;

import java.util.Map;

/**
 * Implementors of this interface represent a container for the storage of
 * resources.
 *
 * @author Blair Butterworth
 */
public interface ResourceContainer
{
    float getResource(ResourceIdentifier resource);

    Map<ResourceIdentifier, Float> getResources();

    void setResource(ResourceIdentifier resource, float value);

    void setResources(Map<ResourceIdentifier, Float> resources);
}
