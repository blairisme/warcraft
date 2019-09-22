/*
 * Copyright (c) 2019, Blair Butterworth
 *
 * This work is licensed under the MIT License. To view a copy of this
 * license, visit
 *
 *        https://opensource.org/licenses/MIT
 */

package com.evilbird.warcraft.action.placeholder;

import com.evilbird.engine.action.framework.BasicAction;
import com.evilbird.engine.item.Item;
import com.evilbird.engine.item.ItemGroup;
import com.evilbird.warcraft.item.unit.Unit;

import javax.inject.Inject;

import static com.evilbird.engine.action.ActionConstants.ActionComplete;

/**
 * Instances of this class stop the use of a placeholder, removing it from the
 * game.
 *
 * @author Blair Butterworth
 */
public class PlaceholderCancel extends BasicAction
{
    private PlaceholderEvents events;

    @Inject
    public PlaceholderCancel(PlaceholderEvents events) {
        this.events = events;
        setIdentifier(PlaceholderActions.PlaceholderCancel);
    }

    @Override
    public boolean act(float time) {
        Unit builder = (Unit)getItem();
        Item building = builder.getAssociatedItem();

        ItemGroup player = building.getParent();
        player.removeItem(building);

        builder.setAssociatedItem(null);
        events.notifyPlaceholderRemoved(builder, building);

        return ActionComplete;
    }
}
