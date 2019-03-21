/*
 * Blair Butterworth (c) 2019
 *
 * This work is licensed under the MIT License. To view a copy of this
 * license, visit
 *
 *      https://opensource.org/licenses/MIT
 */

package com.evilbird.warcraft.action.common;

import com.evilbird.engine.action.Action;
import com.evilbird.engine.action.framework.DelegateAction;
import com.evilbird.engine.common.function.Predicate;
import com.evilbird.engine.common.lang.Identifier;
import com.evilbird.engine.item.Item;

@SuppressWarnings("unchecked")
public class ScenarioAction extends DelegateAction
{

    public static ScenarioAction scenario(Identifier identifier) {
        return new ScenarioAction();
    }

    // Move
    // givenItem(isAlive()).thenInSequence(animate(Move), move, animate(Idle));

    // Train
    // scenario("train").givenItem(isAlive()).then(purchase(type), produce(type), create(type));

    public ScenarioAction given(Predicate<Item>... conditions) {
        return givenItem(conditions);
    }

    public ScenarioAction givenItem(Predicate<Item> ... conditions) {
        return this;
    }

    public ScenarioAction givenTarget(Predicate<Item> ... conditions) {
        return this;
    }

    public ScenarioAction when(Predicate<Item> ... conditions) {
        return this;
    }

    public ScenarioAction whenItem(Predicate<Item> ... conditions) {
        return this;
    }

    public ScenarioAction whenTarget(Predicate<Item> ... conditions) {
        return this;
    }

    public ScenarioAction then(Action... actions) {
        return thenInParallel(actions);
    }

    public ScenarioAction thenInSequence(Action ... action) {
        return this;
    }

    public ScenarioAction thenInParallel(Action ... action) {
        return this;
    }

    @Override
    public boolean act(float delta) {
        return false;
    }
}