/*
 * Blair Butterworth (c) 2019
 *
 * This work is licensed under the MIT License. To view a copy of this
 * license, visit
 *
 *      https://opensource.org/licenses/MIT
 */

package com.evilbird.engine.game;

import com.badlogic.gdx.Preferences;
import com.evilbird.engine.menu.MenuIdentifier;
import com.evilbird.engine.state.StateIdentifier;

/**
 * Implementors of this interface are used to control what content is rendered
 * to the screen and to obtain system preferences.
 *
 * @author Blair Butterworth
 */
public interface GameController
{
    Preferences getPreferences(String identifier);

    void showMenuRoot();

    void showMenu(MenuIdentifier identifier);

    void showState(StateIdentifier identifier);

    void saveState(StateIdentifier identifier);
}
