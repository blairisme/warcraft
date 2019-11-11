/*
 * Copyright (c) 2019, Blair Butterworth
 *
 * This work is licensed under the MIT License. To view a copy of this
 * license, visit
 *
 *        https://opensource.org/licenses/MIT
 */

package com.evilbird.warcraft.action.spell.attack;

import com.evilbird.engine.action.framework.BasicAction;
import com.evilbird.engine.object.GameObject;
import com.evilbird.engine.object.GameObjectGroup;

import javax.inject.Inject;

import static com.evilbird.engine.action.ActionConstants.ActionComplete;

/**
 * An action that removes the effects of the flame shield spell.
 *
 * @author Blair Butterworth
 */
public class FlameShieldCancel extends BasicAction
{
    @Inject
    public FlameShieldCancel() {
    }

    @Override
    public boolean act(float delta) {
        GameObject flameShield = getTarget();
        GameObjectGroup parent = flameShield.getParent();
        parent.removeObject(flameShield);
        return ActionComplete;
    }
}
