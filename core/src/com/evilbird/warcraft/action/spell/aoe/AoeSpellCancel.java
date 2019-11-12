/*
 * Copyright (c) 2019, Blair Butterworth
 *
 * This work is licensed under the MIT License. To view a copy of this
 * license, visit
 *
 *        https://opensource.org/licenses/MIT
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
