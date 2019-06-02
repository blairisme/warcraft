/*
 * Blair Butterworth (c) 2019
 *
 * This work is licensed under the MIT License. To view a copy of this
 * license, visit
 *
 *      https://opensource.org/licenses/MIT
 */

package com.evilbird.warcraft.behaviour.scenario;

import com.evilbird.engine.common.lang.Identifier;
import com.evilbird.engine.events.EventQueue;
import com.evilbird.engine.item.Item;
import com.evilbird.engine.item.ItemRoot;
import com.evilbird.warcraft.action.common.remove.RemoveEvent;
import com.evilbird.warcraft.action.construct.ConstructEvent;
import com.evilbird.warcraft.action.train.TrainEvent;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Map;
import java.util.function.BiPredicate;
import java.util.function.Predicate;

import static com.evilbird.engine.common.function.Predicates.all;
import static com.evilbird.engine.common.function.Predicates.allBi;
import static com.evilbird.engine.common.function.Predicates.combine;
import static com.evilbird.engine.item.utility.ItemPredicates.hasMaximum;
import static com.evilbird.engine.item.utility.ItemPredicates.hasMinimum;
import static com.evilbird.engine.item.utility.ItemPredicates.withClazz;
import static com.evilbird.engine.item.utility.ItemPredicates.withType;
import static com.evilbird.warcraft.item.common.query.UnitPredicates.isAlive;
import static com.evilbird.warcraft.item.common.query.UnitPredicates.isCorporeal;

/**
 * Defines {@link Predicate Predicates} used to define conditions for wining or
 * losing scenarios.
 *
 * @author Blair Butterworth
 */
public class ScenarioConditions
{
    private ScenarioConditions() {
    }

    public static BiPredicate<ItemRoot, EventQueue> playerHasMinimum(Identifier type, Integer amount) {
        return combine(hasMinimum(aliveItems(type), amount), createEvents());
    }

    public static BiPredicate<ItemRoot, EventQueue> playerHasMinimum(Map<Identifier, Integer> items) {
        Collection<BiPredicate<ItemRoot, EventQueue>> conditions = new ArrayList<>(items.size());
        items.forEach((type, amount) -> conditions.add(playerHasMinimum(type, amount)));
        return allBi(conditions);
    }

    public static BiPredicate<ItemRoot, EventQueue> playerHasNone(Class<?> type) {
        return combine(hasMaximum(aliveItems(type), 0), removeEvents());
    }

    private static Predicate<EventQueue> createEvents() {
        return queue -> {
            for (ConstructEvent event: queue.getEvents(ConstructEvent.class)) {
                if (event.isComplete()) { return true; }
            }
            for (TrainEvent event: queue.getEvents(TrainEvent.class)) {
                if (event.isComplete()) { return true; }
            }
            return false;
        };
    }

    private static Predicate<EventQueue> removeEvents() {
        return queue -> queue.hasEvents(RemoveEvent.class);
    }

    private static Predicate<Item> aliveItems(Identifier type) {
        return all(isCorporeal(), withType(type), isAlive());
    }

    private static Predicate<Item> aliveItems(Class<?> type) {
        return all(isCorporeal(), withClazz(type), isAlive());
    }
}
