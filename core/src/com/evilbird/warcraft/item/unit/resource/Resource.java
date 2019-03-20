/*
 * Blair Butterworth (c) 2018
 *
 * This work is licensed under the MIT License. To view a copy of this
 * license, visit
 *
 *      https://opensource.org/licenses/MIT
 */

package com.evilbird.warcraft.item.unit.resource;

import com.evilbird.engine.common.collection.IndexedSet;
import com.evilbird.engine.common.serialization.SerializedType;
import com.evilbird.warcraft.item.common.resource.ResourceContainer;
import com.evilbird.warcraft.item.common.resource.ResourceIdentifier;
import com.evilbird.warcraft.item.common.resource.ResourceValue;
import com.evilbird.warcraft.item.unit.Unit;
import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;
import org.apache.commons.lang3.builder.ToStringBuilder;

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
    private IndexedSet<ResourceValue, ResourceIdentifier> resources;

    public Resource() {
        resources = new IndexedSet<>();
    }

    @Override
    public float getResource(ResourceIdentifier id) {
        if (resources.containsKey(id)){
            return resources.get(id).getValue();
        }
        return 0;
    }

    @Override
    public void setResource(ResourceIdentifier id, float value) {
        resources.removeKey(id);
        resources.add(new ResourceValue(id, value));
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
