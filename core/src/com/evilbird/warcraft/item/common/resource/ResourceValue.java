/*
 * Blair Butterworth (c) 2019
 *
 * This work is licensed under the MIT License. To view a copy of this
 * license, visit
 *
 *      https://opensource.org/licenses/MIT
 */

package com.evilbird.warcraft.item.common.resource;

import com.evilbird.engine.common.collection.Indexible;
import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;
import org.apache.commons.lang3.builder.ToStringBuilder;

public class ResourceValue implements Indexible<ResourceIdentifier>
{
    private ResourceIdentifier id;
    private float value;

    public ResourceValue(ResourceIdentifier id, float value) {
        this.id = id;
        this.value = value;
    }

    @Override
    public ResourceIdentifier getIndex() {
        return id;
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

        ResourceValue resource = (ResourceValue)obj;
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

    @Override
    public String toString() {
        return new ToStringBuilder(this)
            .append("id", id)
            .append("value", value)
            .toString();
    }
}
