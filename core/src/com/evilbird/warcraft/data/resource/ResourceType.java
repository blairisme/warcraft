/*
 * Copyright (c) 2019, Blair Butterworth
 *
 * This work is licensed under the MIT License. To view a copy of this
 * license, visit
 *
 *        https://opensource.org/licenses/MIT
 */

package com.evilbird.warcraft.data.resource;

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
