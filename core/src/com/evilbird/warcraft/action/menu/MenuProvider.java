/*
 * Blair Butterworth (c) 2019
 *
 * This work is licensed under the MIT License. To view a copy of this
 * license, visit
 *
 *      https://opensource.org/licenses/MIT
 */

package com.evilbird.warcraft.action.menu;

import com.evilbird.engine.common.lang.Identifier;

/**
 * Implementors of this interface represent an object that can navigated
 * through.
 *
 * @author Blair Butterworth
 */
public interface MenuProvider
{
    void showMenu(Identifier location);
}
