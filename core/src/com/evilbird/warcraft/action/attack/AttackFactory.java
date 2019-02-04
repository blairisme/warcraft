/*
 * Blair Butterworth (c) 2019
 *
 * This work is licensed under the MIT License. To view a copy of this
 * license, visit
 *
 *      https://opensource.org/licenses/MIT
 */

package com.evilbird.warcraft.action.attack;

import com.badlogic.gdx.scenes.scene2d.Action;
import com.evilbird.engine.action.ActionContext;
import com.evilbird.engine.action.ActionIdentifier;
import com.evilbird.engine.action.common.AnimateAction;
import com.evilbird.engine.action.common.ReplacementAction;
import com.evilbird.engine.action.utilities.InjectedPool;
import com.evilbird.engine.item.Item;
import com.evilbird.engine.item.specialized.animated.Animated;
import com.evilbird.warcraft.action.ActionProvider;
import com.evilbird.warcraft.item.unit.UnitAnimation;
import com.evilbird.warcraft.item.unit.combatant.Combatant;
import org.apache.commons.lang3.Validate;

import javax.inject.Inject;

/**
 * Instances of this factory create {@link Action Actions} that cause a given
 * {@link Item} to attack another, after first moving within attack range.
 *
 * @author Blair Butterworth
 */
//TODO: Use pools
//TODO: Move replacement into actions?
public class AttackFactory implements ActionProvider
{
    private AttackReporter reporter;
    private InjectedPool<AttackAction> pool;

    @Inject
    public AttackFactory(InjectedPool<AttackAction> pool, AttackReporter reporter) {
        this.pool = pool;
        this.reporter = reporter;
    }

    @Override
    public Action get(ActionIdentifier action, ActionContext context) {
        Validate.isInstanceOf(AttackActions.class, action);
        switch ((AttackActions)action) {
            case AttackMelee: return getMeleeAttackAction(context);
            case AttackCancel: return getCancelAttackAction(context);
            default: throw new UnsupportedOperationException();
        }
    }

    private Action getMeleeAttackAction(ActionContext context) {
        AttackAction attack = new AttackAction();//pool.obtain();
        attack.setObserver(reporter);
        attack.setAttacker((Combatant)context.getItem());
        attack.setTarget(context.getTarget());
        return new ReplacementAction(context.getItem(), attack);
    }

    private Action getCancelAttackAction(ActionContext context) {
        Action idle = new AnimateAction((Animated)context.getItem(), UnitAnimation.Idle);
        return new ReplacementAction(context.getItem(), idle);
    }
}
