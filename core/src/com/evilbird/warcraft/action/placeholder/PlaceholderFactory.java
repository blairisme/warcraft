/*
 * Blair Butterworth (c) 2019
 *
 * This work is licensed under the MIT License. To view a copy of this
 * license, visit
 *
 *      https://opensource.org/licenses/MIT
 */

package com.evilbird.warcraft.action.placeholder;

import com.evilbird.engine.action.Action;
import com.evilbird.engine.action.ActionIdentifier;
import com.evilbird.engine.action.common.RemoveAction;
import com.evilbird.engine.action.common.RepositionAction;
import com.evilbird.engine.common.inject.InjectedPool;
import com.evilbird.warcraft.action.ActionProvider;
import org.apache.commons.lang3.Validate;

import javax.inject.Inject;

public class PlaceholderFactory implements ActionProvider
{
    private InjectedPool<RemoveAction> cancelPool;
    private InjectedPool<PlaceholderCreate> createPool;
    private InjectedPool<RepositionAction> movePool;

    @Inject
    public PlaceholderFactory(
        InjectedPool<RemoveAction> cancelPool,
        InjectedPool<PlaceholderCreate> createPool,
        InjectedPool<RepositionAction> movePool)
    {
        this.cancelPool = cancelPool;
        this.createPool = createPool;
        this.movePool = movePool;
    }

    @Override
    public Action get(ActionIdentifier action) {
        Validate.isInstanceOf(PlaceholderActions.class, action);
        PlaceholderActions placeholderAction = (PlaceholderActions)action;

        switch (placeholderAction) {
            case PlaceholderMove: return movePool.obtain();
            case PlaceholderCancel: return cancelPool.obtain();
            default: return getCreateAction(placeholderAction);
        }
    }

    private Action getCreateAction(PlaceholderActions placeholder) {
        PlaceholderCreate action = createPool.obtain();
        action.setType(placeholder.getPlaceholderType());
        return action;
    }
}
