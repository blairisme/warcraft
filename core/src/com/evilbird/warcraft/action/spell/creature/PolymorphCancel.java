/*
 * Copyright (c) 2019, Blair Butterworth
 *
 * This work is licensed under the MIT License. To view a copy of this
 * license, visit
 *
 *        https://opensource.org/licenses/MIT
 */

package com.evilbird.warcraft.action.spell.creature;

import com.evilbird.warcraft.action.common.exclusion.ItemExclusion;
import com.evilbird.warcraft.action.common.remove.RemoveEvents;
import com.evilbird.warcraft.object.unit.Unit;

import javax.inject.Inject;

/**
 * An action that restores a polymorphed game object to default state.
 *
 * @author Blair Butterworth
 */
public class PolymorphCancel extends CreatureSpellCancel
{
    private ItemExclusion exclusion;

    @Inject
    public PolymorphCancel(RemoveEvents removeEvents, ItemExclusion exclusion) {
        super(removeEvents);
        this.exclusion = exclusion;
    }

    @Override
    public boolean act(float delta) {
        Unit creature = (Unit)getTarget();
        Unit target = (Unit)creature.getAssociatedItem();

        target.setPosition(creature.getPosition());
        exclusion.restore(target);

        return super.act(delta);
    }
}
