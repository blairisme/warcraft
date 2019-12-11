/*
 * Copyright (c) 2019, Blair Butterworth
 *
 * This work is licensed under the MIT License. To view a copy of this
 * license, visit
 *
 *        https://opensource.org/licenses/MIT
 */

package com.evilbird.warcraft.object.unit.resource;

import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.evilbird.engine.common.collection.Maps;
import com.evilbird.engine.object.spatial.SpatialObject;
import com.evilbird.warcraft.object.common.capability.PerishableObject;
import com.evilbird.warcraft.object.common.resource.ResourceContainer;
import com.evilbird.warcraft.object.common.resource.ResourceType;
import com.evilbird.warcraft.object.unit.Unit;
import com.evilbird.warcraft.object.unit.UnitStyle;
import com.evilbird.warcraft.object.unit.combatant.gatherer.Gatherer;
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
public class Resource extends Unit implements ResourceContainer, SpatialObject
{
    private Map<String, Double> resources;

    /**
     * Constructs a new instance of this class given a {@link Skin} containing
     * an {@link UnitStyle}, specifying the visual and auditory
     * presentation of the new Resource.
     *
     * @param skin a {@code Skin} instance containing a {@code UnitStyle}.
     *
     * @throws NullPointerException if the given skin is {@code null} or
     *                              doesn't contain a {@code UnitStyle}.
     */
    public Resource(Skin skin) {
        super(skin);
        resources = new LinkedHashMap<>(2);
    }

    /**
     * Determines if the given {@code PerishableObject} is "alive" of not.
     * Resources are not the enemy of anything: always returns {@code false}.
     */
    @Override
    public boolean isEnemy(PerishableObject other) {
        return false;
    }

    /**
     * Returns the {@link Gatherer} that is currently obtaining resources from
     * the Resource, if any.
     */
    public Gatherer getGatherer() {
        return (Gatherer)getAssociatedItem();
    }

    /**
     * Returns the amount of the given resource type held by the Resource
     * instance.
     */
    @Override
    public float getResource(ResourceType type) {
        return Maps.getOrDefault(resources, type.name(), 0.0).floatValue();
    }

    /**
     * Sets the {@link Gatherer} that is currently obtaining resources from
     * the Resource, if any.
     */
    public void setGatherer(Gatherer gatherer) {
        setAssociatedItem(gatherer);
    }

    /**
     * Sets the amount of the given resource type held by the Resource
     * instance.
     */
    @Override
    public void setResource(ResourceType type, float value) {
        resources.put(type.name(), (double)value);
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == null) { return false; }
        if (obj == this) { return true; }
        if (obj.getClass() != getClass()) { return false; }

        Resource resource = (Resource)obj;
        return new EqualsBuilder()
            .appendSuper(super.equals(obj))
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
