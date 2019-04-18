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
import com.evilbird.engine.events.Event;
import com.evilbird.engine.events.EventQueue;
import com.evilbird.engine.item.Item;
import com.evilbird.engine.item.ItemRoot;
import com.evilbird.warcraft.action.common.create.CreateEvent;
import com.evilbird.warcraft.action.common.remove.RemoveEvent;
import com.evilbird.warcraft.action.construct.ConstructEvent;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Map;
import java.util.function.BiPredicate;
import java.util.function.Predicate;

import static com.evilbird.engine.common.function.Predicates.*;
import static com.evilbird.engine.item.utility.ItemPredicates.*;
import static com.evilbird.warcraft.item.common.query.UnitPredicates.isAlive;
import static com.evilbird.warcraft.item.common.query.UnitPredicates.isHuman;
import static java.util.Arrays.asList;

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
        return combine(hasMinimum(aliveItems(type), amount), forEvents(CreateEvent.class, ConstructEvent.class));
    }

    public static BiPredicate<ItemRoot, EventQueue> playerHasMinimum(Map<Identifier, Integer> items) {
        Collection<BiPredicate<ItemRoot, EventQueue>> conditions = new ArrayList<>(items.size());
        items.forEach((type, amount) -> conditions.add(playerHasMinimum(type, amount)));
        return allBi(conditions);
    }

    public static BiPredicate<ItemRoot, EventQueue> playerHasNone(Class<?> type) {
        return combine(hasMaximum(aliveItems(type), 0), forEvent(RemoveEvent.class));
    }

    private static Predicate<EventQueue> forEvent(Class<? extends Event> type) {
        return (queue) -> queue.hasEvents(type);
    }

    @SafeVarargs
    private static Predicate<EventQueue> forEvents(Class<? extends Event> ... types) {
        return forEvents(asList(types));
    }

    private static Predicate<EventQueue> forEvents(Collection<Class<? extends Event>> types) {
        return (queue) -> types.stream().anyMatch(queue::hasEvents);
    }

    private static Predicate<Item> aliveItems(Identifier type) {
        return all(isHuman(), withType(type), isAlive());
    }

    private static Predicate<Item> aliveItems(Class<?> type) {
        return all(isHuman(), withClazz(type), isAlive());
    }
}
