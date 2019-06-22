/*
 * Blair Butterworth (c) 2019
 *
 * This work is licensed under the MIT License. To view a copy of this
 * license, visit
 *
 *      https://opensource.org/licenses/MIT
 */

package com.evilbird.warcraft.menu.intro;

import com.evilbird.engine.menu.MenuIdentifier;

/**
 * Defines types of introduction menus, one for each level in the Human and Orc
 * campaigns.
 *
 * @author Blair Butterworth
 */
public enum IntroMenuType implements MenuIdentifier
{
    Human1,
    Human2,

    Orc1,
    Orc2
}
