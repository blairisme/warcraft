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

import javax.inject.Inject;

import static com.evilbird.warcraft.action.move.MoveActions.MoveToItem;

/**
 * An {@link ActionTask} that assigns moves the transport contained in the
 * tasks blackboard to an embarkation point, prior to loading attackers onto
 * the transport.
 *
 * @author Blair Butterworth
 */
public class TransportDock extends ActionTask<SeaInvasionData>
{
    @Inject
    public TransportDock(ActionFactory factory) {
        super(factory);
    }

    @Override
    protected Action getAction(ActionFactory factory) {
        SeaInvasionData data = getObject();
        GameObject transport = data.getTransport();
        GameObject embarkPoint = data.getEmbarkationPoint();

        Action action = factory.get(MoveToItem);
        action.setSubject(transport);
        action.setTarget(embarkPoint);

        transport.addAction(action);
        return action;
    }
}
