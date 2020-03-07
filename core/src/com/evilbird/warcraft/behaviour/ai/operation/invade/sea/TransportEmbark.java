/*
 * Copyright (c) 2020, Blair Butterworth
 *
 * This Source Code Form is subject to the terms of the Mozilla Public
 * License, v. 2.0. If a copy of the MPL was not distributed with this
 * file, You can obtain one at http://mozilla.org/MPL/2.0/.
 */

package com.evilbird.warcraft.behaviour.ai.operation.invade.sea;

import com.evilbird.engine.action.Action;
import com.evilbird.engine.action.ActionFactory;
import com.evilbird.engine.behaviour.framework.task.ActionTaskSet;
import com.evilbird.engine.object.GameObject;
import com.evilbird.warcraft.action.transport.TransportActions;

import javax.inject.Inject;
import java.util.ArrayList;
import java.util.Collection;

/**
 * An {@link ActionTaskSet} that assigns an embark action to the transport
 * contained in the tasks blackboard, loading it with attackers.
 *
 * @author Blair Butterworth
 */
public class TransportEmbark extends ActionTaskSet<SeaInvasionData>
{
    @Inject
    public TransportEmbark(ActionFactory factory) {
        super(factory);
    }

    @Override
    protected Collection<Action> getActions(ActionFactory factory) {
        SeaInvasionData data = getObject();
        GameObject transport = data.getTransport();

        Collection<GameObject> attackers = data.getAttackers();
        Collection<Action> actions = new ArrayList<>(attackers.size());

        for (GameObject attacker: attackers) {
            Action action = factory.get(TransportActions.TransportEmbark);
            action.setSubject(attacker);
            action.setTarget(transport);

            attacker.addAction(action);
            actions.add(action);
        }
        return actions;
    }
}
