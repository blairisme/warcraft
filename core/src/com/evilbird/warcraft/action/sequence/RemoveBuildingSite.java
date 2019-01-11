package com.evilbird.warcraft.action.sequence;

import com.badlogic.gdx.scenes.scene2d.Action;
import com.evilbird.engine.action.ActionIdentifier;
import com.evilbird.engine.action.common.RemoveAction;
import com.evilbird.engine.device.UserInput;
import com.evilbird.engine.item.Item;
import com.evilbird.warcraft.action.ActionProvider;

import javax.inject.Inject;

/**
 * Instances of this class remove a given building site.
 *
 * @author Blair Butterworth
 */
public class RemoveBuildingSite implements ActionProvider
{
    @Inject
    public RemoveBuildingSite() {
    }

    @Override
    public Action get(ActionIdentifier action, Item item, Item target, UserInput input) {
        return new RemoveAction(item);
    }
}
