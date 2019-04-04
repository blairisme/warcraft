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
import com.evilbird.engine.common.lang.Identifier;
import com.evilbird.engine.item.Item;

import java.util.Objects;
import java.util.function.Consumer;
import java.util.function.Predicate;
import java.util.function.Supplier;

import static com.evilbird.engine.common.function.Predicates.both;

/**
 * Represents an action whose operation is specified somewhat like Gherkin.
 *
 * @param <T> an action identifier type.
 *
 * @author Blair Butterworth
 */
@SuppressWarnings("unchecked")
public class ScenarioAction<T extends Identifier> extends BasicAction
{
    private transient Action scenario;
    private transient SequenceAction then;
    private transient Predicate<Action> given;
    private transient Predicate<Action> when;
    private transient Supplier<Item> itemSupplier;
    private transient Supplier<Item> targetSupplier;

    public ScenarioAction() {
        then = new SequenceAction();
    }

    /**
     * Sets the unique identifier of the scenario.
     *
     * @param identifier an {@link Identifier} instance. This parameter cannot
     *                   be <code>null</code>.
     *
     * @return this scenario.
     */
    public ScenarioAction scenario(Identifier identifier) {
        Objects.requireNonNull(identifier);
        setIdentifier(identifier);
        return this;
    }

    /**
     * Adds a requirement pertaining to the Scenarios subject (item) that must
     * be fulfilled at all times for the Scenario to continue running. Multiple
     * given statements will be combined into a conjunction (and &).
     *
     * @param condition     a condition that must be fulfilled at all times.
     *                      This parameter cannot be <code>null</code>.
     *
     * @return this scenario.
     */
    public ScenarioAction given(Predicate<? super Item> condition) {
        return givenItem(condition);
    }

    /**
     * Adds a requirement pertaining to the Scenarios subject (item) that must
     * be fulfilled at all times for the Scenario to continue running. Multiple
     * given statements will be combined into a conjunction (and &).
     *
     * @param condition     a condition that must be fulfilled at all times.
     *                      This parameter cannot be <code>null</code>.
     *
     * @return this scenario.
     */
    public ScenarioAction givenItem(Predicate<? super Item> condition) {
        Objects.requireNonNull(condition);
        Predicate<Action> newGiven = new ActionPredicate(condition, ActionTarget.Item);
        given = given != null ? both(given, newGiven) : newGiven;
        scenario = null;
        return this;
    }

    /**
     * Adds a requirement pertaining to the Scenarios target that must be
     * fulfilled at all times for the Scenario to continue running. Multiple
     * given statements will be combined into a conjunction (and &).
     *
     * @param condition     a condition that must be fulfilled at all times.
     *                      This parameter cannot be <code>null</code>.
     *
     * @return this scenario.
     */
    public ScenarioAction givenTarget(Predicate<? super Item> condition) {
        Objects.requireNonNull(condition);
        Predicate<Action> newGiven = new ActionPredicate(condition, ActionTarget.Target);
        given = given != null ? both(given, newGiven) : newGiven;
        scenario = null;
        return this;
    }

    /**
     * Adds a requirement pertaining to the Scenarios subject (item) that must
     * be fulfilled for the preceding then statements to be executed: a branch
     * condition. Multiple when statements will be combined into a conjunction
     * (and &).
     *
     * @param condition     a branch condition. This parameter cannot be
     *                      <code>null</code>.
     *
     * @return this scenario.
     */
    public ScenarioAction when(Predicate<Item> condition) {
        return whenItem(condition);
    }

    /**
     * Adds a requirement pertaining to the Scenarios subject (item) that must
     * be fulfilled for the preceding then statements to be executed: a branch
     * condition. Multiple when statements will be combined into a conjunction
     * (and &).
     *
     * @param condition     a branch condition. This parameter cannot be
     *                      <code>null</code>.
     *
     * @return this scenario.
     */
    public ScenarioAction whenItem(Predicate<? super Item> condition) {
        Objects.requireNonNull(condition);
        Predicate<Action> newWhen = new ActionPredicate(condition, ActionTarget.Item);
        when = when != null ? both(when, newWhen) : newWhen;
        scenario = null;
        return this;
    }

    /**
     * Adds a requirement pertaining to the Scenarios target that must be
     * fulfilled for the preceding then statements to be executed: a branch
     * condition. Multiple when statements will be combined into a conjunction
     * (and &).
     *
     * @param condition     a branch condition. This parameter cannot be
     *                      <code>null</code>.
     *
     * @return this scenario.
     */
    public ScenarioAction whenTarget(Predicate<? super Item> condition) {
        Objects.requireNonNull(condition);
        Predicate<Action> newWhen = new ActionPredicate(condition, ActionTarget.Target);
        when = when != null ? both(when, newWhen) : newWhen;
        scenario = null;
        return this;
    }

    /**
     * Sets an {@link Action} that will be executed in sequence, after any
     * previously specified Actions, provided that all specified when
     * conditions are <code>true</code> and all given conditions remain
     * <code>true</code> for the duration Actions execution.
     *
     * @param action    an Action. This parameter cannot be <code>null</code>.
     *
     * @return this scenario.
     */
    public ScenarioAction then(Action action) {
        Objects.requireNonNull(action);
        then.add(action);
        scenario = null;
        return this;
    }

    /**
     * Sets a series of {@link Action Actions} that will be executed as a set
     * in parallel, but overall synchronously in sequence after any
     * previously specified Actions. This is provided that all specified when
     * conditions are <code>true</code> and all given conditions remain
     * <code>true</code> for the duration Actions execution.
     *
     * @param actions an array of Actions. This parameter cannot be
     *                <code>null</code>.
     *
     * @return this scenario.
     */
    public ScenarioAction then(Action ... actions) {
        Objects.requireNonNull(actions);
        then.add(new ParallelAction(actions));
        scenario = null;
        return this;
    }

    /**
     * Assigns a Lambda to be executed as an Action in sequence, after any
     * previously specified Actions, provided that all specified when
     * conditions are <code>true</code> and all given conditions remain
     * <code>true</code> for the duration Actions execution.
     *
     * @param lambda    a {@link Consumer<Item>}. This parameter cannot be
     *                 <code>null</code>.
     *
     * @return this scenario.
     */
    public ScenarioAction then(Consumer<Item> lambda) {
        Objects.requireNonNull(lambda);
        then.add(new LambdaAction(lambda));
        scenario = null;
        return this;
    }

    public ScenarioAction withItem(Supplier<Item> updater) {
        itemSupplier = updater;
        scenario = null;
        return this;
    }

    public ScenarioAction withItem(Item item) {
        setItem(item);
        return this;
    }

    public ScenarioAction withTarget(Item target) {
        setTarget(target);
        return this;
    }

    public ScenarioAction withTarget(Supplier<Item> updater) {
        targetSupplier = updater;
        scenario = null;
        return this;
    }

    @Override
    public boolean act(float delta) {
        Action scenario = getScenario();
        return scenario.act(delta);
    }

    public boolean evaluate() {
        boolean result = false;
        if (when != null) {
            result = when.test(this);
        }
        if (given != null) {
            result &= given.test(this);
        }
        return result;
    }

    @Override
    public void reset() {
        super.reset();
        Action scenario = getScenario();
        scenario.reset();
    }

    @Override
    public void restart() {
        super.restart();
        Action scenario = getScenario();
        scenario.restart();
    }

    protected void steps(T identifier) {
    }

    private Action getScenario() {
        if (scenario == null) {
            steps((T)getIdentifier());
            scenario = build();
        }
        return scenario;
    }

    private Action build() {
        Action result = create();
        result.setItem(getItem());
        result.setTarget(getTarget());
        result.setCause(getCause());
        return result;
    }

    private Action create() {
        Action result = then;
        if (when != null) {
            result = new OptionalAction(result, when);
        }
        if (given != null) {
            result = new RequirementAction(result, given);
        }
        if (itemSupplier != null) {
            result = new UpdateAction(result, itemSupplier, ActionTarget.Item);
        }
        if (targetSupplier != null) {
            result = new UpdateAction(result, targetSupplier, ActionTarget.Target);
        }
        return result;
    }
}