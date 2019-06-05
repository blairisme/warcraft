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
import com.evilbird.engine.events.Events;

import static com.evilbird.warcraft.action.attack.AttackStatus.Cancelled;
import static com.evilbird.warcraft.action.attack.AttackStatus.Complete;
import static com.evilbird.warcraft.action.attack.AttackStatus.Started;

/**
 * Helper class for generating attack events.
 *
 * @author Blair Butterworth
 */
public class AttackEvents
{
    private AttackEvents() {
    }

    public static Action attackStarted(Events events) {
        return new LambdaAction((attacker, target) ->
            events.add(new AttackEvent(attacker, target, Started)));
    }

    public static Action attackComplete(Events events) {
        return new LambdaAction((attacker, target) ->
            events.add(new AttackEvent(attacker, target, Complete)));
    }

    public static Action attackCancelled(Events events) {
        return new LambdaAction((attacker, target) ->
            events.add(new AttackEvent(attacker, target, Cancelled)));
    }
}
