/*
 * Blair Butterworth (c) 2019
 *
 * This work is licensed under the MIT License. To view a copy of this
 * license, visit
 *
 *      https://opensource.org/licenses/MIT
 */

package com.evilbird.engine.common.lang;

import com.evilbird.engine.item.Item;

/**
 * Implementors of this interface provide methods that mark a game object as
 * selectable, a process that aids  the user by allowing them to issue commands
 * to multiple game objects at the same time.
 *
 * @author Blair Butterworth
 */
public interface Selectable extends Item
{
    /**
     * Returns whether the game object has been selected by the user.
     *
     * @return {@code true} if the game object has been selected.
     */
    boolean getSelected();

    /**
     * Returns whether or not the user has the ability to select the game
     * object.
     *
     * @return {@code true} if the game object can been selected.
     */
    boolean getSelectable();

    /**
     * Sets whether the game object has been selected by the user.
     *
     * @param selected {@code true} if the game object has been selected.
     */
    void setSelected(boolean selected);

    /**
     * Sets whether or not the user can select the game object.
     *
     * @param selectable {@code true} if the game object can been selected.
     */
    void setSelectable(boolean selectable);
}
