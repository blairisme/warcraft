/*
 * Blair Butterworth (c) 2019
 *
 * This work is licensed under the MIT License. To view a copy of this
 * license, visit
 *
 *      https://opensource.org/licenses/MIT
 */

package com.evilbird.engine.game;

import com.evilbird.engine.menu.Menu;
import com.evilbird.engine.menu.MenuIdentifier;
import com.evilbird.engine.state.State;
import com.evilbird.engine.state.StateIdentifier;

import java.util.concurrent.Future;

/**
 * Implementors of this interface are used to control what content is rendered
 * to the screen and to obtain system preferences.
 *
 * @author Blair Butterworth
 */
public interface GameController
{
    /**
     * Returns whether a menu is currently being shown.
     *
     * @return {@code true} if a menu is being shown, otherwise {@code false}.
     */
    boolean isMenuShown();

    /**
     * Returns whether an overlay menu is currently being shown.
     *
     * @return {@code true} if a menu is being shown, otherwise {@code false}.
     */
    boolean isMenuOverlayShown();

    /**
     * Returns whether a state is currently being shown.
     *
     * @return {@code true} if a state is being shown, otherwise {@code false}.
     */
    boolean isStateShown();

    /**
     * Returns the currently displayed menu, if any.
     *
     * @return a {@link Menu} instance. This method may return {@code null}.
     */
    Menu getMenu();

    /**
     * Returns the currently displayed state, if any.
     *
     * @return a {@link State} instance. This method may return {@code null}.
     */
    State getState();

    /**
     * Loads the state identified by the given identifier asynchronously.
     * Returns a {@link Future} object which can be used to ascertain the
     * status of the asynchronous operation.
     *
     * @param identifier    a state identifier.
     * @return              a {@code Future}, used to obtain the status of
     *                      the load operation.
     */
    Future<?> loadStateAssets(StateIdentifier identifier);

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
     * @param identifier a {@link StateIdentifier}. This parameter cannot be
     *                     <code>null</code>.
     */
    void showState(StateIdentifier identifier);

    /**
     * Saves the current game state into persisted storage and assigned the
     * given identifier which can be used to load it at a later date.
     *
     * @param identifier a {@link StateIdentifier}. This parameter cannot be
     *                   <code>null</code>.
     */
    void saveState(StateIdentifier identifier);

    /**
     * Shows an error screen appropriate to the given error.
     *
     * @param error an unexpected error.
     */
    void showError(Throwable error);
}
