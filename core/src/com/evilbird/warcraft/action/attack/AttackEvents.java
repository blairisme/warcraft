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
import com.evilbird.engine.action.framework.LambdaAction;
import com.evilbird.warcraft.item.unit.combatant.Combatant;

/**
 * Helper class for generating attack events.
 *
 * @author Blair Butterworth
 */
public class AttackEvents
{
    private AttackEvents() {
    }

    public static Action attackStarted(AttackReporter reporter) {
        return new LambdaAction((attacker, target) ->
            reporter.onAttackStarted((Combatant)attacker, target));
    }

    public static Action attackComplete(AttackReporter reporter) {
        return new LambdaAction((attacker, target) ->
            reporter.onAttackCompleted((Combatant)attacker, target));
    }

    public static Action attackCancelled(AttackReporter reporter) {
        return new LambdaAction((attacker, target) ->
            reporter.onAttackCancelled((Combatant)attacker, target));
    }
}
