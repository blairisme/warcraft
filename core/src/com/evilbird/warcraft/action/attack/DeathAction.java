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
import com.evilbird.engine.common.lang.Identifier;
import com.evilbird.warcraft.action.common.scenario.ScenarioAction;
import com.evilbird.warcraft.item.unit.Unit;

import static com.evilbird.engine.action.common.AnimateAction.animate;
import static com.evilbird.engine.action.common.AudibleAction.play;
import static com.evilbird.engine.action.common.DisableAction.disable;
import static com.evilbird.engine.action.framework.DelayedAction.delay;
import static com.evilbird.warcraft.action.common.remove.RemoveAction.remove;
import static com.evilbird.warcraft.action.common.transfer.TransferAction.deposit;
import static com.evilbird.warcraft.action.select.SelectAction.deselect;
import static com.evilbird.warcraft.item.common.query.UnitPredicates.isDead;
import static com.evilbird.warcraft.item.unit.UnitAnimation.Death;
import static com.evilbird.warcraft.item.unit.UnitAnimation.Decompose;
import static com.evilbird.warcraft.item.unit.UnitCosts.reservedResources;
import static com.evilbird.warcraft.item.unit.UnitSound.Die;

/**
 * Instances of this {@link Action} animate and remove an item after its been
 * killed.
 *
 * @author Blair Butterworth
 */
public class DeathAction extends ScenarioAction
{
    private AttackReporter reporter;

    public DeathAction(AttackReporter reporter) {
        this.reporter = reporter;
    }

    public static DeathAction kill(AttackReporter reporter) {
        return new DeathAction(reporter);
    }

    @Override
    protected void steps(Identifier identifier) {
        Unit subject = (Unit)getItem();
        whenItem(isDead());
        then(animate(Death), delay(1), play(Die), deselect(reporter), disable());
        then(deposit(reservedResources(subject), reporter));
        then(animate(Decompose), delay(2));
        then(remove(reporter));
    }
}
