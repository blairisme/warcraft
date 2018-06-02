package com.evilbird.warcraft.action.sequence;

import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.scenes.scene2d.Action;
import com.evilbird.engine.action.framework.SequenceAction;
import com.evilbird.engine.device.UserInput;
import com.evilbird.engine.item.Item;
import com.evilbird.engine.item.ItemRoot;
import com.evilbird.warcraft.action.ActionProvider;
import com.evilbird.warcraft.action.ActionType;
import com.evilbird.warcraft.action.common.ReplacementAction;

import javax.inject.Inject;

/**
 * Instances of this class TODO:Finish
 *
 * @author Blair Butterworth
 */
public class ConfirmedMoveSequence implements ActionProvider
{
    private MoveSequence moveSequence;
    private ConfirmSequence confirmSequence;

    @Inject
    public ConfirmedMoveSequence(
        MoveSequence moveSequence,
        ConfirmSequence confirmSequence)
    {
        this.moveSequence = moveSequence;
        this.confirmSequence = confirmSequence;
    }

    @Override
    public Action get(ActionType action, Item item, Item target, UserInput input)
    {
        ItemRoot root = item.getRoot();
        Vector2 destination = root.unproject(input.getPosition());
        Action confirm = confirmSequence.get(item.getParent(), destination);
        Action move = moveSequence.get(item, destination);
        Action confirmedMove = new SequenceAction(confirm, move);
        return new ReplacementAction(item, confirmedMove);
    }
}
