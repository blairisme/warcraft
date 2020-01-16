/*
 * Copyright (c) 2020, Blair Butterworth
 *
 * This Source Code Form is subject to the terms of the Mozilla Public
 * License, v. 2.0. If a copy of the MPL was not distributed with this
 * file, You can obtain one at http://mozilla.org/MPL/2.0/.
 */

package com.evilbird.warcraft.behaviour.ainew.common;

import com.badlogic.gdx.ai.btree.LeafTask;
import com.badlogic.gdx.ai.btree.Task;
import com.evilbird.engine.action.Action;
import com.evilbird.engine.action.ActionFactory;
import com.evilbird.engine.action.ActionIdentifier;
import com.evilbird.engine.object.GameObject;
import com.evilbird.engine.state.State;

import javax.inject.Inject;

/**
 * @author Blair Butterworth
 */
public class ActionTask extends LeafTask<State>
{
    private ActionFactory factory;
    private ActionIdentifier identifier;
    private GameObject recipient;
    private GameObject subject;
    private GameObject target;

    @Inject
    public ActionTask(ActionFactory factory) {
        this.factory = factory;
    }

    public void setAction(ActionIdentifier identifier) {
        this.identifier = identifier;
    }

    @Override
    public Status execute() {
        Action action = factory.get(identifier);


        return Status.SUCCEEDED;
    }

    @Override
    protected Task<State> copyTo(Task<State> task) {
        return null;
    }
}
