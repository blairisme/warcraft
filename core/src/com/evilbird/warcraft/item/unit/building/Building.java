/*
 * Copyright (c) 2019, Blair Butterworth
 *
 * This work is licensed under the MIT License. To view a copy of this
 * license, visit
 *
 *        https://opensource.org/licenses/MIT
 */

package com.evilbird.warcraft.item.unit.building;

import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.evilbird.engine.item.Item;
import com.evilbird.engine.item.ItemReference;
import com.evilbird.engine.item.ItemRoot;
import com.evilbird.warcraft.item.common.resource.ResourceContainer;
import com.evilbird.warcraft.item.common.resource.ResourceType;
import com.evilbird.warcraft.item.unit.Unit;

import java.util.LinkedHashMap;
import java.util.Map;

/**
 * Instances of this class represent a building, a {@link Unit} specialization
 * that provides the user the ability to train {@link Unit Units}.
 *
 * @author Blair Butterworth
 */
public class Building extends Unit implements ResourceContainer
{
    private float producing;
    private float constructing;
    private ItemReference constructor;
    private Map<String, Double> resources;

    public Building(Skin skin) {
        super(skin);
        producing = 1;
        constructing = 1;
        resources = new LinkedHashMap<>(2);
    }

    public boolean isConstructing() {
        return constructing != 1;
    }

    public boolean isProducing() {
        return producing != 1;
    }

    public Item getConstructor() {
        return constructor != null ? constructor.get() : null;
    }

    public float getConstructionProgress() {
        return constructing;
    }

    public float getProductionProgress() {
        return producing;
    }

    public float getResource(ResourceType type) {
        return resources.getOrDefault(type.name(), 0.0).floatValue();
    }

    public void setConstructor(Item constructor) {
        this.constructor = constructor != null ? new ItemReference(constructor) : null;
    }

    public void setConstructionProgress(float constructing) {
        this.constructing = constructing;
    }

    public void setProductionProgress(float progress) {
        this.producing = progress;
    }

    public void setResource(ResourceType type, float value) {
        resources.put(type.name(), (double)value);
    }

    @Override
    public void setRoot(ItemRoot root) {
        super.setRoot(root);
        if (constructor != null) {
            constructor.setParent(root);
        }
    }
}
