/*
 * Copyright (c) 2020, Blair Butterworth
 *
 * This Source Code Form is subject to the terms of the Mozilla Public
 * License, v. 2.0. If a copy of the MPL was not distributed with this
 * file, You can obtain one at http://mozilla.org/MPL/2.0/.
 */

package com.evilbird.warcraft.object.common.capability;

import com.evilbird.engine.object.GameObject;

/**
 * Implementors of this interface provide methods that mark a game object as
 * selectable, a process that aids  the user by allowing them to issue commands
 * to multiple game objects at the same time.
 *
 * @author Blair Butterworth
 */
public interface SelectableObject extends GameObject
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
