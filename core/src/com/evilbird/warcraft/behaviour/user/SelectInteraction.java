package com.evilbird.warcraft.behaviour.user;

import com.evilbird.engine.device.UserInput;
import com.evilbird.engine.device.UserInputType;
import com.evilbird.engine.item.Item;
import com.evilbird.warcraft.action.ActionType;
import com.evilbird.warcraft.action.type.CommonAction;
import com.evilbird.warcraft.behaviour.user.interaction.CompositeInteraction;
import com.evilbird.warcraft.behaviour.user.interaction.Interaction;
import com.evilbird.warcraft.behaviour.user.interaction.TargetInteractionFactory;
import com.evilbird.warcraft.item.unit.UnitType;

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
        interactions.add(targetFactory.get(UserInputType.Action, UnitType.Footman, null, null, CommonAction.Select));
        interactions.add(targetFactory.get(UserInputType.Action, UnitType.Peasant, null, null, CommonAction.Select));
        interactions.add(targetFactory.get(UserInputType.Action, UnitType.Grunt, null, null, CommonAction.Select));
        interactions.add(targetFactory.get(UserInputType.Action, UnitType.GoldMine, null, null, CommonAction.Select));
        interactions.add(targetFactory.get(UserInputType.Action, UnitType.TownHall, null, null, CommonAction.Select));
        interactions.add(targetFactory.get(UserInputType.Action, UnitType.Barracks, null, null, CommonAction.Select));
        interactions.add(targetFactory.get(UserInputType.Action, UnitType.Farm, null, null, CommonAction.Select));
    }

    @Override
    public boolean update(UserInput input, Item target, Item worldSelection, Item hudSelection)
    {
        return interactions.update(input, target, worldSelection, hudSelection);
    }
}
