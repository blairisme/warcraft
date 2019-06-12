/*
 * Blair Butterworth (c) 2019
 *
 * This work is licensed under the MIT License. To view a copy of this
 * license, visit
 *
 *      https://opensource.org/licenses/MIT
 */

package com.evilbird.warcraft.behaviour.ui.interaction;

import com.evilbird.engine.action.Action;
import com.evilbird.engine.action.ActionFactory;
import com.evilbird.engine.action.ActionIdentifier;
import com.evilbird.engine.common.lang.Identifier;
import com.evilbird.engine.device.UserInput;
import com.evilbird.engine.device.UserInputType;
import com.evilbird.engine.item.Item;
import com.evilbird.engine.item.utility.ItemOperations;

import javax.inject.Inject;
import java.util.Arrays;
import java.util.Collection;
import java.util.Objects;
import java.util.function.BiConsumer;
import java.util.function.BiFunction;
import java.util.function.Function;
import java.util.function.Predicate;

import static com.evilbird.engine.item.utility.ItemPredicates.hasAction;
import static com.evilbird.engine.item.utility.ItemPredicates.hasType;
import static com.evilbird.warcraft.behaviour.ui.interaction.InteractionApplicability.Selected;
import static com.evilbird.warcraft.behaviour.ui.interaction.InteractionAssignment.Parent;
import static com.evilbird.warcraft.behaviour.ui.interaction.InteractionDisplacement.Addition;
import static com.evilbird.warcraft.behaviour.ui.interaction.InteractionDisplacement.Replacement;
import static com.evilbird.warcraft.behaviour.ui.interaction.InteractionDisplacement.Singleton;

/**
 * Instances of this class define the situations an interaction applies to and
 * how an interaction should alter the game state.
 *
 * @author Blair Butterworth
 */
public class InteractionDefinition implements Interaction
{
    private ActionFactory factory;
    private Collection<ActionIdentifier> actions;
    private Predicate<Item> touchedCondition;
    private Predicate<Item> selectedCondition;
    private Predicate<Item> actionCondition;
    private Predicate<UserInput> inputCondition;
    private BiFunction<Item, Item, Item> subjectSupplier;
    private BiFunction<Item, Item, Item> targetSupplier;
    private Function<Item, Item> recipientSupplier;
    private BiConsumer<Item, Action> application;

    @Inject
    public InteractionDefinition(ActionFactory factory) {
        this.factory = factory;
    }

    /**
     * Sets the {@link ActionIdentifier actions} that this interaction will
     * result in.
     *
     * @param actions   an array of {@code ActionIdentifier}s.
     * @return          this interaction.
     */
    public InteractionDefinition forAction(ActionIdentifier ... actions) {
        this.actions = Arrays.asList(actions);
        return this;
    }

    /**
     * Sets the {@link UserInputType user input} that this interaction applies
     * to.
     *
     * @param input the user input that the interaction applies to.
     * @return      the interaction.
     */
    public InteractionDefinition forInput(UserInputType input) {
        Objects.requireNonNull(input);
        return forInput(hasType(input));
    }

    /**
     * Sets a condition applied to user input, that specifies the set of states
     * this interaction applies to.
     *
     * @param condition a {@link Predicate}.
     * @return the interaction.
     */
    public InteractionDefinition forInput(Predicate<UserInput> condition) {
        inputCondition = condition;
        return this;
    }

    /**
     * Specifies that this interaction applies when the whileTarget of the user
     * behaviour is of the given type.
     *
     * @param identifier    an item type {@link Identifier}. This parameter
     *                      cannot be {@code null}.
     * @return              the interaction.
     */
    public InteractionDefinition whenTarget(Identifier identifier) {
        Objects.requireNonNull(identifier);
        return whenTarget(hasType(identifier));
    }

    /**
     * Sets a condition applied to the whileTarget of the user behaviour, that
     * specifies the set of states this interaction applies to.
     *
     * @param condition a {@link Predicate}.
     * @return          the interaction.
     */
    public InteractionDefinition whenTarget(Predicate<Item> condition) {
        touchedCondition = condition;
        return this;
    }

    /**
     * Specifies that this interaction applies when the user has currently
     * selected an item of the given type.
     *
     * @param identifier    an item type {@link Identifier}. This parameter
     *                      cannot be {@code null}.
     * @return              the interaction.
     */
    public InteractionDefinition whenSelected(Identifier identifier) {
        Objects.requireNonNull(identifier);
        return whenSelected(hasType(identifier));
    }

    /**
     * Sets a condition applied to the currently selected item, that specifies
     * the set of states this interaction applies to.
     *
     * @param condition a {@link Predicate}.
     * @return the interaction.
     */
    public InteractionDefinition whenSelected(Predicate<Item> condition) {
        selectedCondition = condition;
        return this;
    }

    /**
     * Specifies that this interaction applies when the {@link Item} that will
     * be the recipient of the interaction already has an {@link Action} of the
     * given type.
     *
     * @param identifier    an {@link ActionIdentifier}. This parameter
     *                      cannot be {@code null}.
     * @return              the interaction.
     */
    public InteractionDefinition withAction(ActionIdentifier identifier) {
        Objects.requireNonNull(identifier);
        return withAction(hasType(identifier));
    }

    /**
     * Sets a condition that operates on the item that the interaction will be
     * applied to, that specifies the set of states this interaction applies
     * to.
     *
     * @param condition a {@link Predicate}.
     * @return the interaction.
     */
    public InteractionDefinition withAction(Predicate<Action> condition) {
        actionCondition = condition != null ? hasAction(condition) : null;
        return this;
    }

    /**
     * Indicates how the interaction should be applied, either as a replacement
     * for existing {@link Action Actions}, or as an addition.
     *
     * @param displacement  an {@link InteractionDisplacement} option. This
     *                      parameter cannot be {@code null}.
     * @return              the interaction
     */
    public InteractionDefinition appliedAs(InteractionDisplacement displacement) {
        Objects.requireNonNull(displacement);
        return appliedAs((subject, action) -> {
            if (displacement == Replacement) {
                subject.clearActions();
                subject.addAction(action);
            }
            else if (displacement == Addition) {
                subject.addAction(action);
            }
            else if (displacement == Singleton && !ItemOperations.hasAction(subject, action.getIdentifier())) {
                subject.addAction(action);
            }
        });
    }

    /**
     * Indicates how the interaction should be applied.
     *
     * @param consumer  a {@link BiConsumer} that will be called with the
     *                  action resulting from this interaction and the Item it
     *                  should be applied to.
     * @return          the interaction
     */
    public InteractionDefinition appliedAs(BiConsumer<Item, Action> consumer) {
        Objects.requireNonNull(consumer);
        application = consumer;
        return this;
    }

    /**
     * Sets which {@link Item Items} the action resulting from this interaction
     * will be applied to: the subject and whileTarget of the resulting
     * {@link Action}.
     *
     * @param applicability an {@link InteractionApplicability} option. This
     *                      parameter cannot be {@code null}.
     * @return              the interaction
     */
    public InteractionDefinition appliedTo(InteractionApplicability applicability) {
        Objects.requireNonNull(applicability);
        return appliedTo(
            (target, selected) -> applicability == Selected ? selected : target,
            (target, selected) -> applicability == Selected ? target : selected);
    }

    /**
     * Sets which {@link Item Items} the action resulting from this interaction
     * will be applied to: the subject and whileTarget of the resulting
     * {@link Action}.
     *
     * @param subjectSupplier   a {@link BiFunction} the provides the subject
     *                          for the resulting action. This parameter cannot
     *                          be {@code null}.
     * @param targetSupplier    a {@link BiFunction} the provides the whileTarget
     *                          for the resulting action. This parameter cannot
     *                          be {@code null}.
     *
     * @return the interaction
     */
    public InteractionDefinition appliedTo(
        BiFunction<Item, Item, Item> subjectSupplier,
        BiFunction<Item, Item, Item> targetSupplier)
    {
        Objects.requireNonNull(subjectSupplier);
        Objects.requireNonNull(targetSupplier);
        this.subjectSupplier = subjectSupplier;
        this.targetSupplier = targetSupplier;
        return this;
    }

    /**
     * Sets which {@link Item} the action resulting from this interaction will
     * be assigned to: the recipient of the resulting {@link Action}.
     *
     * @param assignment    an {@link InteractionAssignment} option. This
     *                      parameter cannot be {@code null}.
     *
     * @return the interaction
     */
    public InteractionDefinition assignedTo(InteractionAssignment assignment) {
        Objects.requireNonNull(assignment);
        return assignedTo(target -> assignment == Parent ? target.getParent() : target);
    }

    /**
     * Sets which {@link Item} the action resulting from this interaction will
     * be assigned to: the recipient of the resulting {@link Action}.
     *
     * @param recipientSupplier a {@link Function} the provides the recipient
     *                          of the resulting action. This parameter cannot
     *                          be {@code null}.
     *
     * @return the interaction
     */
    public InteractionDefinition assignedTo(Function<Item, Item> recipientSupplier) {
        Objects.requireNonNull(recipientSupplier);
        this.recipientSupplier = recipientSupplier;
        return this;
    }

    /**
     * Returns the {@link ActionIdentifier actions} that this interaction will
     * result in.
     *
     * @return an {@code ActionIdentifier} collection.
     */
    public Collection<ActionIdentifier> getActions() {
        return actions;
    }

    @Override
    public boolean applies(UserInput input, Item touched, Item selected) {
        if (inputCondition != null && !inputCondition.test(input)) {
            return false;
        }
        if (touchedCondition != null && !touchedCondition.test(touched)) {
            return false;
        }
        if (selectedCondition != null && !selectedCondition.test(selected)) {
            return false;
        }
        if (actionCondition != null && !actionCondition.test(subjectSupplier.apply(touched, selected))) {
            return false;
        }
        return true;
    }

    @Override
    public void apply(UserInput input, Item item, Item selected) {
        Item subject = subjectSupplier.apply(item, selected);
        Item target = targetSupplier.apply(item, selected);
        Item recipient = recipientSupplier.apply(subject);

        for (ActionIdentifier actionType: actions) {
            Action action = factory.newAction(actionType);
            application.accept(recipient, action);

            action.setItem(subject);
            action.setTarget(target);
            action.setCause(input);
        }
    }
}

