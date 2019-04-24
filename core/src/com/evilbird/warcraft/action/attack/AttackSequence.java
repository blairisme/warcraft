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
import com.evilbird.engine.action.framework.FeatureAction;
import com.evilbird.engine.action.framework.LambdaAction;
import com.evilbird.engine.item.Item;
import com.evilbird.warcraft.item.unit.UnitSound;
import com.evilbird.warcraft.item.unit.combatant.Combatant;

import javax.inject.Inject;

import java.lang.annotation.Target;

import static com.evilbird.engine.action.common.ActionTarget.Target;
import static com.evilbird.engine.action.common.AnimateAction.animate;
import static com.evilbird.engine.action.common.AudibleAction.play;
import static com.evilbird.engine.action.common.DisableAction.disable;
import static com.evilbird.engine.action.common.RepeatedAudibleAction.playRepeat;
import static com.evilbird.engine.action.framework.DelayedAction.delay;
import static com.evilbird.engine.action.predicates.ActionPredicates.target;
import static com.evilbird.warcraft.action.attack.AttackAction.attack;
import static com.evilbird.warcraft.action.attack.AttackActions.AttackMelee;
import static com.evilbird.warcraft.action.common.remove.RemoveAction.remove;
import static com.evilbird.warcraft.action.move.MoveToItemAction.move;
import static com.evilbird.warcraft.action.select.SelectAction.deselect;
import static com.evilbird.warcraft.item.common.query.UnitPredicates.*;
import static com.evilbird.warcraft.item.unit.UnitAnimation.*;
import static com.evilbird.warcraft.item.unit.UnitSound.Attack;

/**
 * Instances of this {@link Action} cause a given {@link Item} to attack
 * another, after first moving within attack range.
 *
 * @author Blair Butterworth
 */
public class AttackSequence extends FeatureAction
{
    private transient AttackReporter reporter;

    @Inject
    public AttackSequence(AttackReporter reporter) {
        this.reporter = reporter;
        feature(AttackMelee);
        reevaluate();
    }

    @Override
    protected void features() {
        Combatant combatant = (Combatant)getItem();
        reposition(combatant);
        engage(combatant);
        kill();
    }

    private void reposition(Combatant combatant) {
        scenario("reposition")
            .givenItem(isAlive())
            .givenTarget(isAlive())
            .whenTarget(notInRange(combatant))
            .then(animate(Move))
            .then(move(reporter))
            .then(animate(Idle));
    }

    private void engage(Combatant combatant) {
        scenario("attack")
            .givenItem(isAlive())
//            .givenTarget(isAlive())
            .whenTarget(isAlive())
            .whenTarget(inRange(combatant))
            .then(animate(MeleeAttack), notifyStarted())
            .then(attack(), playRepeat(Attack, target(isAlive())))
            .then(animate(Idle), notifyComplete());
    }

    private void kill() {
        scenario("kill")
            .whenTarget(isDead())
            .then(animate(Target, Die), delay(1), play(Target, Die), deselect(Target, reporter), disable(Target))
            .then(animate(Target, Decompose), delay(2))
            .then(remove(Target, reporter));
    }

    private Action notifyStarted() {
        return new LambdaAction((attacker, target) ->
            reporter.onAttackStarted((Combatant)attacker, target));
    }

    private Action notifyComplete() {
        return new LambdaAction((attacker, target) ->
            reporter.onAttackCompleted((Combatant)attacker, target));
    }
}
