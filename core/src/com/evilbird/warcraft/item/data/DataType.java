/*
 * Copyright (c) 2019, Blair Butterworth
 *
 * This work is licensed under the MIT License. To view a copy of this
 * license, visit
 *
 *        https://opensource.org/licenses/MIT
 */

package com.evilbird.warcraft.item.data;

import com.evilbird.engine.common.serialization.SerializedType;
import com.evilbird.engine.item.ItemType;

/**
 * Defines options of specifying data item varieties.
 *
 * @author Blair Butterworth
 */
@SerializedType("Data")
public enum DataType implements ItemType
{
    Camera,
    Player
}
