/*
 * Copyright (c) 2019, Blair Butterworth
 *
 * This work is licensed under the MIT License. To view a copy of this
 * license, visit
 *
 *        https://opensource.org/licenses/MIT
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
public enum WarcraftAssetSet implements Identifier
{
    Summer,
    Swamp,
    Winter
}
