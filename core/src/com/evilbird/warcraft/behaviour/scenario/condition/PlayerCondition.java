/*
 * Copyright (c) 2019, Blair Butterworth
 *
 * This work is licensed under the MIT License. To view a copy of this
 * license, visit
 *
 *        https://opensource.org/licenses/MIT
 */

package com.evilbird.warcraft.behaviour.scenario.condition;

import com.evilbird.engine.events.EventQueue;
import com.evilbird.engine.item.ItemRoot;
import com.evilbird.warcraft.item.common.query.UnitOperations;
import com.evilbird.warcraft.item.data.player.Player;

/**
 * Represents a {@link ScenarioCondition} that operates on the current player.
 *
 * @author Blair Butterworth
 */
public abstract class PlayerCondition implements ScenarioCondition
{
    protected Player player;

    public PlayerCondition() {
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

    private void initialize(ItemRoot state) {
        if (player == null) {
            player = UnitOperations.getHumanPlayer(state);
        }
    }
}
