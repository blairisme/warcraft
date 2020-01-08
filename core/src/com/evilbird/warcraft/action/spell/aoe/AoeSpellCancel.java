/*
 * Copyright (c) 2020, Blair Butterworth
 *
 * This Source Code Form is subject to the terms of the Mozilla Public
 * License, v. 2.0. If a copy of the MPL was not distributed with this
 * file, You can obtain one at http://mozilla.org/MPL/2.0/.
 */

package com.evilbird.warcraft.action.spell.aoe;

import com.evilbird.engine.action.framework.BasicAction;
import com.evilbird.engine.object.GameObject;
import com.evilbird.engine.object.GameObjectGroup;

import javax.inject.Inject;

import static com.evilbird.engine.action.ActionConstants.ActionComplete;

/**
 * An action that removes a conjured area of effect object.
 *
 * @author Blair Butterworth
 */
public class AoeSpellCancel extends BasicAction
{
    @Inject
    public AoeSpellCancel() {
    }

    @Override
    public boolean act(float delta) {
        GameObject aoe = getTarget();
        GameObjectGroup parent = aoe.getParent();
        parent.removeObject(aoe);
        return ActionComplete;
    }
}
