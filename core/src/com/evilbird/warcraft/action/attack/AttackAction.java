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
import com.evilbird.engine.common.lang.Directionable;
import com.evilbird.engine.item.Item;
import com.evilbird.engine.item.specialized.animated.Animated;
import com.evilbird.warcraft.action.common.AnimatedAction;
import com.evilbird.warcraft.action.move.MoveAction;
import com.evilbird.warcraft.item.common.capability.Destroyable;
import com.evilbird.warcraft.item.common.capability.Movable;
import com.evilbird.warcraft.item.unit.UnitAnimation;
import com.evilbird.warcraft.item.unit.UnitSound;
import com.evilbird.warcraft.item.unit.combatant.Combatant;

import javax.inject.Inject;

import static com.evilbird.engine.action.utilities.ActionPredicates.noError;
import static com.evilbird.engine.item.ItemSuppliers.isAlive;
import static com.evilbird.warcraft.action.common.ActionPredicates.withinRange;

/**
 * Instances of this {@link Action} cause a given {@link Item} to attack
 * another, after first moving within attack range.
 *
 * @author Blair Butterworth
 */
//TODO: Reuse action sequence
public class AttackAction extends DelegateAction
{
    private AttackObserver observer;
    private Combatant attacker;
    private Item target;

    @Inject
    public AttackAction() {
        this.delegate = null;
    }

    public void setAttacker(Combatant attacker) {
        this.attacker = attacker;
        this.delegate = null;
    }

    public void setTarget(Item target) {
        this.target = target;
        this.delegate = null;
    }

    public void setObserver(AttackObserver observer) {
        this.observer = observer;
    }

    @Override
    public boolean act(float delta) {
        if (delegate == null) {
            delegate = sequence(attacker, target);
            observer.onAttack(attacker, target);
        }
        return delegate.act(delta);
    }

    private Action sequence(Combatant attacker, Item target) {
        Action attack = attack(attacker, target);
        Action complete = die(target);
        return new SequenceAction(attack, complete);
    }

    private Action attack(Combatant attacker, Item target) {
        Action reposition = reposition(attacker, target);
        Action damage = damage(attacker, target);
        return new PrerequisiteAction(damage, reposition, withinRange(attacker, target));
    }

    private Action reposition(Item attacker, Item target) {
        Action reposition = new MoveAction((Movable)attacker, target);
        Action animation = new AnimatedAction(reposition, (Animated)attacker, UnitAnimation.Move, UnitAnimation.Idle);
        Action move = new RequirementAction(animation, noError());
        Action reorient = new DirectionAction((Directionable)attacker, target);
        return new SequenceAction(move, reorient);
    }

    private Action damage(Item attacker, Item target) {
        Action attack = new DamageAction((Combatant)attacker, (Destroyable)target);
        Action sound = new RepeatedAudibleAction(attacker, UnitSound.Attack, 0.5f, isAlive((Destroyable)target));
        Action damage = new ParallelAction(attack, sound);
        return new AnimatedAction(damage, (Animated)attacker, UnitAnimation.Attack, UnitAnimation.Idle);
    }

    private Action die(Item target) {
        Action die = new DeathAction(target);
        return new ReplacementAction(target, die);
    }
}
