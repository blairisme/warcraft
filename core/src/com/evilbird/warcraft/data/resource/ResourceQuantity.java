/*
 * Copyright (c) 2020, Blair Butterworth
 *
 * This Source Code Form is subject to the terms of the Mozilla Public
 * License, v. 2.0. If a copy of the MPL was not distributed with this
 * file, You can obtain one at http://mozilla.org/MPL/2.0/.
 */

package com.evilbird.warcraft.data.resource;

import org.apache.commons.lang3.builder.ToStringBuilder;

/**
 * Implementors of this interface represent an object with an associated
 * resource cost.
 *
 * @author Blair Butterworth
 */
public class ResourceQuantity
{
    private float value;
    private ResourceType type;

    public ResourceQuantity(ResourceType type, float value) {
        this.value = value;
        this.type = type;
    }

    public ResourceType getType() {
        return type;
    }

    public float getValue() {
        return value;
    }

    public ResourceQuantity negate() {
        return new ResourceQuantity(type, value * -1);
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this)
            .append("type", type)
            .append("value", value)
            .toString();
    }
}
