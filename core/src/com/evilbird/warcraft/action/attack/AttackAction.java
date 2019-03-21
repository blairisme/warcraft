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
import com.evilbird.engine.action.common.DirectionAction;
import com.evilbird.engine.action.common.RepeatedAudibleAction;
import com.evilbird.engine.action.framework.*;
import com.evilbird.engine.item.Item;
import com.evilbird.warcraft.action.common.animation.AnimatedAction;
import com.evilbird.warcraft.action.move.MoveToItemSequence;
import com.evilbird.warcraft.item.unit.UnitAnimation;
import com.evilbird.warcraft.item.unit.UnitSound;
import com.evilbird.warcraft.item.unit.combatant.Combatant;
import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;

import javax.inject.Inject;

import static com.evilbird.engine.action.predicates.ActionPredicates.withoutError;
import static com.evilbird.warcraft.action.attack.AttackActions.AttackMelee;
import static com.evilbird.warcraft.action.common.predicate.ActionPredicates.isTargetAlive;
import static com.evilbird.warcraft.action.common.predicate.ActionPredicates.withinRange;

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
    public AttackAction() {
        setIdentifier(AttackMelee);
        Action initiate = attackTarget();
        Action complete = killTarget();
        Action sequence = new SequenceAction(initiate, complete);
        delegate = sequence;
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

    private Action attackTarget() {
        Action reposition = reposition();
        Action damage = damage();
        return new PrerequisiteAction(damage, reposition, withinRange());
    }

    private Action reposition() {
        Action reposition = new MoveToItemSequence();
        Action animation = new AnimatedAction(reposition, UnitAnimation.Move, UnitAnimation.Idle);
        Action move = new RequirementAction(animation, withoutError());
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
        return die;
//        return new ReplacementAction(die);
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == this) return true;
        if (obj == null) return false;
        if (obj.getClass() != getClass()) return false;

        AttackAction that = (AttackAction)obj;
        return new EqualsBuilder()
            .appendSuper(super.equals(obj))
            .append(observer, that.observer)
            .isEquals();
    }

    @Override
    public int hashCode() {
        return new HashCodeBuilder(17, 37)
            .appendSuper(super.hashCode())
            .append(observer)
            .toHashCode();
    }
}
