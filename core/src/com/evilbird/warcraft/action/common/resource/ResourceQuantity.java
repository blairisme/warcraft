/*
 * Blair Butterworth (c) 2019
 *
 * This work is licensed under the MIT License. To view a copy of this
 * license, visit
 *
 *      https://opensource.org/licenses/MIT
 */

package com.evilbird.warcraft.action.common.resource;

import com.evilbird.warcraft.item.common.resource.ResourceType;

/**
 * Implementors of this interface represent an object with an associated
 * resource cost.
 *
 * @author Blair Butterworth
 */
public interface ResourceQuantity
{
    ResourceType getResource();

    float getValue();
}
