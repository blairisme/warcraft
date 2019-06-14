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
import com.evilbird.engine.item.Item;
import com.evilbird.engine.item.ItemRoot;
import com.evilbird.warcraft.action.common.remove.RemoveEvent;

import java.util.function.Predicate;

import static com.evilbird.engine.item.utility.ItemOperations.hasNone;
import static com.evilbird.warcraft.item.common.query.UnitPredicates.isAlive;

/**
 * Represents a {@link ScenarioCondition} that is fulfilled when all of the
 * users units have been destroyed.
 *
 * @author Blair Butterworth
 */
public class PlayerDestruction extends PlayerCondition
{
    private Predicate<Item> livingUnits;

    public PlayerDestruction() {
        livingUnits = isAlive();//both(withClazz(Unit.class), isAlive());
    }

    public static PlayerDestruction playerDestroyed() {
        return new PlayerDestruction();
    }

    @Override
    protected boolean applicable(EventQueue queue) {
        return queue.hasEvents(RemoveEvent.class);
    }

    @Override
    protected boolean evaluate(ItemRoot state) {
        return hasNone(player, livingUnits);
    }
}
