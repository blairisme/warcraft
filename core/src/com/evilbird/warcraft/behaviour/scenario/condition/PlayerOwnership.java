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
import com.evilbird.engine.item.Item;
import com.evilbird.engine.item.ItemRoot;
import com.evilbird.engine.item.ItemType;
import com.evilbird.warcraft.action.construct.ConstructEvent;
import com.evilbird.warcraft.action.train.TrainEvent;

import java.util.function.Predicate;

import static com.evilbird.engine.common.function.Predicates.both;
import static com.evilbird.engine.item.utility.ItemOperations.hasMinimum;
import static com.evilbird.engine.item.utility.ItemPredicates.withId;
import static com.evilbird.engine.item.utility.ItemPredicates.withType;
import static com.evilbird.warcraft.item.common.query.UnitPredicates.isAlive;

/**
 * Represents a {@link ScenarioCondition} that is fulfilled when the user owns
 * a given number or specific set of units.
 *
 * @author Blair Butterworth
 */
public class PlayerOwnership extends PlayerCondition
{
    private Predicate<Item> condition;
    private int count;

    public PlayerOwnership(Predicate<Item> condition, int count) {
        this.condition = condition;
        this.count = count;
    }

    public static PlayerOwnership playerOwns(ItemType type, int amount) {
        return new PlayerOwnership(both(withType(type), isAlive()), amount);
    }

    public static PlayerOwnership playerOwns(Identifier id, int amount) {
        return new PlayerOwnership(both(withId(id), isAlive()), amount);
    }

    @Override
    protected boolean applicable(EventQueue queue) {
        for (ConstructEvent event: queue.getEvents(ConstructEvent.class)) {
            if (event.isComplete()) { return true; }
        }
        for (TrainEvent event: queue.getEvents(TrainEvent.class)) {
            if (event.isComplete()) { return true; }
        }
        return false;
    }

    @Override
    protected boolean evaluate(ItemRoot state) {
        return hasMinimum(player, condition, count);
    }
}