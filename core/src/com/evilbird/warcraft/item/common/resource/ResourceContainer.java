/*
 * Blair Butterworth (c) 2019
 *
 * This work is licensed under the MIT License. To view a copy of this
 * license, visit
 *
 *      https://opensource.org/licenses/MIT
 */

package com.evilbird.warcraft.item.common.resource;

import com.evilbird.engine.item.Item;

/**
 * Implementors of this interface represent a container for the storage of
 * resources.
 *
 * @author Blair Butterworth
 */
public interface ResourceContainer extends Item
{
    float getResource(ResourceType resource);

    void setResource(ResourceType resource, float value);
}
