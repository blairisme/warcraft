/*
 * Blair Butterworth (c) 2019
 *
 * This work is licensed under the MIT License. To view a copy of this
 * license, visit
 *
 *      https://opensource.org/licenses/MIT
 */

package com.evilbird.warcraft.behaviour.user;

import com.badlogic.gdx.scenes.scene2d.Action;
import com.evilbird.engine.action.*;
import com.evilbird.engine.common.lang.Identifier;
import com.evilbird.engine.common.lang.Objects;
import com.evilbird.engine.device.UserInput;
import com.evilbird.engine.device.UserInputType;
import com.evilbird.engine.item.Item;

import javax.inject.Inject;

import static com.evilbird.engine.action.utilities.ActionUtilities.hasIdentifiedAction;

/**
 * Defines an interaction, a combination of
 *
 * @author Blair Butterworth
 */
public class InteractionDefinition implements Interaction
{
    private ActionFactory factory;
    private ActionIdentifier actionType;
    private ActionIdentifier currentType;
    private Identifier touchedType;
    private Identifier selectedType;
    private UserInputType inputType;
    private InteractionApplicability applicability;

    @Inject
    public InteractionDefinition(ActionFactory factory) {
        this.factory = factory;
    }

    /**
     * Sets the {@link ActionIdentifier action} that this interaction will
     * result in.
     *
     * @param action    an action identifier.
     * @return          this interaction.
     */
    public InteractionDefinition forAction(ActionIdentifier action) {
        this.actionType = action;
        return this;
    }

    /**
     * Sets the {@link UserInputType user input} that this interaction applies
     * to.
     *
     * @param input the user input that the interaction applies to.
     * @return      this interaction.
     */
    public InteractionDefinition forInput(UserInputType input) {
        this.inputType = input;
        return this;
    }

    public InteractionDefinition whenTarget(Identifier identifier) {
        touchedType = identifier;
        return this;
    }

    public InteractionDefinition whenSelected(Identifier identifier) {
        selectedType = identifier;
        return this;
    }

    public InteractionDefinition withAction(ActionIdentifier identifier) {
        this.currentType = identifier;
        return this;
    }

    /**
     * Sets which {@link Item} the action resulting from this interaction will
     * be applied to.
     *
     * @param applicability the type of applicability
     * @return              the interaction
     */
    public InteractionDefinition appliedTo(InteractionApplicability applicability) {
        this.applicability = applicability;
        return this;
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
        if (currentType != null && !hasIdentifiedAction(getPrimary(touched, selected), currentType)) {
            return false;
        }
        return true;
    }

    private Identifier getType(Item item) {
        return item != null ? item.getType() : null;
    }

    @Override
    public void update(UserInput input, Item item, Item selected) {
        Item primary = getPrimary(item, selected);
        Item secondary = getSecondary(item, selected);

        ActionContext context = new BasicActionContext(primary, secondary, input);
        Action action = factory.newAction(actionType, context);

        primary.addAction(action);
    }

    private Item getPrimary(Item item, Item selected) {
        switch (applicability) {
            case Target: return item;
            case Selected: return selected;
            default: return item;
        }
    }

    private Item getSecondary(Item item, Item selected) {
        switch (applicability) {
            case Target: return selected;
            case Selected: return item;
            default: return selected;
        }
    }
}
