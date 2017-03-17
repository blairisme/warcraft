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
public class SelectInteraction implements Interaction
{
    private CompositeInteraction interactions;

    @Inject
    public SelectInteraction(TargetInteractionFactory targetFactory)
    {
        interactions = new CompositeInteraction();
        interactions.add(targetFactory.get(UserInputType.Action, "Footman", null, null, ActionType.Select));
        interactions.add(targetFactory.get(UserInputType.Action, "Peasant", null, null, ActionType.Select));
        interactions.add(targetFactory.get(UserInputType.Action, "Grunt", null, null, ActionType.Select));
        interactions.add(targetFactory.get(UserInputType.Action, "Gold", null, null, ActionType.Select));
        interactions.add(targetFactory.get(UserInputType.Action, "TownHall", null, null, ActionType.Select));
        interactions.add(targetFactory.get(UserInputType.Action, "Barracks", null, null, ActionType.Select));
        interactions.add(targetFactory.get(UserInputType.Action, "Farm", null, null, ActionType.Select));
    }

    @Override
    public boolean update(UserInput input, Item target, Item worldSelection, Item hudSelection)
    {
        return interactions.update(input, target, worldSelection, hudSelection);
    }
}
