/*
 * Blair Butterworth (c) 2019
 *
 * This work is licensed under the MIT License. To view a copy of this
 * license, visit
 *
 *      https://opensource.org/licenses/MIT
 */

package com.evilbird.warcraft.item.layer.terrain;

import com.evilbird.engine.common.serialization.SerializedConstructor;
import com.evilbird.engine.common.serialization.SerializedType;
import com.evilbird.engine.item.spatial.ItemGraphOccupant;
import com.evilbird.warcraft.item.layer.Layer;
import com.evilbird.warcraft.item.layer.LayerAdapter;
import com.evilbird.warcraft.item.unit.Unit;
import com.google.gson.annotations.JsonAdapter;

import javax.inject.Inject;

/**
 * Instances of this {@link Layer} represent the ground below which all land
 * based {@link Unit Units} traverse.
 *
 * @author Blair Butterworth
 */
@SerializedType("Terrain")
@JsonAdapter(LayerAdapter.class)
public class Terrain extends Layer implements ItemGraphOccupant
{
    @Inject
    @SerializedConstructor
    public Terrain() {
    }
}
