/*
 * Blair Butterworth (c) 2019
 *
 * This work is licensed under the MIT License. To view a copy of this
 * license, visit
 *
 *      https://opensource.org/licenses/MIT
 */

package com.evilbird.warcraft.action.common.predicate;

import com.evilbird.engine.action.Action;
import com.evilbird.engine.common.function.Predicate;
import com.evilbird.warcraft.item.common.resource.ResourceContainer;
import com.evilbird.warcraft.item.unit.resource.ResourceType;
import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;

public class TargetHasResources implements Predicate<Action>
{
    private ResourceType type;

    public TargetHasResources() {
        this(null);
    }

    public TargetHasResources(ResourceType type) {
        this.type = type;
    }

    public void setResourceType(ResourceType type) {
        this.type = type;
    }

    @Override
    public boolean test(Action action) {
        if (type != null) {
            ResourceContainer container = (ResourceContainer) action.getItem();
            return container.getResource(type) > 0;
        }
        return false;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == this) return true;
        if (obj == null) return false;
        if (obj.getClass() != getClass()) return false;

        TargetHasResources that = (TargetHasResources)obj;
        return new EqualsBuilder()
            .append(type, that.type)
            .isEquals();
    }

    @Override
    public int hashCode() {
        return new HashCodeBuilder(17, 37)
            .append(type)
            .toHashCode();
    }
}
