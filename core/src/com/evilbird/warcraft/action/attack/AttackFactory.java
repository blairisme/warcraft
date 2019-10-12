/*
 * Copyright (c) 2019, Blair Butterworth
 *
 * This work is licensed under the MIT License. To view a copy of this
 * license, visit
 *
 *        https://opensource.org/licenses/MIT
 */

package com.evilbird.warcraft.action.attack;

import com.evilbird.engine.action.Action;
import com.evilbird.engine.action.ActionIdentifier;
import com.evilbird.engine.common.inject.InjectedPool;
import com.evilbird.engine.item.Item;
import com.evilbird.warcraft.action.ActionProvider;
import org.apache.commons.lang3.Validate;

import javax.inject.Inject;

/**
 * Instances of this factory create {@link Action Actions} that cause a given
 * {@link Item} to attack another, after first moving within attack range.
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
