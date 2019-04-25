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
import com.evilbird.engine.action.common.ActionRecipient;
import com.evilbird.engine.action.predicates.ActionPredicate;
import com.evilbird.engine.common.lang.Identifier;
import com.evilbird.engine.item.Item;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Objects;
import java.util.function.BiConsumer;
import java.util.function.Predicate;
import java.util.function.Supplier;

import static com.evilbird.engine.common.function.Predicates.both;

/**
 * Represents an action whose operation is specified in a syntax akin to
 * Gherkin.
 *
 * @param <T> an action identifier type.
 *
 * @author Blair Butterworth
 */
@SuppressWarnings("unchecked")
public class ScenarioAction<T extends Identifier> extends BasicAction
{
    private static transient final Logger logger = LoggerFactory.getLogger(ScenarioAction.class);

    private transient Action scenario;
    private transient SequenceAction then;
    private transient Predicate<Action> given;
    private transient Predicate<Action> when;
    private transient Supplier<Item> itemSupplier;
    private transient Supplier<Item> targetSupplier;

    /**
     * Constructs a new instance of this class with no requirements, branches
     * or operations.
     */
    public ScenarioAction() {
        then = new SequenceAction();
    }

    /**
     * Sets the unique identifier of the scenario.
     *
     * @param identifier an {@link Identifier} instance. This parameter cannot
     *                   be {@code null}.
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
     *                      This parameter cannot be {@code null}.
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
     *                      This parameter cannot be {@code null}.
     *
     * @return this scenario.
     */
    public ScenarioAction givenItem(Predicate<? super Item> condition) {
        Objects.requireNonNull(condition);
        Predicate<Action> newGiven = new ActionPredicate(condition, ActionRecipient.Subject);
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
     *                      This parameter cannot be {@code null}.
     *
     * @return this scenario.
     */
    public ScenarioAction givenTarget(Predicate<? super Item> condition) {
        Objects.requireNonNull(condition);
        Predicate<Action> newGiven = new ActionPredicate(condition, ActionRecipient.Target);
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
     *                      {@code null}.
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
     *                      {@code null}.
     *
     * @return this scenario.
     */
    public ScenarioAction whenItem(Predicate<? super Item> condition) {
        Objects.requireNonNull(condition);
        Predicate<Action> newWhen = new ActionPredicate(condition, ActionRecipient.Subject);
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
     *                      {@code null}.
     *
     * @return this scenario.
     */
    public ScenarioAction whenTarget(Predicate<? super Item> condition) {
        Objects.requireNonNull(condition);
        Predicate<Action> newWhen = new ActionPredicate(condition, ActionRecipient.Target);
        when = when != null ? both(when, newWhen) : newWhen;
        scenario = null;
        return this;
    }

    /**
     * Sets an {@link Action} that will be executed in sequence, after any
     * previously specified Actions, provided that all specified when
     * conditions are {@code true}and all given conditions remain
     * {@code true} for the duration Actions execution.
     *
     * @param action    an Action. This parameter cannot be {@code null}.
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
     * Sets an {@link Action} that will be executed in sequence, after any
     * previously specified Actions, provided that all specified when
     * conditions are {@code true}and all given conditions remain
     * {@code true} for the duration Actions execution. After execution, any
     * subsequent actions will updated to use the subject and target of the
     * given {@code Action}.
     *
     * @param action    an Action. This parameter cannot be {@code null}.
     *
     * @return this scenario.
     */
    public ScenarioAction thenUpdate(Action action) {
        Objects.requireNonNull(action);
        then.add(new CopyAction(action, then));
        scenario = null;
        return this;
    }

    /**
     * Sets a series of {@link Action Actions} that will be executed as a set
     * in parallel, but overall synchronously in sequence after any
     * previously specified Actions. This is provided that all specified when
     * conditions are {@code true}and all given conditions remain
     * {@code true} for the duration Actions execution.
     *
     * @param actions an array of Actions. This parameter cannot be
     *                {@code null}.
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
     * conditions are {@code true} and all given conditions remain
     * {@code true} for the duration Actions execution.
     *
     * @param lambda    a {@link BiConsumer<Item, Item>} that will receive the
     *                  current item and target when invoked. This parameter
     *                  cannot be {@code null}.
     *
     * @return this scenario.
     */
    public ScenarioAction then(BiConsumer<Item, Item> lambda) {
        Objects.requireNonNull(lambda);
        then.add(new LambdaAction(lambda));
        scenario = null;
        return this;
    }

    /**
     * Specifies the subject of the Scenario, the {@link Item} that the
     * Scenario will operate on.
     *
     * @param item  an {@code Subject} instance. This parameter cannot be
     *              {@code null}.
     *
     * @return this scenario.
     */
    public ScenarioAction withItem(Item item) {
        Objects.requireNonNull(item);
        setItem(item);
        return this;
    }

    /**
     * Assigns a {@link Supplier} that specifies the subject of the Scenario,
     * the {@link Item} that the Scenario will operate on. The supplier will be
     * asked for the subject {@code Subject} once as soon as the Scenario is acted
     * upon or after its restarted or reset.
     *
     * @param supplier  an {@code Subject Supplier}. This parameter cannot be
     *                  {@code null}.
     *
     * @return this scenario.
     */
    public ScenarioAction withItem(Supplier<Item> supplier) {
        Objects.requireNonNull(supplier);
        itemSupplier = supplier;
        scenario = null;
        return this;
    }

    /**
     * Specifies the target of the Scenario, an optional {@link Item} that this
     * action will manipulate.
     *
     * @param target an {@code Subject} instance. May be {@code null}.
     *
     * @return this scenario.
     */
    public ScenarioAction withTarget(Item target) {
        setTarget(target);
        return this;
    }

    /**
     * Assigns a {@link Supplier} that specifies the target of the Scenario,
     * an optional {@link Item} that this action will manipulate. The supplier
     * will be asked for the target {@code Subject} once as soon as the Scenario
     * is acted upon or after its restarted or reset.
     *
     * @param supplier  an {@code Subject Supplier}. This parameter cannot be
     *                  {@code null}.
     *
     * @return this scenario.
     */
    public ScenarioAction withTarget(Supplier<Item> supplier) {
        Objects.requireNonNull(supplier);
        targetSupplier = supplier;
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

        if (result != null) {
            result = log(result);
        }
        if (when != null) {
            result = new OptionalAction(result, when);
        }
        if (given != null) {
            result = new RequirementAction(result, given);
        }
        if (itemSupplier != null) {
            result = new UpdateAction(result, itemSupplier, ActionRecipient.Subject);
        }
        if (targetSupplier != null) {
            result = new UpdateAction(result, targetSupplier, ActionRecipient.Target);
        }
        return result;
    }

    private Action log(Action action) {
        ParallelAction result = new ParallelAction();
        result.add(log("Action sequence started: {}", getIdentifier()));
        result.add(action);
        result.add(log("Action sequence completed: {}", getIdentifier()));
        return action;
    }

    private Action log(String message, Object ... values) {
        return new LambdaAction((i, t) -> logger.debug(message, values));
    }
}