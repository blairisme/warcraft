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
import com.evilbird.engine.item.Item;
import com.evilbird.warcraft.action.common.create.CreateEvent;
import com.evilbird.warcraft.action.common.remove.RemoveEvent;
import com.evilbird.warcraft.behaviour.scenario.conditions.BasicCondition;
import com.evilbird.warcraft.behaviour.scenario.conditions.CompositeCondition;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Map;
import java.util.function.Predicate;

import static com.evilbird.engine.common.function.Predicates.combination;
import static com.evilbird.engine.item.utility.ItemPredicates.*;
import static com.evilbird.warcraft.item.common.query.UnitPredicates.isAlive;
import static com.evilbird.warcraft.item.common.query.UnitPredicates.isHuman;

public class ScenarioConditions
{
    private ScenarioConditions() {
    }

    private static Predicate<Item> children(Identifier type) {
        return combination(isHuman(), withType(type), isAlive());
    }

    public static ScenarioCondition playerHasMinimum(Identifier type, Integer amount) {
        return new BasicCondition(CreateEvent.class, hasMinimum(children(type), amount));
    }

    public static ScenarioCondition playerHasMinimum(Map<Identifier, Integer> items) {
        Collection<ScenarioCondition> conditions = new ArrayList<>(items.size());
        items.forEach((type, amount) -> conditions.add(playerHasMinimum(type, amount)));
        return new CompositeCondition(conditions);
    }

    public static ScenarioCondition playerHasNone(Class<?> type) {
        return new BasicCondition(RemoveEvent.class, hasMaximum(withClazz(type), 0));
    }
}
