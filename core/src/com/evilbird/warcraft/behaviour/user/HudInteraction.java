package com.evilbird.warcraft.behaviour.user;

import com.evilbird.engine.device.UserInput;
import com.evilbird.engine.device.UserInputType;
import com.evilbird.engine.item.Item;
import com.evilbird.warcraft.action.ActionType;
import com.evilbird.warcraft.behaviour.user.interaction.CompositeInteraction;
import com.evilbird.warcraft.behaviour.user.interaction.Interaction;
import com.evilbird.warcraft.behaviour.user.interaction.SelectionInteractionFactory;
import com.evilbird.warcraft.behaviour.user.interaction.TargetInteractionFactory;

import javax.inject.Inject;

/**
 * Instances of this class TODO:Finish
 *
 * @author Blair Butterworth
 */
public class HudInteraction implements Interaction
{
    private CompositeInteraction interactions;

    @Inject
    public HudInteraction(SelectionInteractionFactory selectionFactory, TargetInteractionFactory targetFactory)
    {
        interactions = new CompositeInteraction();
        interactions.add(selectionFactory.get(UserInputType.Action, "StopButton", "Footman", null, ActionType.Stop));
        interactions.add(selectionFactory.get(UserInputType.Action, "StopButton", "Peasant", null, ActionType.Stop));

        interactions.add(selectionFactory.get(UserInputType.Action, "CreateBarracksButton", "Peasant", null, ActionType.CreateBarracksPrototype));
        interactions.add(selectionFactory.get(UserInputType.Action, "BarracksPrototype", "Peasant", null, ActionType.CreateBarracks));

        interactions.add(targetFactory.get(UserInputType.Drag, "BarracksPrototype", null, null, ActionType.Drag));
    }

    @Override
    public boolean update(UserInput input, Item target, Item worldSelection, Item hudSelection)
    {
        return interactions.update(input, target, worldSelection, hudSelection);
    }
}
