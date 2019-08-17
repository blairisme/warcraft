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

import java.util.Objects;

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

    /**
     * Constructs a new instance of this class given a player identifier.
     *
     * @param player an {@link Identifier}.
     *
     * @throws NullPointerException if the given player identifier is
     *                              {@code null}.
     */
    public PlayerCondition(Identifier player) {
        Objects.requireNonNull(player);
        this.playerId = player;
    }

    @Override
    public boolean test(ItemRoot state, EventQueue events) {
        initialize(state);
        if (applicable(events)) {
            return evaluate(state);
        }
        return false;
    }

    /**
     * Performs initialization logic pertinent to the condition.
     *
     * @param state the current game state.
     */
    protected void initialize(ItemRoot state) {
        if (player == null) {
            player = (Player)state.find(withId(playerId));
        }
    }

    /**
     * Determines if the condition applies given the events that occurred in
     * the last update cycle.
     *
     * @param queue an {@link EventQueue}.
     *
     * @return  {@code true} if the condition applies to the events in the event
     *          queue.
     */
    protected abstract boolean applicable(EventQueue queue);

    /**
     * Determines if the condition is fulfilled by the current game state.
     *
     * @param state an {@link ItemRoot} containing the current game state.
     *
     * @return  {@code true} if the condition is fulfilled by the current game
     *          state.
     */
    protected abstract boolean evaluate(ItemRoot state);
}
