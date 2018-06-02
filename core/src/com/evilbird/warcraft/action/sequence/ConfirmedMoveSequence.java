package com.evilbird.warcraft.action.sequence;

import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.scenes.scene2d.Action;
import com.evilbird.engine.action.framework.SequenceAction;
import com.evilbird.engine.device.UserInput;
import com.evilbird.engine.item.Item;
import com.evilbird.engine.item.ItemRoot;
import com.evilbird.warcraft.action.ActionProvider;
import com.evilbird.warcraft.action.ActionType;
import com.evilbird.warcraft.action.subsequence.ConfirmSubsequence;
import com.evilbird.warcraft.action.subsequence.MoveSubsequence;

import javax.inject.Inject;

/**
 * Instances of this class TODO:Finish
 *
 * @author Blair Butterworth
 */
public class MoveSequence implements ActionProvider
{
    private MoveSubsequence moveSubsequence;
    private ConfirmSubsequence confirmSubsequence;

    @Inject
    public MoveSequence(
        MoveSubsequence moveSubsequence,
        ConfirmSubsequence confirmSubsequence)
    {
        this.moveSubsequence = moveSubsequence;
        this.confirmSubsequence = confirmSubsequence;
    }

    @Override
    public Action get(ActionType action, Item item, Item target, UserInput input)
    {
        ItemRoot root = item.getRoot();
        Vector2 destination = root.unproject(input.getPosition());
        Action confirm = confirmSubsequence.get(item.getParent(), destination);
        Action move = moveSubsequence.get(item, destination);
        return new SequenceAction(confirm, move);
    }
}
