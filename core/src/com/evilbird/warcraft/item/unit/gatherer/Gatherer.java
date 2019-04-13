/*
 * Blair Butterworth (c) 2019
 *
 * This work is licensed under the MIT License. To view a copy of this
 * license, visit
 *
 *      https://opensource.org/licenses/MIT
 */

package com.evilbird.warcraft.item.unit.gatherer;

import com.evilbird.engine.common.serialization.SerializedType;
import com.evilbird.warcraft.item.common.resource.ResourceContainer;
import com.evilbird.warcraft.item.common.resource.ResourceIdentifier;
import com.evilbird.warcraft.item.unit.combatant.Combatant;
import org.apache.commons.lang3.builder.ToStringBuilder;

import javax.inject.Inject;
import java.util.HashMap;
import java.util.Map;

/**
 * Instances of this class represent a unit that can both fight and collect
 * resources.
 *
 * @author Blair Butterworth
 */
@SerializedType("Gatherer")
public class Gatherer extends Combatant implements ResourceContainer
{
    private Map<ResourceIdentifier, Float> resources;

    @Inject
    public Gatherer() {
        resources = new HashMap<>();
    }

    @Override
    public float getResource(ResourceIdentifier resource) {
        Float result = resources.get(resource);
        return result != null ? result : 0f;
    }

    @Override
    public void setResource(ResourceIdentifier type, float value) {
        this.resources.put(type, value);
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this)
            .appendSuper("combatant")
            .append("resources", resources)
            .toString();
    }
}
