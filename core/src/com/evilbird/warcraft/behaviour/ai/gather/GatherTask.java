/*
 * Copyright (c) 2020, Blair Butterworth
 *
 * This Source Code Form is subject to the terms of the Mozilla Public
 * License, v. 2.0. If a copy of the MPL was not distributed with this
 * file, You can obtain one at http://mozilla.org/MPL/2.0/.
 */

package com.evilbird.warcraft.behaviour.ai.gather;

import com.evilbird.engine.action.Action;
import com.evilbird.engine.action.ActionFactory;
import com.evilbird.engine.behaviour.framework.task.AsyncActionTask;
import com.evilbird.engine.object.GameObject;
import com.evilbird.warcraft.action.gather.GatherActions;
import com.evilbird.warcraft.data.resource.ResourceType;

import javax.inject.Inject;

/**
 * An action task that assigns a gathering action to the subject contained in
 * the tasks blackboard.
 *
 * @author Blair Butterworth
 */
public class GatherTask extends AsyncActionTask<GatherData>
{
    @Inject
    public GatherTask(ActionFactory actionFactory) {
        super(actionFactory);
    }
    
    @Override
    protected Action getAction(ActionFactory factory) {
        GatherData data = getObject();
        ResourceType resource = data.getResource();

        GatherActions identifier = GatherActions.forResource(resource);
        Action action = factory.get(identifier);

        GameObject gatherer = data.getGatherer();
        GameObject location = data.getLocation();

        action.setSubject(gatherer);
        action.setTarget(location);

        gatherer.removeActions();
        gatherer.addAction(action);

        return action;
    }
}
