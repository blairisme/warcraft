/*
 * Copyright (c) 2019, Blair Butterworth
 *
 * This work is licensed under the MIT License. To view a copy of this
 * license, visit
 *
 *        https://opensource.org/licenses/MIT
 */

package com.evilbird.warcraft.action.selector;

import com.evilbird.engine.action.Action;
import com.evilbird.engine.action.ActionIdentifier;
import com.evilbird.engine.common.inject.InjectedPool;
import com.evilbird.warcraft.action.ActionProvider;
import org.apache.commons.lang3.Validate;

import javax.inject.Inject;

import static com.evilbird.warcraft.action.selector.SelectorActions.SelectorCancel;
import static com.evilbird.warcraft.action.selector.SelectorActions.SelectorMove;

/**
 * Instances of this factory create {@link Action Actions} related to
 * selectors, user interface elements used to by the user to select locations
 * or units.
 *
 * @author Blair Butterworth
 */
public class SelectorFactory implements ActionProvider
{
    private InjectedPool<SelectorCancel> cancelPool;
    private InjectedPool<SelectorCreate> createPool;
    private InjectedPool<SelectorMove> movePool;

    @Inject
    public SelectorFactory(
        InjectedPool<SelectorCancel> cancelPool,
        InjectedPool<SelectorCreate> createPool,
        InjectedPool<SelectorMove> movePool)
    {
        this.cancelPool = cancelPool;
        this.createPool = createPool;
        this.movePool = movePool;
    }

    @Override
    public Action get(ActionIdentifier action) {
        Validate.isInstanceOf(SelectorActions.class, action);
        SelectorActions identifier = (SelectorActions)action;

        switch (identifier) {
            case SelectorMove: return getAction(movePool, SelectorMove);
            case SelectorCancel: return getAction(cancelPool, SelectorCancel);
            default: return getAction(createPool, identifier);
        }
    }

    private <T extends Action> Action getAction(InjectedPool<T> pool, SelectorActions identifier) {
        T action = pool.obtain();
        action.setIdentifier(identifier);
        return action;
    }
}
