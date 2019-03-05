/*
 * Blair Butterworth (c) 2019
 *
 * This work is licensed under the MIT License. To view a copy of this
 * license, visit
 *
 *      https://opensource.org/licenses/MIT
 */

package com.evilbird.engine.game;

import com.evilbird.engine.common.lang.Identifier;
import com.evilbird.engine.menu.MenuIdentifier;

/**
 * Implementors of this interface are used to control what content is rendered
 * to the screen and to obtain system preferences.
 *
 * @author Blair Butterworth
 */
public interface GameController
{
    /**
     * Shows the default menu, usually the top level menu.
     */
    void showMenu();

    /**
     * Shows the specified menu. If a menu or game state is current shown then
     * this will be disposed.
     *
     * @param identifier a {@link MenuIdentifier}. This parameter cannot be
     *                   <code>null</code>.
     */
    void showMenu(MenuIdentifier identifier);

    /**
     * Shows the specified menu overlaid on top of the existing menu or game
     * state. The current menu or game state will be rendered but not updated.
     *
     * @param identifier a {@link MenuIdentifier}. This parameter cannot be
     *                   <code>null</code>.
     */
    void showMenuOverlay(MenuIdentifier identifier);

    /**
     * Hides the overlay menu and shows the current game state.
     */
    void showState();

    /**
     * Shows the specified game state. If a menu or game state is current shown then
     * this will be disposed.
     *
     * @param identifier a {@link Identifier}. This parameter cannot be
     *                     <code>null</code>.
     */
    void showState(Identifier identifier);

    /**
     * Saves the current game state into persisted storage and assigned the
     * given identifier which can be used to load it at a later date.
     *
     * @param identifier a {@link Identifier}. This parameter cannot be
     *                   <code>null</code>.
     */
    void saveState(Identifier identifier);
}
