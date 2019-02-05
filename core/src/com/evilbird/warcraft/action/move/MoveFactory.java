/*
 * Blair Butterworth (c) 2018
 *
 * This work is licensed under the MIT License. To view a copy of this
 * license, visit
 *
 *      https://opensource.org/licenses/MIT
 */

package com.evilbird.warcraft.action.move;

import com.badlogic.gdx.math.Vector2;
import com.evilbird.engine.action.ActionContext;
import com.evilbird.engine.action.ActionIdentifier;
import com.evilbird.engine.action.framework.Action;
import com.evilbird.engine.action.utilities.InjectedPool;
import com.evilbird.engine.device.UserInput;
import com.evilbird.engine.item.Item;
import com.evilbird.engine.item.ItemRoot;
import com.evilbird.warcraft.action.ActionProvider;
import com.evilbird.warcraft.action.common.CancelAction;

import javax.inject.Inject;

/**
 * Instances of this class create {@link Action Actions} that instruct a given
 * {@link Item} to move to the given target following a path that conforms to
 * its movement capability.
 *
 * @author Blair Butterworth
 */
public class MoveFactory implements ActionProvider
{
    private MoveReporter reporter;
    private InjectedPool<MoveSequence> movePool;
    private InjectedPool<CancelAction> cancelPool;

    @Inject
    public MoveFactory(
        MoveReporter reporter,
        InjectedPool<MoveSequence> movePool,
        InjectedPool<CancelAction> cancelPool)
    {
        this.reporter = reporter;
        this.movePool = movePool;
        this.cancelPool = cancelPool;
    }

    @Override
    public Action get(ActionIdentifier action, ActionContext context) {
        switch((MoveActions)action) {
            case MoveToLocation: return getMoveToLocationAction(context);
            case MoveToItem: return moveToItemAction(context);
            case MoveCancel: return getMoveCancelAction();
            default: throw new UnsupportedOperationException();
        }
    }

    private Action moveToItemAction(ActionContext context) {
        MoveSequence action = movePool.obtain();
        action.setDestination(context.getItem());
        action.setObserver(reporter);
        return action;
    }

    private Action getMoveToLocationAction(ActionContext context) {
        MoveSequence action = movePool.obtain();
        action.setDestination(getDestinationLocation(context));
        action.setObserver(reporter);
        return action;
    }

    private Action getMoveCancelAction() {
        return cancelPool.obtain();
    }

    private Vector2 getDestinationLocation(ActionContext context) {
        Item item = context.getItem();
        ItemRoot root = item.getRoot();
        UserInput input = context.getInput();
        return root.unproject(input.getPosition());
    }
}
