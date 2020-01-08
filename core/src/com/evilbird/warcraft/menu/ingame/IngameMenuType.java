/*
 * Copyright (c) 2020, Blair Butterworth
 *
 * This Source Code Form is subject to the terms of the Mozilla Public
 * License, v. 2.0. If a copy of the MPL was not distributed with this
 * file, You can obtain one at http://mozilla.org/MPL/2.0/.
 */

package com.evilbird.warcraft.menu.ingame;

import com.evilbird.engine.menu.MenuIdentifier;

/**
 * Defines options for in-game menu varieties.
 *
 * @author Blair Butterworth
 */
public enum IngameMenuType implements MenuIdentifier
{
    Root,
    Save,
    Load,
    Exit,
    Confirm,
    Options,
    Sounds,
    Objectives,
    Defeat,
    Victory
}