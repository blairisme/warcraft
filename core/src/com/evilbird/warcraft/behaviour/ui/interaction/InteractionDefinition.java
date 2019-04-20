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
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.inject.Inject;
import java.util.Objects;

import static com.evilbird.warcraft.behaviour.ui.interaction.InteractionDisplacement.Replacement;

/**
 * Instances of this class define the situations an interaction applies to and
 * how an interaction should alter the game state.
 *
 * @author Blair Butterworth
 */
public class InteractionDefinition implements Interaction
{
    private static final Logger logger = LoggerFactory.getLogger(InteractionDefinition.class);

    private ActionFactory factory;
    private ActionIdentifier actionType;
    private ActionIdentifier currentType;
    private Identifier touchedType;
    private Identifier selectedType;
    private UserInputType inputType;
    private InteractionAssignment assignment;
    private InteractionApplicability applicability;
    private InteractionDisplacement displacement;

    @Inject
    public InteractionDefinition(ActionFactory factory) {
        this.factory = factory;
    }

    /**
     * Sets the {@link ActionIdentifier action} that this interaction will
     * result in.
     *
     * @param action    an {@code ActionIdentifier}.
     * @return          this interaction.
     */
    public InteractionDefinition forAction(ActionIdentifier action) {
        Objects.requireNonNull(action);
        this.actionType = action;
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
        this.inputType = input;
        return this;
    }

    /**
     * Specifies that this interaction applies when the target of the user
     * behaviour is of the given type.
     *
     * @param identifier    an item type {@link Identifier}.
     * @return              the interaction.
     */
    public InteractionDefinition whenTarget(Identifier identifier) {
        touchedType = identifier;
        return this;
    }

    /**
     * Specifies that this interaction applies when the user has currently
     * selected an item of the given type.
     *
     * @param identifier    an item type {@link Identifier}.
     * @return              the interaction.
     */
    public InteractionDefinition whenSelected(Identifier identifier) {
        selectedType = identifier;
        return this;
    }

    /**
     * Specifies that this interaction applies when the {@link Item} that will
     * be the recipient of the interaction already has an {@link Action} of the
     * given type.
     *
     * @param identifier    an {@link ActionIdentifier}.
     * @return              the interaction.
     */
    public InteractionDefinition withAction(ActionIdentifier identifier) {
        this.currentType = identifier;
        return this;
    }

    /**
     * Indicates how the interaction should be applied, either as a replacement
     * for existing {@link Action Actions}, or as an addition.
     *
     * @@param displacement an {@link InteractionDisplacement} option.
     * @return              the interaction
     */
    public InteractionDefinition appliedAs(InteractionDisplacement displacement) {
        this.displacement = displacement;
        return this;
    }

    /**
     * Sets which {@link Item} the action resulting from this interaction will
     * be applied to: the target of the resulting {@link Action}.
     *
     * @param applicability an {@link InteractionApplicability} option.
     * @return              the interaction
     */
    public InteractionDefinition appliedTo(InteractionApplicability applicability) {
        this.applicability = applicability;
        return this;
    }

    /**
     * Sets which {@link Item} the action resulting from this interaction will
     * be assigned to: the item of the resulting {@link Action}.
     *
     * @param assignment    an {@link InteractionAssignment} option.
     * @return              the interaction
     */
    public InteractionDefinition assignedTo(InteractionAssignment assignment) {
        this.assignment = assignment;
        return this;
    }

    /**
     * Returns the {@link ActionIdentifier action} that this interaction will
     * result in.
     *
     * @return an {@code ActionIdentifier}.
     */
    public ActionIdentifier getAction() {
        return actionType;
    }

    /**
     * Returns the {@link UserInputType user input} that this interaction
     * applies to.
     *
     * @return a {@code UserInputType} option.
     */
    public UserInputType getInput() {
        return inputType;
    }

    /**
     * Returns the {@link Item} that the action resulting from this interaction
     * will be assigned to: the item of the resulting {@link Action}.
     *
     * @return an {@link InteractionAssignment} option.
     */
    public InteractionAssignment getAssignedTo() {
        return assignment;
    }

    /**
     * Returns an {@link InteractionDisplacement} option indicating how the
     * interaction should be applied, either as a replacement for existing
     * {@link Action Actions}, or as an addition.
     *
     * @return an {@code InteractionDisplacement} option.
     */
    public InteractionDisplacement getAppliedAs() {
        return displacement;
    }

    @Override
    public boolean applies(UserInput input, Item touched, Item selected) {
        if (inputType != null && !Objects.equals(inputType, input.getType())){
            return false;
        }
        if (touchedType != null && !Objects.equals(touchedType, getType(touched))){
            return false;
        }
        if (selectedType != null && !Objects.equals(selectedType, getType(selected))){
            return false;
        }
        if (currentType != null && !hasAction(getPrimary(touched, selected), currentType)) {
            return false;
        }
        return true;
    }

    private Identifier getType(Item item) {
        return item != null ? item.getType() : null;
    }

    private boolean hasAction(Item item, Identifier identifier) {
        for (Action action: item.getActions()) {
            if (action.getIdentifier() == identifier) {
                return true;
            }
        }
        return false;
    }

    @Override
    public void update(UserInput input, Item item, Item selected) {
        Item primary = getPrimary(item, selected);
        Item secondary = getSecondary(item, selected);
        Item subject = getSubject(primary);

        Action action = factory.newAction(actionType);
        if (displacement == Replacement) {
            subject.clearActions();
        }
        subject.addAction(action);

        action.setItem(primary);
        action.setTarget(secondary);
        action.setCause(input);

        log(actionType, primary);
    }

    private Item getPrimary(Item item, Item selected) {
        switch (applicability) {
            case Selected: return selected;
            default: return item;
        }
    }

    private Item getSecondary(Item item, Item selected) {
        switch (applicability) {
            case Selected: return item;
            default: return selected;
        }
    }

    private Item getSubject(Item item) {
        switch (assignment) {
            case Parent: return item.getParent();
            default: return item;
        }
    }

    private void log(ActionIdentifier actionType, Item subject) {
        logger.debug("Action started - type: '{}' subject: '{}'",
            actionType, subject.getIdentifier());
    }
}

