/*
 * Blair Butterworth (c) 2019
 *
 * This work is licensed under the MIT License. To view a copy of this
 * license, visit
 *
 *      https://opensource.org/licenses/MIT
 */

package com.evilbird.warcraft.behaviour.ui.interaction;

import com.evilbird.engine.device.UserInput;
import com.evilbird.engine.item.Item;

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
     * @param target    the {@link Item} thats the focus of the user input.
     * @param selected  the currently selected items.
     * @return          <code>true</code> if the interaction applies to the
     *                  given state, otherwise false.
     */
    boolean applies(UserInput input, Item target, Item selected);

    /**
     * Applies this interaction to the given game state.
     *
     * @param input     the last {@link UserInput input} provided by the user.
     * @param target    the {@link Item} thats the focus of the user input.
     * @param selected  the currently selected items.
     */
    void apply(UserInput input, Item target, Item selected);
}
