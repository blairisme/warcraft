/*
 * Blair Butterworth (c) 2019
 *
 * This work is licensed under the MIT License. To view a copy of this
 * license, visit
 *
 *      https://opensource.org/licenses/MIT
 */

package com.evilbird.warcraft.action.attack;

import com.evilbird.engine.action.common.DirectionAction;
import com.evilbird.engine.action.common.RepeatedAudibleAction;
import com.evilbird.engine.action.common.ReplacementAction;
import com.evilbird.engine.action.framework.*;
import com.evilbird.engine.item.Item;
import com.evilbird.warcraft.action.common.animation.AnimatedAction;
import com.evilbird.warcraft.action.move.MoveActions;
import com.evilbird.warcraft.action.move.MoveFactory;
import com.evilbird.warcraft.item.unit.UnitAnimation;
import com.evilbird.warcraft.item.unit.UnitSound;
import com.evilbird.warcraft.item.unit.combatant.Combatant;

import javax.inject.Inject;

import static com.evilbird.engine.action.utilities.ActionPredicates.noError;
import static com.evilbird.warcraft.action.common.query.ActionPredicates.isTargetAlive;
import static com.evilbird.warcraft.action.common.query.ActionPredicates.withinRange;

/**
 * Instances of this {@link Action} cause a given {@link Item} to attack
 * another, after first moving within attack range.
 *
 * @author Blair Butterworth
 */
public class AttackAction extends DelegateAction
{
    private AttackObserver observer;

    @Inject
    public AttackAction(MoveFactory moveFactory) {
        Action initiate = attackTarget(moveFactory);
        Action complete = killTarget();
        Action sequence = new SequenceAction(initiate, complete);
        delegate = new ReplacementAction(sequence);
    }

    public void setObserver(AttackObserver observer) {
        this.observer = observer;
    }

    @Override
    public boolean act(float delta) {
        observer.onAttack((Combatant)getItem(), getTarget());
        return delegate.act(delta);
    }

    @Override
    public void restart() {
        super.restart();
        observer.reset();
    }

    private Action attackTarget(MoveFactory moveFactory) {
        Action reposition = reposition(moveFactory);
        Action damage = damage();
        return new PrerequisiteAction(damage, reposition, withinRange());
    }

    private Action reposition(MoveFactory moveFactory) {
        Action reposition = moveFactory.get(MoveActions.MoveToItem);
        Action animation = new AnimatedAction(reposition, UnitAnimation.Move, UnitAnimation.Idle);
        Action move = new RequirementAction(animation, noError());
        Action reorient = new DirectionAction();
        return new SequenceAction(move, reorient);
    }

    private Action damage() {
        Action attack = new DamageAction();
        Action sound = new RepeatedAudibleAction(UnitSound.Attack, 0.5f, isTargetAlive());
        Action damage = new ParallelAction(attack, sound);
        return new AnimatedAction(damage, UnitAnimation.Attack, UnitAnimation.Idle);
    }

    private Action killTarget() {
        Action die = new DeathAction();
        return new ReplacementAction(die);
    }
}
