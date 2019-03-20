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
    private Item builder;

    public Building() {
        constructing = false;
        producing = false;
        progress = 1f;
        builder = null;
    }

    public boolean isConstructing() {
        return constructing;
    }

    public boolean isProducing() {
        return producing;
    }

    public Item getBuilder() {
        return builder;
    }

    public float getProgress() {
        return progress;
    }

    public void setBuilder(Item builder) {
        this.builder = builder;
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
