/*
 * Blair Butterworth (c) 2019
 *
 * This work is licensed under the MIT License. To view a copy of this
 * license, visit
 *
 *      https://opensource.org/licenses/MIT
 */

package com.evilbird.warcraft.item.hud;

import com.evilbird.engine.common.serialization.SerializedType;
import com.evilbird.engine.item.ItemType;

@SerializedType("Hud")
public enum HudType implements ItemType
{
    Human,
    Orc
}
