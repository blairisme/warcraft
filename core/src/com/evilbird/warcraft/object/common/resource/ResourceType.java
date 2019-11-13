/*
 * Blair Butterworth (c) 2018
 *
 * This work is licensed under the MIT License. To view a copy of this
 * license, visit
 *
 *      https://opensource.org/licenses/MIT
 */

package com.evilbird.warcraft.object.common.resource;

import com.evilbird.engine.common.lang.Identifier;

/**
 * Implementors of this interface specify unique identifiers for resources.
 *
 * @author Blair Butterworth
 */
public enum ResourceType implements Identifier
{
    Gold,
    Oil,
    Wood,
    Food
}
