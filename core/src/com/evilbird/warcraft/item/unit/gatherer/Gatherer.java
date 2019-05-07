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
import com.evilbird.engine.item.Item;
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
    private Item construction;
    private Map<String, Double> resources;

    @Inject
    public Gatherer(Skin skin) {
        super(skin);
        resources = new LinkedHashMap<>();
    }

    public Item getConstruction() {
        return construction;
    }

    @Override
    public float getResource(ResourceType type) {
        String key = type.name();
        if (resources.containsKey(key)){
            return resources.get(key).floatValue();
        }
        return 0;
    }

    public boolean isConstructing() {
        return construction != null;
    }

    public void setConstruction(Item construction) {
        this.construction = construction;
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
            .append("construction", construction)
            .append("resources", resources)
            .toString();
    }
}
