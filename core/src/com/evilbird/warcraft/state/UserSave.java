/*
 * Blair Butterworth (c) 2019
 *
 * This work is licensed under the MIT License. To view a copy of this
 * license, visit
 *
 *      https://opensource.org/licenses/MIT
 */

package com.evilbird.warcraft.state;

import com.evilbird.engine.state.StateIdentifier;
import com.evilbird.warcraft.state.hud.Hud;
import com.evilbird.warcraft.state.map.Map;

public class UserSave implements StateIdentifier, StateDefinition
{
    public UserSave(String path) {

    }

    @Override
    public Hud getHud() {
        return null;
    }

    @Override
    public Map getMap() {
        return null;
    }
}
