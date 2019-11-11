/*
 * Copyright (c) 2019, Blair Butterworth
 *
 * This work is licensed under the MIT License. To view a copy of this
 * license, visit
 *
 *        https://opensource.org/licenses/MIT
 */

package com.evilbird.warcraft.state;

import com.badlogic.gdx.audio.Music;
import com.evilbird.engine.behaviour.Behaviour;
import com.evilbird.engine.object.GameObjectContainer;
import com.evilbird.engine.state.State;
import com.google.gson.annotations.JsonAdapter;
import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;
import org.apache.commons.lang3.builder.ToStringBuilder;

/**
 * Instances of class represent a snapshot of all Warcraft game
 * objects and their properties at a given point in time.
 *
 * @author Blair Butterworth
 */
@JsonAdapter(WarcraftStateAdapter.class)
public class WarcraftState implements State
{
    private GameObjectContainer hud;
    private GameObjectContainer world;
    private Behaviour behaviour;
    private WarcraftContext context;
    private transient Music music;

    public WarcraftState() {
    }

    public WarcraftState(
        GameObjectContainer world,
        GameObjectContainer hud,
        Behaviour behaviour,
        Music music,
        WarcraftContext context)
    {
        this.world = world;
        this.hud = hud;
        this.behaviour = behaviour;
        this.context = context;
        this.music = music;
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

    public GameObjectContainer getHud() {
        return hud;
    }

    public GameObjectContainer getWorld() {
        return world;
    }

    public Music getMusic() {
        return music;
    }

    public void setContext(WarcraftContext context) {
        this.context = context;
    }

    public void setBehaviour(Behaviour behaviour) {
        this.behaviour = behaviour;
    }

    public void setHud(GameObjectContainer hud) {
        this.hud = hud;
    }

    public void setWorld(GameObjectContainer world) {
        this.world = world;
    }

    public void setMusic(Music music) {
        this.music = music;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == null) { return false; }
        if (obj == this) { return true; }
        if (obj.getClass() != getClass()) { return false; }

        WarcraftState that = (WarcraftState)obj;
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
