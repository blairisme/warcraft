/*
 * Copyright (c) 2020, Blair Butterworth
 *
 * This Source Code Form is subject to the terms of the Mozilla Public
 * License, v. 2.0. If a copy of the MPL was not distributed with this
 * file, You can obtain one at http://mozilla.org/MPL/2.0/.
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
