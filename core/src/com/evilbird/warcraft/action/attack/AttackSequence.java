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
import com.evilbird.engine.item.Item;
import com.evilbird.warcraft.item.unit.UnitSound;
import com.evilbird.warcraft.item.unit.combatant.Combatant;

import javax.inject.Inject;

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
    private AttackReporter reporter;

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
            .whenTarget(isAlive())
            .whenTarget(inRange(combatant))
            .then(animate(MeleeAttack))
            .then(attack(), playRepeat(Attack, target(isAlive())))
            .then(animate(Idle));
    }

    private void kill() {
        scenario("kill")
            .withItem(getTarget())
            .whenTarget(isDead())
            .then(deselect(reporter), disable(), play(UnitSound.Die))
            .then(animate(Die), delay(1))
            .then(animate(Decompose), delay(10))
            .then(remove());
    }
}
