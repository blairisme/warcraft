/*
 * Copyright (c) 2019, Blair Butterworth
 *
 * This work is licensed under the MIT License. To view a copy of this
 * license, visit
 *
 *        https://opensource.org/licenses/MIT
 */

package com.evilbird.warcraft.item.unit.gatherer;

import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.evilbird.warcraft.item.common.resource.ResourceContainer;
import com.evilbird.warcraft.item.common.resource.ResourceType;
import com.evilbird.warcraft.item.unit.combatant.Combatant;
import org.apache.commons.lang3.builder.ToStringBuilder;

import javax.inject.Inject;
import java.util.LinkedHashMap;
import java.util.Map;

/**
 * Instances of this class represent a gatherer: a {@link Combatant}
 * specialization that can both fight and collect resources.
 *
 * @author Blair Butterworth
 */
public class Gatherer extends Combatant implements ResourceContainer
{
    private float progress;
    private Map<String, Double> resources;

    @Inject
    public Gatherer(Skin skin) {
        super(skin);
        resources = new LinkedHashMap<>(2);
        progress = 1;
    }

    public boolean isGathering() {
        return progress != 1;
    }

    public float getGathererProgress() {
        return progress;
    }

    public float getGatherCapacity(ResourceType resource) {
        return 100; //TODO
    }

    public float getGatherDuration(ResourceType resource) {
        return 5; //TODO
    }

    public void clearResources() {
        resources.clear();
    }

    @Override
    public float getResource(ResourceType type) {
        return resources.getOrDefault(type.name(), 0.0).floatValue();
    }

    public boolean hasResource(ResourceType type) {
        return resources.containsKey(type.name());
    }

    public boolean hasOtherResource(ResourceType type) {
        return !resources.isEmpty() && !resources.containsKey(type.name());
    }

    public void setGathererProgress(float progress) {
        this.progress = progress;
    }

    @Override
    public void setResource(ResourceType type, float value) {
        resources.put(type.name(), (double)value);
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this)
            .appendSuper("combatant")
            .append("progress", progress)
            .append("resources", resources)
            .toString();
    }
}
