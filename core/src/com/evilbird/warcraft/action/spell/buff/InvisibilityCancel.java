/*
 * Copyright (c) 2020, Blair Butterworth
 *
 * This Source Code Form is subject to the terms of the Mozilla Public
 * License, v. 2.0. If a copy of the MPL was not distributed with this
 * file, You can obtain one at http://mozilla.org/MPL/2.0/.
 */

package com.evilbird.warcraft.action.spell.buff;

import com.evilbird.engine.action.framework.BasicAction;
import com.evilbird.engine.object.GameObject;
import com.evilbird.engine.object.GameObjectGroup;
import com.evilbird.warcraft.object.unit.combatant.Combatant;

import javax.inject.Inject;

import static com.evilbird.engine.action.ActionConstants.ActionComplete;

/**
 * An action that removes the effects of the invisibility spell.
 *
 * @author Blair Butterworth
 */
public class InvisibilityCancel extends BasicAction
{
    @Inject
    public InvisibilityCancel() {
    }

    @Override
    public boolean act(float delta) {
        Combatant target = (Combatant)getTarget();
        GameObject effect = target.getEffect();
        GameObjectGroup parent = effect.getParent();

        parent.removeObject(effect);
        target.setEffect(null);
        target.setAttackable(true);

        return ActionComplete;
    }
}
