/*
 * Copyright (c) 2019, Blair Butterworth
 *
 * This work is licensed under the MIT License. To view a copy of this
 * license, visit
 *
 *        https://opensource.org/licenses/MIT
 */

package com.evilbird.warcraft.behaviour.scenario.condition;

import com.evilbird.engine.common.lang.Identifier;
import com.evilbird.engine.events.EventQueue;
import com.evilbird.engine.item.ItemRoot;
import com.evilbird.warcraft.item.data.player.Player;

import static com.evilbird.engine.item.utility.ItemPredicates.withId;

/**
 * Represents a {@link ScenarioCondition} that operates on a player, either the
 * current user or an enemy combatant.
 *
 * @author Blair Butterworth
 */
public abstract class PlayerCondition implements ScenarioCondition
{
    protected Player player;
    protected Identifier playerId;

    public PlayerCondition(Identifier playerId) {
        this.playerId = playerId;
    }

    @Override
    public boolean test(ItemRoot state, EventQueue events) {
        initialize(state);
        if (applicable(events)) {
            return evaluate(state);
        }
        return false;
    }

    protected abstract boolean applicable(EventQueue queue);

    protected abstract boolean evaluate(ItemRoot state);

    protected void initialize(ItemRoot state) {
        if (player == null) {
            player = (Player)state.find(withId(playerId));
        }
    }
}
