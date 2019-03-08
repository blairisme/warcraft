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
import com.evilbird.warcraft.item.layer.Layer;
import com.evilbird.warcraft.item.unit.Unit;

import javax.inject.Inject;

/**
 * Instances of this {@link Layer} represent the ground below which all land
 * based {@link Unit Units} traverse.
 *
 * @author Blair Butterworth
 */
public class Terrain extends Layer
{
    @Inject
    @SerializedConstructor
    public Terrain() {
    }
}
