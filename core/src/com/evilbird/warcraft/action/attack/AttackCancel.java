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
import com.evilbird.engine.common.lang.Identifier;
import com.evilbird.warcraft.action.common.scenario.ScenarioAction;
import com.evilbird.warcraft.item.unit.combatant.Combatant;

import javax.inject.Inject;

import static com.evilbird.engine.action.common.AnimateAction.animate;
import static com.evilbird.warcraft.item.unit.UnitAnimation.Idle;

/**
 * Instances of this class stop a {@link Combatant} from attacking.
 *
 * @author Blair Butterworth
 */
public class AttackCancel extends ScenarioAction
{
    private AttackReporter reporter;

    @Inject
    public AttackCancel(AttackReporter reporter) {
        this.reporter = reporter;
    }

    @Override
    protected void steps(Identifier identifier) {
        scenario(AttackActions.AttackCancel);
        then(animate(Idle), notifyCancelled());
    }

    private Action notifyCancelled() {
        return new LambdaAction((attacker, target) ->
            reporter.onAttackCancelled((Combatant)attacker, target));
    }
}
