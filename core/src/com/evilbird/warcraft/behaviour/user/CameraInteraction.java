package com.evilbird.warcraft.behaviour.user;

import com.evilbird.engine.device.UserInput;
import com.evilbird.engine.device.UserInputType;
import com.evilbird.engine.item.Item;
import com.evilbird.warcraft.action.ActionType;
import com.evilbird.warcraft.behaviour.user.interaction.CompositeInteraction;
import com.evilbird.warcraft.behaviour.user.interaction.Interaction;
import com.evilbird.warcraft.behaviour.user.interaction.TargetInteractionFactory;
import com.evilbird.warcraft.item.data.DataType;

import javax.inject.Inject;

/**
 * Instances of this class TODO:Finish
 *
 * @author Blair Butterworth
 */
public class CameraInteraction implements Interaction
{
    private CompositeInteraction interactions;

    @Inject
    public CameraInteraction(TargetInteractionFactory targetFactory)
    {
        interactions = new CompositeInteraction();
        interactions.add(targetFactory.get(UserInputType.Zoom, DataType.Camera, null, null, ActionType.Zoom));
        interactions.add(targetFactory.get(UserInputType.Drag, DataType.Camera, null, null, ActionType.Pan));
    }

    @Override
    public boolean update(UserInput input, Item target, Item worldSelection, Item hudSelection)
    {
        return interactions.update(input, target, worldSelection, hudSelection);
    }
}
