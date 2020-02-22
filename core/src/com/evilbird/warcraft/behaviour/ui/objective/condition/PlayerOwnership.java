/*
 * Copyright (c) 2020, Blair Butterworth
 *
 * This Source Code Form is subject to the terms of the Mozilla Public
 * License, v. 2.0. If a copy of the MPL was not distributed with this
 * file, You can obtain one at http://mozilla.org/MPL/2.0/.
 */

package com.evilbird.warcraft.behaviour.ui.objective.condition;

import com.evilbird.engine.common.lang.Identifier;
import com.evilbird.engine.events.EventQueue;
import com.evilbird.engine.object.GameObject;
import com.evilbird.engine.object.GameObjectContainer;
import com.evilbird.engine.object.GameObjectType;
import com.evilbird.warcraft.action.construct.ConstructEvent;
import com.evilbird.warcraft.action.produce.ProduceEvent;

import java.util.Objects;
import java.util.function.Predicate;

import static com.evilbird.engine.common.function.Predicates.both;
import static com.evilbird.engine.object.utility.GameObjectOperations.hasMinimum;
import static com.evilbird.engine.object.utility.GameObjectPredicates.withId;
import static com.evilbird.engine.object.utility.GameObjectPredicates.withType;
import static com.evilbird.warcraft.object.common.query.UnitPredicates.isAlive;
import static com.evilbird.warcraft.object.data.player.PlayerIds.Player1;

/**
 * Represents a {@link ScenarioCondition} that is fulfilled when the user owns
 * a given number or specific set of units.
 *
 * @author Blair Butterworth
 */
public class PlayerOwnership extends PlayerCondition
{
    private Predicate<GameObject> condition;
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
    public PlayerOwnership(Predicate<GameObject> condition, int minimum) {
        super(Player1);
        Objects.requireNonNull(condition);

        this.condition = condition;
        this.count = minimum;
    }

    /**
     * Creates a new PlayerOwnership instance the is fulfilled when the user
     * has at least the specified minimum number of units of the given
     * {@link GameObjectType type}.
     *
     * @param type      the type of qualifying units.
     * @param minimum   the minimum number of required units.
     * @return          a new PlayerOwnership instance.
     *
     * @throws NullPointerException if the given type is {@code null}.
     */
    public static PlayerOwnership playerOwns(GameObjectType type, int minimum) {
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
    protected boolean evaluate(GameObjectContainer state) {
        return hasMinimum(player, condition, count);
    }
}