/*
 * Copyright (c) 2019, Blair Butterworth
 *
 * This work is licensed under the MIT License. To view a copy of this
 * license, visit
 *
 *        https://opensource.org/licenses/MIT
 */

package com.evilbird.warcraft.action.placeholder;

import com.badlogic.gdx.math.Vector2;
import com.evilbird.engine.action.Action;
import com.evilbird.engine.events.Events;
import com.evilbird.engine.item.Item;
import com.evilbird.engine.item.ItemFactory;
import com.evilbird.engine.item.ItemType;
import com.evilbird.warcraft.action.common.create.CreateAction;
import com.evilbird.warcraft.item.unit.Unit;

import javax.inject.Inject;

import static com.evilbird.engine.action.ActionConstants.ActionComplete;
import static com.evilbird.engine.action.ActionConstants.ActionIncomplete;
import static com.evilbird.engine.item.utility.ItemOperations.getScreenCenter;
import static com.evilbird.warcraft.action.placeholder.PlaceholderEvents.notifyPlaceholderAdded;
import static com.evilbird.warcraft.item.WarcraftItemConstants.TILE_HEIGHT;
import static com.evilbird.warcraft.item.WarcraftItemConstants.TILE_WIDTH;

/**
 * Instances of this class provide {@link Action Actions} that add a building
 * placeholder.
 *
 * @author Blair Butterworth
 */
public class PlaceholderCreate extends CreateAction
{
    @Inject
    public PlaceholderCreate(Events events, ItemFactory factory) {
        super(events, factory);
        type = this::getPlaceholderType;
        properties = this::setPlaceholderPosition;
        recipient = this::setTarget;
    }

    @Override
    public boolean act(float time) {
        if (super.act(time)) {
            return complete();
        }
        return ActionIncomplete;
    }

    private boolean complete() {
        Unit builder = (Unit)getItem();
        Item placeholder = getTarget();

        builder.setAssociatedItem(placeholder);
        notifyPlaceholderAdded(events, builder, placeholder);

        return ActionComplete;
    }

    private ItemType getPlaceholderType() {
        PlaceholderActions placeholderAction = (PlaceholderActions)getIdentifier();
        return placeholderAction.getPlaceholder();
    }

    private void setPlaceholderPosition(Item item) {
        Vector2 screenCenter = getScreenCenter(getItem());
        screenCenter.x = Math.round(screenCenter.x/TILE_WIDTH) * TILE_WIDTH;
        screenCenter.y = Math.round(screenCenter.y/TILE_HEIGHT) * TILE_HEIGHT;
        item.setPosition(screenCenter);
    }
}
