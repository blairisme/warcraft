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
import com.evilbird.warcraft.item.common.resource.ResourceType;
import com.evilbird.warcraft.item.unit.combatant.Combatant;
import org.apache.commons.lang3.builder.ToStringBuilder;

import javax.inject.Inject;
import java.util.LinkedHashMap;
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
    private Map<String, Double> resources;

    @Inject
    public Gatherer() {
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
    public String toString() {
        return new ToStringBuilder(this)
            .appendSuper("combatant")
            .append("resources", resources)
            .toString();
    }
}
