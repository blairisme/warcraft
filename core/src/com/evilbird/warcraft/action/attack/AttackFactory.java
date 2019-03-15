/*
 * Blair Butterworth (c) 2019
 *
 * This work is licensed under the MIT License. To view a copy of this
 * license, visit
 *
 *      https://opensource.org/licenses/MIT
 */

package com.evilbird.warcraft.action.attack;

import com.evilbird.engine.action.Action;
import com.evilbird.engine.action.ActionIdentifier;
import com.evilbird.engine.common.inject.InjectedPool;
import com.evilbird.engine.item.Item;
import com.evilbird.warcraft.action.ActionProvider;
import com.evilbird.warcraft.action.common.cancel.CancelAction;
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
    private AttackReporter reporter;
    private InjectedPool<AttackAction> attackPool;
    private InjectedPool<CancelAction> cancelPool;

    @Inject
    public AttackFactory(
        AttackReporter reporter,
        InjectedPool<AttackAction> attackPool,
        InjectedPool<CancelAction> cancelPool)
    {
        this.reporter = reporter;
        this.attackPool = attackPool;
        this.cancelPool = cancelPool;
    }

    @Override
    public Action get(ActionIdentifier action) {
        Validate.isInstanceOf(AttackActions.class, action);
        switch ((AttackActions)action) {
            case AttackMelee: return getMeleeAttackAction();
            case AttackCancel: return getCancelAttackAction();
            default: throw new UnsupportedOperationException();
        }
    }

    private Action getMeleeAttackAction() {
        AttackAction attack = attackPool.obtain();
        attack.setObserver(reporter);
        return attack;
    }

    private Action getCancelAttackAction() {
        return cancelPool.obtain();
    }
}
