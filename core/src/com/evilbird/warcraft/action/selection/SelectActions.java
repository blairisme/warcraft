/*
 * Copyright (c) 2019, Blair Butterworth
 *
 * This work is licensed under the MIT License. To view a copy of this
 * license, visit
 *
 *        https://opensource.org/licenses/MIT
 */

package com.evilbird.warcraft.action.selection;

import com.evilbird.engine.action.ActionIdentifier;

/**
 * Defines options of specifying selection action varieties.
 *
 * @author Blair Butterworth
 */
public enum SelectActions implements ActionIdentifier
{
    SelectDeselect,
    SelectInvert,
    SelectFlash
}
