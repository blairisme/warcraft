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
 * Implementors of this interface represent an object with an associated
 * resource cost, usual required for creation.
 *
 * @author Blair Butterworth
 */
public interface ResourceRequirement
{
    Map<ResourceIdentifier, Float> getResourceRequirements();
}
