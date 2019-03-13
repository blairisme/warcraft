/*
 * Blair Butterworth (c) 2019
 *
 * This work is licensed under the MIT License. To view a copy of this
 * license, visit
 *
 *      https://opensource.org/licenses/MIT
 */

package com.evilbird.warcraft.state;

import com.evilbird.engine.behaviour.Behaviour;
import com.evilbird.engine.item.ItemRoot;
import com.evilbird.engine.state.State;
import com.google.gson.annotations.JsonAdapter;

@JsonAdapter(WarcraftStateAdapter.class)
public class WarcraftState implements State
{
    private ItemRoot world;
    private ItemRoot hud;
    private Behaviour behaviour;

    public WarcraftState(ItemRoot world, ItemRoot hud, Behaviour behaviour) {
        this.world = world;
        this.hud = hud;
        this.behaviour = behaviour;
    }

    public ItemRoot getWorld() {
        return world;
    }

    public ItemRoot getHud() {
        return hud;
    }

    public Behaviour getBehaviour() {
        return behaviour;
    }
}
