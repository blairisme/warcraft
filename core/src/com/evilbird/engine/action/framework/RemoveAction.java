/*
 * Copyright (c) 2019, Blair Butterworth
 *
 * This work is licensed under the MIT License. To view a copy of this
 * license, visit
 *
 *        https://opensource.org/licenses/MIT
 */

package com.evilbird.engine.action.framework;

import com.evilbird.engine.action.Action;
import com.evilbird.engine.object.GameObject;
import com.evilbird.engine.object.GameObjectGroup;

import javax.inject.Inject;

import static com.evilbird.engine.action.ActionConstants.ActionComplete;

/**
 * An {@link Action} that removes its subject from its parent.
 *
 * @author Blair Butterworth
 */
public class RemoveAction extends BasicAction
{
    @Inject
    public RemoveAction() {
    }

    @Override
    public boolean act(float delta) {
        GameObject subject = getSubject();
        GameObjectGroup parent = subject.getParent();
        parent.removeObject(subject);
        return ActionComplete;
    }
}
