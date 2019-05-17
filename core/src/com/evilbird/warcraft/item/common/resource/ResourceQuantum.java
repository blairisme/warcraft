/*
 * Copyright (c) 2019, Blair Butterworth
 *
 * This work is licensed under the MIT License. To view a copy of this
 * license, visit
 *
 *        https://opensource.org/licenses/MIT
 */

package com.evilbird.warcraft.item.common.resource;

/**
 * A {@link ResourceQuantity} implementation representing a resource type and
 * value pair.
 *
 * @author Blair Butterworth
 */
public class ResourceQuantum implements ResourceQuantity
{
    private float value;
    private ResourceType type;

    public ResourceQuantum(ResourceType type, float value) {
        this.value = value;
        this.type = type;
    }

    public static ResourceQuantity resource(ResourceType type, float value) {
        return new ResourceQuantum(type, value);
    }

    @Override
    public ResourceType getResource() {
        return type;
    }

    @Override
    public float getValue() {
        return value;
    }
}
