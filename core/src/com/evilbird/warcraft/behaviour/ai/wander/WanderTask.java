/*
 * Copyright (c) 2020, Blair Butterworth
 *
 * This Source Code Form is subject to the terms of the Mozilla Public
 * License, v. 2.0. If a copy of the MPL was not distributed with this
 * file, You can obtain one at http://mozilla.org/MPL/2.0/.
 */

package com.evilbird.warcraft.behaviour.ai.wander;

import com.badlogic.gdx.math.Vector2;
import com.evilbird.engine.action.Action;
import com.evilbird.engine.action.ActionFactory;
import com.evilbird.engine.object.GameObject;
import com.evilbird.warcraft.action.move.MoveToVectorAction;
import com.evilbird.warcraft.behaviour.ai.common.task.AsyncActionTask;

import javax.inject.Inject;

import static com.evilbird.warcraft.action.move.MoveActions.MoveToLocation;

/**
 * An {@link AsyncActionTask} that assigns a move to location action to the
 * subject contained in the tasks blackboard.
 *
 * @author Blair Butterworth
 */
public class WanderTask extends AsyncActionTask<WanderData>
{
    @Inject
    public WanderTask(ActionFactory actionFactory) {
        super(actionFactory);
    }

    @Override
    protected Action getAction(ActionFactory factory) {
        WanderData data = getObject();
        GameObject subject = data.getSubject();
        Vector2 destination = data.getDestination();

        MoveToVectorAction moveAction = (MoveToVectorAction)factory.get(MoveToLocation);
        moveAction.setSubject(subject);
        moveAction.setDestination(destination);

        subject.removeActions();
        subject.addAction(moveAction);

        return moveAction;
    }
}
