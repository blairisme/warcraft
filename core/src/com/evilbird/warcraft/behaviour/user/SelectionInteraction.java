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
import com.evilbird.engine.action.ActionFactory;
import com.evilbird.engine.action.ActionIdentifier;
import com.evilbird.engine.device.UserInput;
import com.evilbird.engine.item.Item;

import javax.inject.Inject;

/**
 * Instances of this class represent an interaction that operates on the
 * currently selected {@link Item items}. These items do not necessarily have
 * to be the focus of user input. For example an interaction specifying the
 * selected gatherers should gather from a resource.
 *
 * @author Blair Butterworth
 */
public class SelectionInteraction extends AbstractInteraction
{
    private ActionIdentifier actionType;
    private ActionFactory actionFactory;

    @Inject
    public SelectionInteraction(ActionFactory actionFactory)
    {
        this.actionFactory = actionFactory;
    }

    public void setActionType(ActionIdentifier actionType)
    {
        this.actionType = actionType;
    }

    @Override
    public void update(UserInput input, Item target, Item selected, Item hudSelection)
    {
        Action action = actionFactory.newAction(actionType, selected, target, input);
        selected.addAction(action);
    }
}
