/*
 * Blair Butterworth (c) 2019
 *
 * This work is licensed under the MIT License. To view a copy of this
 * license, visit
 *
 *      https://opensource.org/licenses/MIT
 */

package com.evilbird.warcraft.menu.ingame;

import com.evilbird.engine.menu.MenuIdentifier;

public enum IngameMenuType implements MenuIdentifier
{
    Root,
    Save,
    Load,
    Exit,
    Confirm,
    Options,
    Sounds,
    Speeds,
    Preferences,
    Objectives,
    Failure,
    Victory
}