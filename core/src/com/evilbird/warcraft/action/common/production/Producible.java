/*
 * Blair Butterworth (c) 2019
 *
 * This work is licensed under the MIT License. To view a copy of this
 * license, visit
 *
 *      https://opensource.org/licenses/MIT
 */

package com.evilbird.warcraft.action.common.production;

import com.evilbird.engine.item.ItemType;

public interface Producible
{
    float getDuration();

    ItemType getItemType();
}
