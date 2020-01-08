/*
 * Copyright (c) 2020, Blair Butterworth
 *
 * This Source Code Form is subject to the terms of the Mozilla Public
 * License, v. 2.0. If a copy of the MPL was not distributed with this
 * file, You can obtain one at http://mozilla.org/MPL/2.0/.
 */

package com.evilbird.warcraft.action.selector;

import com.evilbird.engine.action.Action;
import com.evilbird.engine.action.ActionIdentifier;
import com.evilbird.engine.common.inject.InjectedPool;
import com.evilbird.warcraft.action.ActionProvider;
import org.apache.commons.lang3.Validate;

import javax.inject.Inject;

import static com.evilbird.warcraft.action.selector.SelectorActions.HideSelector;
import static com.evilbird.warcraft.action.selector.SelectorActions.MoveSelector;

/**
 * Instances of this factory create {@link Action Actions} related to
 * selectors, user interface elements used to by the user to select locations
 * or units.
 *
 * @author Blair Butterworth
 */
public class SelectorFactory implements ActionProvider
{
    private InjectedPool<SelectorArea> areaPool;
    private InjectedPool<SelectorCancel> cancelPool;
    private InjectedPool<SelectorCreate> createPool;
    private InjectedPool<SelectorMove> movePool;

    @Inject
    public SelectorFactory(
        InjectedPool<SelectorArea> areaPool,
        InjectedPool<SelectorCancel> cancelPool,
        InjectedPool<SelectorCreate> createPool,
        InjectedPool<SelectorMove> movePool)
    {
        this.areaPool = areaPool;
        this.cancelPool = cancelPool;
        this.createPool = createPool;
        this.movePool = movePool;
    }

    @Override
    public Action get(ActionIdentifier action) {
        Validate.isInstanceOf(SelectorActions.class, action);
        SelectorActions identifier = (SelectorActions)action;

        switch (identifier) {
            case ShowAreaSelector:
            case ResizeAreaSelector:
            case HideAreaSelector: return getAction(areaPool, identifier);
            case MoveSelector: return getAction(movePool, MoveSelector);
            case HideSelector: return getAction(cancelPool, HideSelector);
            default: return getAction(createPool, identifier);
        }
    }

    private <T extends Action> Action getAction(InjectedPool<T> pool, SelectorActions identifier) {
        T action = pool.obtain();
        action.setIdentifier(identifier);
        return action;
    }
}
