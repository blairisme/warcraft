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
    private transient ItemRoot hud;
    private transient Behaviour behaviour;

    public State(ItemRoot world, ItemRoot hud, Behaviour behaviour) {
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
