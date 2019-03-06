/*
 * Blair Butterworth (c) 2019
 *
 * This work is licensed under the MIT License. To view a copy of this
 * license, visit
 *
 *      https://opensource.org/licenses/MIT
 */

package com.evilbird.warcraft.state.asset;

import com.evilbird.warcraft.state.StateIdentifier;

public enum AssetState implements StateIdentifier
{
    Human1 (Map.Human1, Hud.Human);

    private HudDefinition hud;
    private MapDefinition map;

    AssetState(MapDefinition map, HudDefinition hud) {
        this.hud = hud;
        this.map = map;
    }

    public HudDefinition getHud() {
        return hud;
    }

    public MapDefinition getMap() {
        return map;
    }
}
