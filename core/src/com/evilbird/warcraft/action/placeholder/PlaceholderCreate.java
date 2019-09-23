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
import com.evilbird.engine.action.framework.BasicAction;
import com.evilbird.engine.item.Item;
import com.evilbird.engine.item.ItemFactory;
import com.evilbird.engine.item.ItemType;
import com.evilbird.warcraft.item.data.player.Player;
import com.evilbird.warcraft.item.unit.Unit;

import javax.inject.Inject;

import static com.evilbird.engine.action.ActionConstants.ActionComplete;
import static com.evilbird.engine.item.utility.ItemOperations.getScreenCenter;
import static com.evilbird.warcraft.item.WarcraftItemConstants.TILE_HEIGHT;
import static com.evilbird.warcraft.item.WarcraftItemConstants.TILE_WIDTH;
import static com.evilbird.warcraft.item.common.query.UnitOperations.getPlayer;

/**
 * Instances of this class provide {@link Action Actions} that add a building
 * placeholder.
 *
 * @author Blair Butterworth
 */
public class PlaceholderCreate extends BasicAction
{
    private ItemFactory factory;
    private PlaceholderEvents events;

    @Inject
    public PlaceholderCreate(PlaceholderEvents events, ItemFactory factory) {
        this.events = events;
        this.factory = factory;
    }

    @Override
    public boolean act(float time) {
        Unit builder = (Unit)getItem();
        Player player = getPlayer(builder);

        Item placeholder = factory.get(getPlaceholderType());
        placeholder.setPosition(getPlaceholderPosition(builder));
        player.addItem(placeholder);

        builder.setAssociatedItem(placeholder);
        events.notifyPlaceholderAdded(builder, placeholder);

        return ActionComplete;
    }

    private ItemType getPlaceholderType() {
        PlaceholderActions placeholderAction = (PlaceholderActions)getIdentifier();
        return placeholderAction.getPlaceholder();
    }

    private Vector2 getPlaceholderPosition(Item builder) {
        Vector2 screenCenter = getScreenCenter(builder);
        screenCenter.x = Math.round(screenCenter.x/TILE_WIDTH) * TILE_WIDTH;
        screenCenter.y = Math.round(screenCenter.y/TILE_HEIGHT) * TILE_HEIGHT;
        return screenCenter;
    }
}
