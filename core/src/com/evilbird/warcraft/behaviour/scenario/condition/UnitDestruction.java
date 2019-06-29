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
import com.evilbird.warcraft.action.common.remove.RemoveEvent;
import com.evilbird.warcraft.item.unit.UnitType;

import java.util.function.Predicate;

import static com.evilbird.engine.common.function.Predicates.both;
import static com.evilbird.engine.item.utility.ItemOperations.hasNone;
import static com.evilbird.engine.item.utility.ItemPredicates.withType;
import static com.evilbird.warcraft.item.common.query.UnitPredicates.isAlive;

/**
 * Represents a {@link ScenarioCondition} that is fulfilled when all of a
 * players units of a given type have been destroyed.
 *
 * @author Blair Butterworth
 */
public class UnitDestruction extends PlayerCondition
{
    private Predicate<Item> unitsOfType;

    public UnitDestruction(Identifier player, UnitType type) {
        super(player);
        unitsOfType = both(withType(type), isAlive());
    }

    public static UnitDestruction unitsDestroyed(Identifier player, UnitType type) {
        return new UnitDestruction(player, type);
    }

    @Override
    protected boolean applicable(EventQueue queue) {
        return queue.hasEvents(RemoveEvent.class);
    }

    @Override
    protected boolean evaluate(ItemRoot state) {
        return hasNone(player, unitsOfType);
    }
}
