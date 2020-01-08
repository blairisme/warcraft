/*
 * Copyright (c) 2020, Blair Butterworth
 *
 * This Source Code Form is subject to the terms of the Mozilla Public
 * License, v. 2.0. If a copy of the MPL was not distributed with this
 * file, You can obtain one at http://mozilla.org/MPL/2.0/.
 */

package com.evilbird.warcraft.object.layer;

import com.evilbird.engine.object.GameObjectType;

/**
 * Defines identifiers for layer varieties.
 *
 * @author Blair Butterworth
 */
public enum LayerType implements GameObjectType
{
    Map, //Land
    Sea,
    Shore,
    Forest,
    Mountain,
    Tree,
    OpaqueFog,
    OpaqueFogSection,
    TransparentFog,
    Wall,
    WallSection
}
