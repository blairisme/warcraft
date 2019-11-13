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
import com.evilbird.engine.object.GameObject;
import com.evilbird.engine.object.GameObjectContainer;
import com.evilbird.warcraft.action.common.remove.RemoveEvent;
import com.evilbird.warcraft.object.unit.UnitType;

import java.util.function.Predicate;

import static com.evilbird.engine.common.function.Predicates.both;
import static com.evilbird.engine.object.utility.GameObjectOperations.hasNone;
import static com.evilbird.engine.object.utility.GameObjectPredicates.withType;
import static com.evilbird.warcraft.object.common.query.UnitPredicates.isAlive;

/**
 * Represents a {@link ScenarioCondition} that is fulfilled when all of a
 * players units of a given type have been destroyed.
 *
 * @author Blair Butterworth
 */
public class UnitDestruction extends PlayerCondition
{
    private Predicate<GameObject> unitsOfType;

    /**
     * Creates a new instance of this class that will be fulfilled when the
     * given player no longer has any units of the given {@link UnitType type}.
     *
     * @param player    the {@link Identifier} of a player.
     * @param type      the type of units whose destruction will be determined.
     *
     * @throws NullPointerException if the given player id or type is
     *                              {@code null}.
     */
    public UnitDestruction(Identifier player, UnitType type) {
        super(player);
        unitsOfType = both(withType(type), isAlive());
    }

    /**
     * Creates a new UnitDestruction instance that is fulfilled when the given
     * player no longer has any units of the given {@link UnitType type}.
     *
     * @param player    the {@link Identifier} of a player.
     * @param type      the type of units whose destruction will be determined.
     * @return          a new UnitDestruction instance.
     *
     * @throws NullPointerException if the given player id or type is
     *                              {@code null}.
     */
    public static UnitDestruction unitsDestroyed(Identifier player, UnitType type) {
        return new UnitDestruction(player, type);
    }

    @Override
    protected boolean applicable(EventQueue queue) {
        return queue.hasEvents(RemoveEvent.class);
    }

    @Override
    protected boolean evaluate(GameObjectContainer state) {
        return hasNone(player, unitsOfType);
    }
}
