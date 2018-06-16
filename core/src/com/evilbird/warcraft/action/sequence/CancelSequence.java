package com.evilbird.warcraft.action.sequence;

import com.badlogic.gdx.scenes.scene2d.Action;
import com.evilbird.engine.action.ActionIdentifier;
import com.evilbird.engine.action.common.CancelAction;
import com.evilbird.engine.device.UserInput;
import com.evilbird.engine.item.Item;
import com.evilbird.warcraft.action.ActionProvider;
import com.evilbird.warcraft.action.ActionType;

import javax.inject.Inject;

/**
 * Instances of this class remove actions from the given item.
 *
 * @author Blair Butterworth
 */
public class CancelSequence implements ActionProvider
{
    @Inject
    public CancelSequence() {
    }

    @Override
    public Action get(ActionIdentifier action, Item item, Item target, UserInput input) {
        return new CancelAction(item);
    }
}
