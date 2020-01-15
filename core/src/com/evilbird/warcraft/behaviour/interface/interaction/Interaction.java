/*
 * Copyright (c) 2020, Blair Butterworth
 *
 * This Source Code Form is subject to the terms of the Mozilla Public
 * License, v. 2.0. If a copy of the MPL was not distributed with this
 * file, You can obtain one at http://mozilla.org/MPL/2.0/.
 */

package com.evilbird.warcraft.behaviour.ui.interaction;

import com.evilbird.engine.device.UserInput;
import com.evilbird.engine.object.GameObject;

/**
 * Implementors of this interface provide methods that specify which
 * situations an interactions applies to and how the interaction should alter
 * the game state.
 *
 * @author Blair Butterworth
 */
public interface Interaction
{
    /**
     * Determines if this interaction applies to the given game state.
     *
     * @param input     the last {@link UserInput input} provided by the user.
     * @param target    the {@link GameObject} thats the focus of the user input.
     * @param selected  the currently selected items.
     * @return          <code>true</code> if the interaction applies to the
     *                  given state, otherwise false.
     */
    boolean applies(UserInput input, GameObject target, GameObject selected);

    /**
     * Applies this interaction to the given game state.
     *
     * @param input     the last {@link UserInput input} provided by the user.
     * @param target    the {@link GameObject} thats the focus of the user input.
     * @param selected  the currently selected items.
     */
    void apply(UserInput input, GameObject target, GameObject selected);
}
