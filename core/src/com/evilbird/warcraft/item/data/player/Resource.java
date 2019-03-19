/*
 * Blair Butterworth (c) 2019
 *
 * This work is licensed under the MIT License. To view a copy of this
 * license, visit
 *
 *      https://opensource.org/licenses/MIT
 */

package com.evilbird.warcraft.item.data.player;

import com.evilbird.warcraft.item.common.resource.ResourceIdentifier;
import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;

/**
 * @author Blair Butterworth
 */
public class Resource
{
    private ResourceIdentifier id;
    private float value;

    public Resource(ResourceIdentifier id, float value) {
        this.id = id;
        this.value = value;
    }

    public ResourceIdentifier getId() {
        return id;
    }

    public float getValue() {
        return value;
    }

    public void setValue(float value) {
        this.value = value;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == this) return true;
        if (obj == null) return false;
        if (obj.getClass() != getClass()) return false;

        Resource resource = (Resource)obj;
        return new EqualsBuilder()
            .append(value, resource.value)
            .append(id, resource.id)
            .isEquals();
    }

    @Override
    public int hashCode() {
        return new HashCodeBuilder(17, 37)
            .append(id)
            .append(value)
            .toHashCode();
    }
}
