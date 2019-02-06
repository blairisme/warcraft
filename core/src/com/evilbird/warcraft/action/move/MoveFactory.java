/*
 * Blair Butterworth (c) 2018
 *
 * This work is licensed under the MIT License. To view a copy of this
 * license, visit
 *
 *      https://opensource.org/licenses/MIT
 */

package com.evilbird.warcraft.action.move;

import com.evilbird.engine.action.ActionContext;
import com.evilbird.engine.action.ActionIdentifier;
import com.evilbird.engine.action.framework.Action;
import com.evilbird.engine.action.utilities.InjectedPool;
import com.evilbird.engine.item.Item;
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
    private InjectedPool<CancelAction> cancelPool;
    private InjectedPool<MoveToItem> moveItemPool;
    private InjectedPool<MoveToLocation> moveLocationPool;

    @Inject
    public MoveFactory(
        MoveReporter reporter,
        InjectedPool<CancelAction> cancelPool,
        InjectedPool<MoveToItem> moveItemPool,
        InjectedPool<MoveToLocation> moveLocationPool)
    {
        this.reporter = reporter;
        this.cancelPool = cancelPool;
        this.moveItemPool = moveItemPool;
        this.moveLocationPool = moveLocationPool;
    }

    @Override
    public Action get(ActionIdentifier action, ActionContext context) {
        switch((MoveActions)action) {
            case MoveToLocation: return getMoveToLocationAction();
            case MoveToItem: return moveToItemAction();
            case MoveCancel: return getMoveCancelAction();
            default: throw new UnsupportedOperationException();
        }
    }

    private Action moveToItemAction() {
        MoveSequence action = moveItemPool.obtain();
        action.setObserver(reporter);
        return action;
    }

    private Action getMoveToLocationAction() {
        MoveSequence action = moveLocationPool.obtain();
        action.setObserver(reporter);
        return action;
    }

    private Action getMoveCancelAction() {
        return cancelPool.obtain();
    }
}
