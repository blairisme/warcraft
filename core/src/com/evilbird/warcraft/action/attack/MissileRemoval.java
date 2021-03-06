/*
 * Copyright (c) 2020, Blair Butterworth
 *
 * This Source Code Form is subject to the terms of the Mozilla Public
 * License, v. 2.0. If a copy of the MPL was not distributed with this
 * file, You can obtain one at http://mozilla.org/MPL/2.0/.
 */

package com.evilbird.warcraft.action.attack;

import com.evilbird.engine.action.Action;
import com.evilbird.engine.action.ActionResult;
import com.evilbird.engine.action.framework.BasicAction;
import com.evilbird.engine.object.GameObject;
import com.evilbird.engine.object.GameObjectGroup;

import javax.inject.Inject;

/**
 * An {@link Action} that removes its subject from its parent.
 *
 * @author Blair Butterworth
 */
public class MissileRemoval extends BasicAction
{
    @Inject
    public MissileRemoval() {
    }

    @Override
    public ActionResult act(float delta) {
        GameObject subject = getSubject();
        GameObjectGroup parent = subject.getParent();
        parent.removeObject(subject);
        return ActionResult.Complete;
    }
}
