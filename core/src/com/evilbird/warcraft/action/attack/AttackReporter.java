/*
 * Blair Butterworth (c) 2019
 *
 * This work is licensed under the MIT License. To view a copy of this
 * license, visit
 *
 *      https://opensource.org/licenses/MIT
 */

package com.evilbird.warcraft.action.attack;

import com.evilbird.engine.event.Events;
import com.evilbird.engine.item.Item;
import com.evilbird.warcraft.item.unit.combatant.Combatant;

import javax.inject.Inject;

/**
 * Instances of this class generate {@link AttackEvent AttackEvents} based on
 * the operation of attack actions.
 *
 * @author Blair Butterworth
 */
public class AttackReporter implements AttackObserver
{
    private Events events;
    private boolean notified;

    @Inject
    public AttackReporter(Events events) {
        this.events = events;
        this.notified = false;
    }

    @Override
    public void onAttack(Combatant attacker, Item target) {
        if (! notified) {
            notified = true;
            events.add(new AttackEvent(attacker, target));
        }
    }

    @Override
    public void reset() {
        notified = false;
    }
}
