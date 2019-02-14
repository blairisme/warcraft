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
import com.evilbird.engine.state.StateIdentifier;

public interface ScreenController
{
    void showMenu(MenuIdentifier identifier);

    void showState(StateIdentifier identifier);
}
