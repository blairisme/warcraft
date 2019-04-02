/*
 * Blair Butterworth (c) 2018
 *
 * This work is licensed under the MIT License. To view a copy of this
 * license, visit
 *
 *      https://opensource.org/licenses/MIT
 */

package com.evilbird.warcraft.item.unit.building;

import com.evilbird.engine.common.serialization.SerializedType;
import com.evilbird.engine.item.Item;
import com.evilbird.engine.item.ItemReference;
import com.evilbird.warcraft.item.unit.Unit;

/**
 * Instances of this class represent a building.
 *
 * @author Blair Butterworth
 */
@SerializedType("Building")
public class Building extends Unit
{
    private boolean constructing;
    private boolean producing;
    private float progress;
    private ItemReference constructor;

    public Building() {
        constructing = false;
        producing = false;
        progress = 1f;
        constructor = null;
    }

    public boolean isConstructing() {
        return constructing;
    }

    public boolean isProducing() {
        return producing;
    }

    public Item getConstructor() {
        return constructor != null ? constructor.get() : null;
    }

    public float getProgress() {
        return progress;
    }

    public void setConstructor(Item constructor) {
        this.constructor = new ItemReference(constructor);
    }

    public void setConstructing(boolean constructing) {
        this.constructing = constructing;
    }

    public void setProducing(boolean producing) {
        this.producing = producing;
    }

    public void setProgress(float progress) {
        this.progress = progress;
    }
}
