package com.evilbird.warcraft.behaviour.user.interaction;

import com.badlogic.gdx.scenes.scene2d.Action;
import com.evilbird.engine.action.ActionFactory;
import com.evilbird.engine.action.ActionIdentifier;
import com.evilbird.engine.device.UserInput;
import com.evilbird.engine.item.Item;
import com.evilbird.warcraft.action.ActionType;

import javax.inject.Inject;

/**
 * Instances of this class TODO:Finish
 *
 * @author Blair Butterworth
 */
public class TargetInteraction extends AbstractInteraction
{
    private ActionIdentifier actionType;
    private ActionFactory actionFactory;

    @Inject
    public TargetInteraction(ActionFactory actionFactory)
    {
        this.actionFactory = actionFactory;
    }

    public void setActionType(ActionIdentifier actionType)
    {
        this.actionType = actionType;
    }

    @Override
    public void apply(UserInput input, Item target, Item selected)
    {
        if (actionType != null)
        {
            Action action = actionFactory.newAction(actionType, target, selected, input);
            target.addAction(action);
        }
    }
}
