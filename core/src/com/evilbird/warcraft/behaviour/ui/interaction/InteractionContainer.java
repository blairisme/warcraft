/*
 * Blair Butterworth (c) 2019
 *
 * This work is licensed under the MIT License. To view a copy of this
 * license, visit
 *
 *      https://opensource.org/licenses/MIT
 */

package com.evilbird.warcraft.behaviour.ui.interaction;

import com.evilbird.engine.action.ActionIdentifier;
import com.evilbird.engine.device.UserInput;
import com.evilbird.engine.device.UserInputType;
import com.evilbird.engine.item.Item;

import javax.inject.Inject;
import javax.inject.Provider;
import java.util.ArrayList;
import java.util.Collection;

/**
 * Instances of this class contain a set of {@link Interaction Interactions},
 * with methods available to add and retrieve them.
 *
 * @author Blair Butterworth
 */
public class InteractionContainer
{
    private Provider<InteractionDefinition> factory;
    private Collection<Interaction> interactions;

    @Inject
    public InteractionContainer(Provider<InteractionDefinition> factory) {
        this.factory = factory;
        this.interactions = new ArrayList<>();
    }

    public InteractionDefinition addAction(ActionIdentifier action) {
        InteractionDefinition interaction = factory.get();
        interaction.forAction(action);
        interaction.forInput(UserInputType.Action);
        interaction.assignedTo(InteractionAssignment.Item);
        interaction.appliedAs(InteractionDisplacement.Replacement);
        interactions.add(interaction);
        return interaction;
    }

    public Collection<Interaction> getInteractions(UserInput input, Item item, Item selected) {
        Collection<Interaction> result = new ArrayList<>();
        for (Interaction interaction: interactions) {
            if (interaction.applies(input, item, selected)) {
                result.add(interaction);
            }
        }
        return result;
    }
}
