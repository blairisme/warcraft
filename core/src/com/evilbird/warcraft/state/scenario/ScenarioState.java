/*
 * Copyright (c) 2019, Blair Butterworth
 *
 * This work is licensed under the MIT License. To view a copy of this
 * license, visit
 *
 *        https://opensource.org/licenses/MIT
 */

package com.evilbird.warcraft.state.scenario;

import com.evilbird.engine.behaviour.Behaviour;
import com.evilbird.engine.item.ItemRoot;
import com.evilbird.warcraft.common.WarcraftContext;
import com.evilbird.warcraft.state.WarcraftState;
import com.google.gson.annotations.JsonAdapter;
import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;
import org.apache.commons.lang3.builder.ToStringBuilder;

/**
 * Instances of class represent the game state, a snapshot of the all game
 * objects and their properties at a given point in time. Warcraft game state
 * is persisted for objects in the game world, but not the user interface or
 * active behaviours which are stateless.
 *
 * @author Blair Butterworth
 */
@JsonAdapter(ScenarioAdapter.class)
public class ScenarioState implements WarcraftState
{
    private ItemRoot world;
    private ItemRoot hud;
    private Behaviour behaviour;
    private WarcraftContext context;

    public ScenarioState() {
    }

    public ScenarioState(
        ItemRoot world,
        ItemRoot hud,
        Behaviour behaviour,
        WarcraftContext context)
    {
        this.world = world;
        this.hud = hud;
        this.behaviour = behaviour;
        this.context = context;
    }

    @Override
    public void dispose() {
        this.world.dispose();
        this.hud.dispose();
    }

    public WarcraftContext getContext() {
        return context;
    }

    public Behaviour getBehaviour() {
        return behaviour;
    }

    public ItemRoot getHud() {
        return hud;
    }

    public ItemRoot getWorld() {
        return world;
    }

    public void setContext(WarcraftContext context) {
        this.context = context;
    }

    public void setBehaviour(Behaviour behaviour) {
        this.behaviour = behaviour;
    }

    public void setHud(ItemRoot hud) {
        this.hud = hud;
    }

    public void setWorld(ItemRoot world) {
        this.world = world;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == null) { return false; }
        if (obj == this) { return true; }
        if (obj.getClass() != getClass()) { return false; }

        ScenarioState that = (ScenarioState)obj;
        return new EqualsBuilder()
            .append(context, that.context)
            .append(world, that.world)
            .append(hud, that.hud)
            .append(behaviour, that.behaviour)
            .isEquals();
    }

    @Override
    public int hashCode() {
        return new HashCodeBuilder(17, 37)
            .append(context)
            .append(world)
            .append(hud)
            .append(behaviour)
            .toHashCode();
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this)
            .append("world", world)
            .append("hud", hud)
            .append("behaviour", behaviour)
            .toString();
    }
}
