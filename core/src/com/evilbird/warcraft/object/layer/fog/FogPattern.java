/*
 * Copyright (c) 2020, Blair Butterworth
 *
 * This Source Code Form is subject to the terms of the Mozilla Public
 * License, v. 2.0. If a copy of the MPL was not distributed with this
 * file, You can obtain one at http://mozilla.org/MPL/2.0/.
 */

package com.evilbird.warcraft.object.layer.fog;

/**
 * Defines types of fog edge patterns.
 *
 * @author Blair Butterworth
 */
public enum FogPattern
{
    Full,
    Empty,

    Left,
    Right,

    Bottom,
    BottomLeftExternal,
    BottomRightExternal,
    BottomRightInterval,
    BottomLeftInternal,

    Top,
    TopLeftExternal,
    TopRightExternal,
    TopRightInternal,
    TopLeftInternal,

    BackSlash,
    ForwardSlash,
}
