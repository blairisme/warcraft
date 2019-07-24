/*
 * Copyright (c) 2019, Blair Butterworth
 *
 * This work is licensed under the MIT License. To view a copy of this
 * license, visit
 *
 *        https://opensource.org/licenses/MIT
 */

package com.evilbird.warcraft.item.ui.display.control.status.details;

import com.evilbird.engine.item.Item;

public interface DetailsPaneElement extends Item
{
    void setItem(Item item);
}
