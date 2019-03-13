/*
 * Blair Butterworth (c) 2019
 *
 * This work is licensed under the MIT License. To view a copy of this
 * license, visit
 *
 *      https://opensource.org/licenses/MIT
 */

package com.evilbird.warcraft.state.asset;

import com.evilbird.engine.state.StateIdentifier;
import com.evilbird.warcraft.state.hud.HudStateIdentifier;
import com.evilbird.warcraft.state.world.WorldStateIdentifier;

public enum AssetState implements StateIdentifier
{
    Human1 (WorldStateIdentifier.Human1, HudStateIdentifier.Human);

    private HudStateIdentifier hud;
    private WorldStateIdentifier world;

    AssetState(WorldStateIdentifier world, HudStateIdentifier hud) {
        this.hud = hud;
        this.world = world;
    }

    public HudStateIdentifier getHud() {
        return hud;
    }

    public WorldStateIdentifier getWorld() {
        return world;
    }
}
