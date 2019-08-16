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
import com.evilbird.warcraft.action.produce.ProduceEvent;

import java.util.Objects;
import java.util.function.Predicate;

import static com.evilbird.engine.common.function.Predicates.both;
import static com.evilbird.engine.item.utility.ItemOperations.hasMinimum;
import static com.evilbird.engine.item.utility.ItemPredicates.withId;
import static com.evilbird.engine.item.utility.ItemPredicates.withType;
import static com.evilbird.warcraft.item.common.query.UnitPredicates.isAlive;
import static com.evilbird.warcraft.item.data.player.PlayerIds.Player1;

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

    /**
     * Constructs a new instance of this class given a condition used to
     * identify qualifying units owned by the player and the minimum number of
     * units required to satisfy the condition.
     *
     * @param condition a {@link Predicate} used to identify qualifying units.
     * @param minimum   the minimum number of required units.
     *
     * @throws NullPointerException if the given condition is
     *                              {@code null}.
     */
    public PlayerOwnership(Predicate<Item> condition, int minimum) {
        super(Player1);
        Objects.requireNonNull(condition);

        this.condition = condition;
        this.count = minimum;
    }

    /**
     * Creates a new PlayerOwnership instance the is fulfilled when the user
     * has at least the specified minimum number of units of the given
     * {@link ItemType type}.
     *
     * @param type      the type of qualifying units.
     * @param minimum   the minimum number of required units.
     * @return          a new PlayerOwnership instance.
     *
     * @throws NullPointerException if the given type is {@code null}.
     */
    public static PlayerOwnership playerOwns(ItemType type, int minimum) {
        return new PlayerOwnership(both(withType(type), isAlive()), minimum);
    }

    /**
     * Creates a new PlayerOwnership instance the is fulfilled when the user
     * has at least the specified minimum number of units with the given
     * {@link Identifier}.
     *
     * @param id        the identifier of qualifying units.
     * @param minimum   the minimum number of required units.
     * @return          a new PlayerOwnership instance.
     *
     * @throws NullPointerException if the given type is {@code null}.
     */
    public static PlayerOwnership playerOwns(Identifier id, int minimum) {
        return new PlayerOwnership(both(withId(id), isAlive()), minimum);
    }

    @Override
    protected boolean applicable(EventQueue queue) {
        for (ConstructEvent event: queue.getEvents(ConstructEvent.class)) {
            if (event.isComplete()) { return true; }
        }
        for (ProduceEvent event: queue.getEvents(ProduceEvent.class)) {
            if (event.isComplete()) { return true; }
        }
        return false;
    }

    @Override
    protected boolean evaluate(ItemRoot state) {
        return hasMinimum(player, condition, count);
    }
}