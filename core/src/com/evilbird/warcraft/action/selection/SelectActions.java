/*
 * Copyright (c) 2020, Blair Butterworth
 *
 * This Source Code Form is subject to the terms of the Mozilla Public
 * License, v. 2.0. If a copy of the MPL was not distributed with this
 * file, You can obtain one at http://mozilla.org/MPL/2.0/.
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
