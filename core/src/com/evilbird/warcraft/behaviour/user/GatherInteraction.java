package com.evilbird.warcraft.behaviour.user;

import com.evilbird.engine.device.UserInput;
import com.evilbird.engine.device.UserInputType;
import com.evilbird.engine.item.Item;
import com.evilbird.warcraft.action.ActionType;
import com.evilbird.warcraft.behaviour.user.interaction.CompositeInteraction;
import com.evilbird.warcraft.behaviour.user.interaction.Interaction;
import com.evilbird.warcraft.behaviour.user.interaction.TargetInteractionFactory;

import javax.inject.Inject;

/**
 * Instances of this class TODO:Finish
 *
 * @author Blair Butterworth
 */
public class GatherInteraction implements Interaction
{
    private CompositeInteraction interactions;

    @Inject
    public GatherInteraction(TargetInteractionFactory interactionFactory)
    {
        interactions = new CompositeInteraction();
        interactions.add(interactionFactory.get(UserInputType.Action, "GoldMine", "Peasant", null, ActionType.GatherGold));
        interactions.add(interactionFactory.get(UserInputType.Action, "Wood", "Peasant", null, ActionType.GatherWood));
    }

    @Override
    public void update(UserInput input, Item target, Item worldSelection, Item hudSelection)
    {
        interactions.update(input, target, worldSelection, hudSelection);
    }
}
