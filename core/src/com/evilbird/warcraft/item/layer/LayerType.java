/*
 * Blair Butterworth (c) 2018
 *
 * This work is licensed under the MIT License. To view a copy of this
 * license, visit
 *
 *      https://opensource.org/licenses/MIT
 */

package com.evilbird.warcraft.item.layer;

import com.evilbird.engine.common.serialization.SerializedType;
import com.evilbird.engine.item.ItemType;

@SerializedType("LayerType")
public enum LayerType implements ItemType
{
    Map,
    Sea,
    Forest,
    Tree,
    OpaqueFog,
    TransparentFog,
}
