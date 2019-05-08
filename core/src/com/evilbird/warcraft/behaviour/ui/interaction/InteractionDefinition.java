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

import javax.inject.Inject;
import java.util.Arrays;
import java.util.Collection;
import java.util.Objects;
import java.util.function.BiConsumer;
import java.util.function.Predicate;

import static com.evilbird.engine.item.utility.ItemPredicates.hasAction;
import static com.evilbird.engine.item.utility.ItemPredicates.hasType;
import static com.evilbird.warcraft.behaviour.ui.interaction.InteractionApplicability.Selected;
import static com.evilbird.warcraft.behaviour.ui.interaction.InteractionAssignment.Parent;
import static com.evilbird.warcraft.behaviour.ui.interaction.InteractionDisplacement.Replacement;

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
    private InteractionAssignment assignment;
    private InteractionApplicability applicability;
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
     * Specifies that this interaction applies when the target of the user
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
     * Sets a condition applied to the target of the user behaviour, that
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
            }
            subject.addAction(action);
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
     * Sets which {@link Item} the action resulting from this interaction will
     * be applied to: the target of the resulting {@link Action}.
     *
     * @param applicability an {@link InteractionApplicability} option. This
     *                      parameter cannot be {@code null}.
     * @return              the interaction
     */
    public InteractionDefinition appliedTo(InteractionApplicability applicability) {
        Objects.requireNonNull(applicability);
        this.applicability = applicability;
        return this;
    }

    /**
     * Sets which {@link Item} the action resulting from this interaction will
     * be assigned to: the item of the resulting {@link Action}.
     *
     * @param assignment    an {@link InteractionAssignment} option. This
     *                      parameter cannot be {@code null}.
     * @return              the interaction
     */
    public InteractionDefinition assignedTo(InteractionAssignment assignment) {
        Objects.requireNonNull(assignment);
        this.assignment = assignment;
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
        if (actionCondition != null && !actionCondition.test(getPrimary(touched, selected))) {
            return false;
        }
        return true;
    }

    @Override
    public void apply(UserInput input, Item item, Item selected) {
        Item primary = getPrimary(item, selected);
        Item secondary = getSecondary(item, selected);
        Item subject = getSubject(primary);

        for (ActionIdentifier action: actions) {
            apply(action, input, primary, secondary, subject);
        }
    }

    private void apply(ActionIdentifier actionType, UserInput input, Item primary, Item secondary, Item subject) {
        Action action = factory.newAction(actionType);
        application.accept(subject, action);

        action.setItem(primary);
        action.setTarget(secondary);
        action.setCause(input);
    }

    private Item getPrimary(Item item, Item selected) {
        return applicability == Selected ? selected : item;
    }

    private Item getSecondary(Item item, Item selected) {
        return applicability == Selected ? item : selected;
    }

    private Item getSubject(Item item) {
        return assignment == Parent ? item.getParent() : item;
    }
}

