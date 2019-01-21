/*
 * Blair Butterworth (c) 2019
 *
 * This work is licensed under the MIT License. To view a copy of this
 * license, visit
 *
 *      https://opensource.org/licenses/MIT
 */

package com.evilbird.warcraft.item.hud;

import com.evilbird.engine.item.ItemType;

/**
 * Defines options for specifying interface control types.
 *
 * @author Blair Butterworth
 */
public enum HudControls implements ItemType
{
    ActionButton,
    ActionPane,
    ControlPane,
    DetailsPane,
    HealthBar,
    BuildingBar,
    MinimapPane,
    ResourcePane,
    SelectionPane,
    StatePane,
    UnitPane,
}
