/*
 * Copyright (c) 2020, Blair Butterworth
 *
 * This Source Code Form is subject to the terms of the Mozilla Public
 * License, v. 2.0. If a copy of the MPL was not distributed with this
 * file, You can obtain one at http://mozilla.org/MPL/2.0/.
 */

package com.evilbird.warcraft.behaviour.ainew.invade;

import com.badlogic.gdx.ai.btree.LeafTask;
import com.badlogic.gdx.ai.btree.Task;
import com.evilbird.engine.action.Action;
import com.evilbird.engine.action.ActionFactory;
import com.evilbird.engine.object.GameObject;

import static com.evilbird.warcraft.action.attack.AttackActions.Attack;

/**
 * A task that assigns an attack action to the attackers contained in the
 * tasks blackboard.
 *
 * @author Blair Butterworth
 */
public class InvadeTask extends LeafTask<InvadeData>
{
    private ActionFactory factory;

    public InvadeTask(ActionFactory factory) {
        this.factory = factory;
    }

    @Override
    public Status execute() {
        InvadeData data = getObject();
        GameObject enemy = data.getEnemyCommand();

        for (GameObject attacker: data.getAttackers()) {
            Action action = factory.get(Attack);
            action.setSubject(attacker);
            action.setTarget(enemy);

            attacker.removeActions();
            attacker.addAction(action);
        }
        return Status.SUCCEEDED;
    }

    @Override
    protected Task<InvadeData> copyTo(Task<InvadeData> task) {
        InvadeTask invadeTask = (InvadeTask)task;
        invadeTask.factory = this.factory;
        return invadeTask;
    }
}
