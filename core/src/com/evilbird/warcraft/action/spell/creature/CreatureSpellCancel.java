/*
 * Copyright (c) 2020, Blair Butterworth
 *
 * This Source Code Form is subject to the terms of the Mozilla Public
 * License, v. 2.0. If a copy of the MPL was not distributed with this
 * file, You can obtain one at http://mozilla.org/MPL/2.0/.
 */

package com.evilbird.warcraft.action.spell.creature;

import com.evilbird.engine.action.framework.BasicAction;
import com.evilbird.engine.object.GameObject;
import com.evilbird.engine.object.GameObjectGroup;
import com.evilbird.warcraft.action.death.RemoveEvents;

import javax.inject.Inject;

import static com.evilbird.engine.action.ActionConstants.ActionComplete;

/**
 * An action that removes a conjured creature, when invoked.
 *
 * @author Blair Butterworth
 */
public class CreatureSpellCancel extends BasicAction
{
    private RemoveEvents removeEvents;

    @Inject
    public CreatureSpellCancel(RemoveEvents removeEvents) {
        this.removeEvents = removeEvents;
    }

    @Override
    public boolean act(float delta) {
        GameObject creature = getTarget();
        GameObjectGroup parent = creature.getParent();
        parent.removeObject(creature);
        removeEvents.objectRemoved(creature);
        return ActionComplete;
    }
}
