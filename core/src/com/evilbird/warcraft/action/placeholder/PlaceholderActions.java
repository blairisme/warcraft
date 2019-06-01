/*
 * Blair Butterworth (c) 2019
 *
 * This work is licensed under the MIT License. To view a copy of this
 * license, visit
 *
 *      https://opensource.org/licenses/MIT
 */

package com.evilbird.warcraft.action.placeholder;

import com.evilbird.engine.action.ActionIdentifier;
import com.evilbird.warcraft.item.placeholder.PlaceholderType;

/**
 * Defines options of specifying placeholder action varieties.
 *
 * @author Blair Butterworth
 */
public enum PlaceholderActions implements ActionIdentifier
{
    AddBarracksPlaceholder(PlaceholderType.BarracksPlaceholder),
    AddFarmPlaceholder(PlaceholderType.FarmPlaceholder),
    AddTownHallPlaceholder(PlaceholderType.TownHallPlaceholder),
    PlaceholderMove,
    PlaceholderCancel;

    private PlaceholderType placeholderType;

    PlaceholderActions() {
    }

    PlaceholderActions(PlaceholderType placeholderType) {
        this.placeholderType = placeholderType;
    }

    public PlaceholderType type() {
        return placeholderType;
    }
}
