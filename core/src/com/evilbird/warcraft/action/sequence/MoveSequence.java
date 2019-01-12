package com.evilbird.warcraft.action.sequence;

import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.scenes.scene2d.Action;
import com.evilbird.engine.action.ActionIdentifier;
import com.evilbird.engine.action.common.ReplacementAction;
import com.evilbird.engine.action.framework.ParallelAction;
import com.evilbird.engine.device.UserInput;
import com.evilbird.engine.item.Item;
import com.evilbird.engine.item.ItemRoot;
import com.evilbird.warcraft.action.ActionProvider;
import com.evilbird.warcraft.action.component.AnimatedMoveAction;

import javax.inject.Inject;

/**
 * Instances of this class TODO:Finish
 *
 * @author Blair Butterworth
 */
public class MoveSequence implements ActionProvider
{
    private ConfirmSequence confirmSequence;

    @Inject
    public MoveSequence(ConfirmSequence confirmSequence) {
        this.confirmSequence = confirmSequence;
    }

    @Override
    public Action get(ActionIdentifier action, Item item, Item target, UserInput input) {
        ItemRoot root = item.getRoot();
        Vector2 destination = root.unproject(input.getPosition());
        Action confirm = confirmSequence.get(item.getParent(), destination);
        Action move = new AnimatedMoveAction(item, destination);
        Action confirmedMove = new ParallelAction(confirm, move);
        return new ReplacementAction(item, confirmedMove);
    }
}
