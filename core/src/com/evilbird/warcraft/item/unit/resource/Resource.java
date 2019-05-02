/*
 * Copyright (c) 2019, Blair Butterworth
 *
 * This work is licensed under the MIT License. To view a copy of this
 * license, visit
 *
 *        https://opensource.org/licenses/MIT
 */

package com.evilbird.warcraft.item.unit.resource;

import com.evilbird.engine.common.serialization.SerializedType;
import com.evilbird.warcraft.item.common.resource.ResourceContainer;
import com.evilbird.warcraft.item.common.resource.ResourceType;
import com.evilbird.warcraft.item.unit.Unit;
import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;
import org.apache.commons.lang3.builder.ToStringBuilder;

import java.util.LinkedHashMap;
import java.util.Map;

/**
 * Instances of this class represent a game entity that contains resources,
 * such as a gold mine. Methods are provided to obtain resource values from
 * the resource. Gold for example.
 *
 * @author Blair Butterworth
 */
@SerializedType("Resource")
public class Resource extends Unit implements ResourceContainer
{
    private Map<String, Double> resources;

    public Resource() {
        resources = new LinkedHashMap<>();
    }

    @Override
    public float getResource(ResourceType type) {
        String key = type.name();
        if (resources.containsKey(key)){
            return resources.get(key).floatValue();
        }
        return 0;
    }

    @Override
    public void setResource(ResourceType type, float value) {
        String key = type.name();
        resources.put(key, (double)value);
    }

    @Override
    public boolean equals(Object object) {
        if (object == this) return true;
        if (object == null) return false;
        if (object.getClass() != getClass()) return false;

        Resource resource = (Resource)object;
        return new EqualsBuilder()
            .appendSuper(super.equals(object))
            .append(resources, resource.resources)
            .isEquals();
    }

    @Override
    public int hashCode() {
        return new HashCodeBuilder(17, 37)
            .appendSuper(super.hashCode())
            .append(resources)
            .toHashCode();
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this)
            .appendSuper("unit")
            .append("resources", resources)
            .toString();
    }
}
