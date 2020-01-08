/*
 * Copyright (c) 2020, Blair Butterworth
 *
 * This Source Code Form is subject to the terms of the Mozilla Public
 * License, v. 2.0. If a copy of the MPL was not distributed with this
 * file, You can obtain one at http://mozilla.org/MPL/2.0/.
 */

package com.evilbird.warcraft.action.attack;

import com.evilbird.engine.action.Action;
import com.evilbird.engine.action.ActionIdentifier;
import com.evilbird.engine.common.inject.InjectedPool;
import com.evilbird.engine.object.GameObject;
import com.evilbird.warcraft.action.ActionProvider;
import org.apache.commons.lang3.Validate;

import javax.inject.Inject;

/**
 * Instances of this factory create {@link Action Actions} that cause a given
 * {@link GameObject} to attack another, after first moving within attack range.
 *
 * @author Blair Butterworth
 */
public class AttackFactory implements ActionProvider
{
    private InjectedPool<AttackAction> attackPool;
    private InjectedPool<AttackCancel> cancelPool;

    @Inject
    public AttackFactory(
        InjectedPool<AttackAction> attackPool,
        InjectedPool<AttackCancel> cancelPool)
    {
        this.attackPool = attackPool;
        this.cancelPool = cancelPool;
    }

    @Override
    public Action get(ActionIdentifier action) {
        Validate.isInstanceOf(AttackActions.class, action);
        switch ((AttackActions)action) {
            case Attack: return getAction(attackPool, action);
            case AttackCancel: return getAction(cancelPool, action);
            default: throw new UnsupportedOperationException();
        }
    }

    private <T extends Action> T getAction(InjectedPool<T> pool, ActionIdentifier identifier) {
        T result = pool.obtain();
        result.setIdentifier(identifier);
        return result;
    }
}
