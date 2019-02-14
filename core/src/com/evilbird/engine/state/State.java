/*
 * Blair Butterworth (c) 2019
 *
 * This work is licensed under the MIT License. To view a copy of this
 * license, visit
 *
 *      https://opensource.org/licenses/MIT
 */

package com.evilbird.engine.state;

import com.evilbird.engine.behaviour.Behaviour;
import com.evilbird.engine.item.ItemRoot;

public class State
{
    private ItemRoot world;
    private ItemRoot hud;

    public State(ItemRoot world, ItemRoot hud) {
        this.world = world;
        this.hud = hud;
    }

    public ItemRoot getWorld() {
        return world;
    }

    public ItemRoot getHud() {
        return hud;
    }

    public Behaviour getBehaviour() {
        throw new UnsupportedOperationException(); //TODO
    }
}
