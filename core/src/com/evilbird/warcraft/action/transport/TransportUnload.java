/*
 * Copyright (c) 2019, Blair Butterworth
 *
 * This work is licensed under the MIT License. To view a copy of this
 * license, visit
 *
 *        https://opensource.org/licenses/MIT
 */

package com.evilbird.warcraft.action.transport;

import com.evilbird.engine.action.Action;
import com.evilbird.engine.action.framework.BasicAction;
import com.evilbird.engine.item.Item;
import com.evilbird.warcraft.action.common.exclusion.ItemExclusion;
import com.evilbird.warcraft.action.move.MoveAdjacent;
import com.evilbird.warcraft.item.common.capability.MovableObject;
import com.evilbird.warcraft.item.unit.Unit;

import javax.inject.Inject;

import static com.evilbird.engine.action.ActionConstants.ActionComplete;

/**
 * Represents an {@link Action} that moves a disembarkee from a vessel.
 *
 * @author Blair Butterworth
 */
public class TransportUnload extends BasicAction
{
    private transient ItemExclusion exclusion;
    private transient MoveAdjacent movement;

    @Inject
    public TransportUnload(ItemExclusion exclusion, MoveAdjacent movement) {
        this.exclusion = exclusion;
        this.movement = movement;
    }

    @Override
    public boolean act(float delta) {
        Unit vessel = (Unit)getItem();
        for (Item associate: vessel.getAssociatedObjects()) {
            Unit unit = (Unit)associate;
            if (movement.reposition((MovableObject)unit, vessel)) {
                vessel.removeAssociatedItem(unit);
                exclusion.restore(unit);
            }
        }
        if (vessel.hasAssociatedItems()) {
            setError(new TransportFailed("Unable to offload all units"));
        }
        return ActionComplete;
    }
}
