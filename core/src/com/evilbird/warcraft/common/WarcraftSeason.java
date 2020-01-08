/*
 * Copyright (c) 2020, Blair Butterworth
 *
 * This Source Code Form is subject to the terms of the Mozilla Public
 * License, v. 2.0. If a copy of the MPL was not distributed with this
 * file, You can obtain one at http://mozilla.org/MPL/2.0/.
 */

package com.evilbird.warcraft.common;

import com.evilbird.engine.common.lang.Identifier;

/**
 * Defines options for specifying the graphical contexts of the game. This
 * determines which graphical assets are loaded and used to display game
 * entities. E.g., buildings shown with snow on their roofs or not.
 *
 * @author Blair Butterworth
 */
public enum WarcraftSeason implements Identifier
{
    Summer,
    Swamp,
    Winter
}
