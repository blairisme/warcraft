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
import com.evilbird.engine.behaviour.framework.task.ActionTask;
import com.evilbird.engine.object.GameObject;
import com.evilbird.warcraft.action.transport.TransportActions;

import javax.inject.Inject;

/**
 * An {@link ActionTask} that assigns a disembark action to the transport
 * contained in the tasks blackboard, offloading any transported attackers.
 *
 * @author Blair Butterworth
 */
public class TransportDisembark extends ActionTask<SeaInvasionData>
{
    @Inject
    public TransportDisembark(ActionFactory factory) {
        super(factory);
    }

    @Override
    protected Action getAction(ActionFactory factory) {
        SeaInvasionData data = getObject();
        GameObject transport = data.getTransport();

        Action action = factory.get(TransportActions.TransportDisembark);
        action.setSubject(transport);

        transport.addAction(action);
        return action;
    }
}

