/*
 * Copyright (c) 2019, Blair Butterworth
 *
 * This work is licensed under the MIT License. To view a copy of this
 * license, visit
 *
 *        https://opensource.org/licenses/MIT
 */

package com.evilbird.warcraft.action.spell.creature;

import com.evilbird.engine.action.framework.BasicAction;
import com.evilbird.engine.item.Item;
import com.evilbird.engine.item.ItemGroup;
import com.evilbird.warcraft.action.common.remove.RemoveEvents;

import static com.evilbird.engine.action.ActionConstants.ActionComplete;

/**
 * An action that removes a conjured creature, when invoked.
 *
 * @author Blair Butterworth
 */
public class CreatureSpellCancel extends BasicAction
{
    private RemoveEvents removeEvents;

    public CreatureSpellCancel(RemoveEvents removeEvents) {
        this.removeEvents = removeEvents;
    }

    @Override
    public boolean act(float delta) {
        Item creature = getTarget();
        ItemGroup parent = creature.getParent();
        parent.removeItem(creature);
        removeEvents.notifyRemove(creature);
        return ActionComplete;
    }
}
