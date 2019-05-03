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
import com.evilbird.engine.common.serialization.SerializedType;
import com.evilbird.warcraft.item.unit.Unit;

/**
 * Instances of this class represent a building, a {@link Unit} specialization
 * that provides the user the ability to train {@link Unit Units}.
 *
 * @author Blair Butterworth
 */
@SerializedType("Building")
public class Building extends Unit
{
    private float producing;
    private float constructing;

    public Building(Skin skin) {
        super(skin);
        producing = 1;
        constructing = 1;
    }

    public boolean isConstructing() {
        return constructing != 1;
    }

    public boolean isProducing() {
        return producing != 1;
    }

    public float getConstructionProgress() {
        return constructing;
    }

    public float getProductionProgress() {
        return producing;
    }

    public void setConstructionProgress(float constructing) {
        this.constructing = constructing;
    }

    public void setProductionProgress(float progress) {
        this.producing = progress;
    }
}
