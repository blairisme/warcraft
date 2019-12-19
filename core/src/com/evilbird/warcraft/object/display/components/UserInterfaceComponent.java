/*
 * Copyright (c) 2019, Blair Butterworth
 *
 * This work is licensed under the MIT License. To view a copy of this
 * license, visit
 *
 *        https://opensource.org/licenses/MIT
 */

package com.evilbird.warcraft.object.display.components;

import com.evilbird.engine.object.GameObjectType;

/**
 * Defines options for specifying interface control types.
 *
 * @author Blair Butterworth
 */
public enum UserInterfaceComponent implements GameObjectType
{
    ActionPane,
    ControlPane,
    DetailsPane,
    MenuPane,
    MapPane,
    MinimapPane,
    ResourcePane,
    SelectionPane,
    StatePane,
}
