/*
 * Copyright (c) 2020, Blair Butterworth
 *
 * This Source Code Form is subject to the terms of the Mozilla Public
 * License, v. 2.0. If a copy of the MPL was not distributed with this
 * file, You can obtain one at http://mozilla.org/MPL/2.0/.
 */

package com.evilbird.warcraft.behaviour.ai.invade;

import com.evilbird.engine.action.Action;
import com.evilbird.engine.action.ActionFactory;
import com.evilbird.engine.object.GameObject;
import com.evilbird.engine.behaviour.framework.task.ActionTaskSet;

import javax.inject.Inject;
import java.util.ArrayList;
import java.util.Collection;

import static com.evilbird.warcraft.action.attack.AttackActions.Attack;

/**
 * A task that assigns an attack action to the attackers contained in the
 * tasks blackboard.
 *
 * @author Blair Butterworth
 */
public class InvadeTask extends ActionTaskSet<InvadeData>
{
    @Inject
    public InvadeTask(ActionFactory factory) {
       super(factory);
    }

    @Override
    protected Collection<Action> getActions(ActionFactory factory) {
        InvadeData data = getObject();
        GameObject enemy = data.getEnemyCommand();

        Collection<GameObject> attackers = data.getAttackers();
        Collection<Action> actions = new ArrayList<>(attackers.size());

        for (GameObject attacker: attackers) {
            Action action = getAction(factory, attacker, enemy);
            actions.add(action);
        }
        return actions;
    }

    protected Action getAction(ActionFactory factory, GameObject attacker, GameObject enemy) {
        Action action = factory.get(Attack);
        action.setSubject(attacker);
        action.setTarget(enemy);

        attacker.removeActions();
        attacker.addAction(action);

        return action;
    }
}
