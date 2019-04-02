/*
 * Blair Butterworth (c) 2019
 *
 * This work is licensed under the MIT License. To view a copy of this
 * license, visit
 *
 *      https://opensource.org/licenses/MIT
 */

package com.evilbird.engine.action.framework;

import com.evilbird.engine.action.Action;
import com.evilbird.engine.action.common.ActionTarget;
import com.evilbird.engine.action.predicates.ActionPredicate;
import com.evilbird.engine.common.function.Predicate;
import com.evilbird.engine.common.function.Predicates;
import com.evilbird.engine.common.lang.Identifier;
import com.evilbird.engine.item.Item;

@SuppressWarnings("unchecked")
public class ScenarioAction<T extends Identifier> extends BasicAction
{
    private ActionPredicate given;
    private SequenceAction then;
    private Action scenario;

    public ScenarioAction() {
        given = new ActionPredicate(Predicates.accept(), ActionTarget.Item);
        then = new SequenceAction();
    }

    public ScenarioAction scenario(Identifier identifier) {
        setIdentifier(identifier);
        return this;
    }

    public ScenarioAction given(Predicate<Item> condition) {
        return givenItem(condition);
    }

    public ScenarioAction givenItem(Predicate<? super Item> condition) {
        given = new ActionPredicate(condition, ActionTarget.Item);
        scenario = null;
        return this;
    }

    public ScenarioAction givenTarget(Predicate<? super Item> condition) {
        given = new ActionPredicate(condition, ActionTarget.Target);
        scenario = null;
        return this;
    }

    public ScenarioAction then(Action action) {
        then.add(action);
        scenario = null;
        return this;
    }

    public ScenarioAction then(Action ... actions) {
        then.add(new ParallelAction(actions));
        scenario = null;
        return this;
    }

    @Override
    public boolean act(float delta) {
        if (scenario == null) {
            steps((T)getIdentifier());
            scenario = build();
        }
        return scenario.act(delta);
    }

    protected void steps(T identifier) {
    }

    protected Action build() {
        Action result = new OptionalAction(then, given);
        result.setItem(getItem());
        result.setTarget(getTarget());
        result.setCause(getCause());
        return result;
    }
}