package com.evilbird.warcraft.behaviour.user;

import com.evilbird.engine.device.UserInput;
import com.evilbird.engine.device.UserInputType;
import com.evilbird.engine.item.Item;
import com.evilbird.warcraft.action.ActionType;
import com.evilbird.warcraft.behaviour.user.interaction.CompositeInteraction;
import com.evilbird.warcraft.behaviour.user.interaction.Interaction;
import com.evilbird.warcraft.behaviour.user.interaction.SelectionInteractionFactory;
import com.evilbird.warcraft.item.layer.LayerType;
import com.evilbird.warcraft.item.unit.UnitType;

import javax.inject.Inject;

/**
 * Instances of this class TODO:Finish
 *
 * @author Blair Butterworth
 */
public class MoveInteraction implements Interaction
{
    private CompositeInteraction interactions;

    @Inject
    public MoveInteraction(SelectionInteractionFactory interactionFactory)
    {
        interactions = new CompositeInteraction();
        interactions.add(interactionFactory.get(UserInputType.Action, LayerType.Map, UnitType.Footman, null, ActionType.Move));
        interactions.add(interactionFactory.get(UserInputType.Action, LayerType.Map, UnitType.Peasant, null, ActionType.Move));
        interactions.add(interactionFactory.get(UserInputType.Action, LayerType.Map, UnitType.Grunt, null, ActionType.Move));
    }

    @Override
    public boolean update(UserInput input, Item target, Item worldSelection, Item hudSelection)
    {
        return interactions.update(input, target, worldSelection, hudSelection);
    }
}
