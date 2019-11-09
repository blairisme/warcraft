/*
 * Copyright (c) 2019, Blair Butterworth
 *
 * This work is licensed under the MIT License. To view a copy of this
 * license, visit
 *
 *        https://opensource.org/licenses/MIT
 */

package com.evilbird.warcraft.action.selector;

import com.badlogic.gdx.math.Vector2;
import com.evilbird.engine.action.Action;
import com.evilbird.engine.action.framework.BasicAction;
import com.evilbird.engine.item.Item;
import com.evilbird.engine.item.ItemFactory;
import com.evilbird.warcraft.item.data.player.Player;
import com.evilbird.warcraft.item.selector.SelectorType;
import com.evilbird.warcraft.item.unit.Unit;

import javax.inject.Inject;

import static com.evilbird.engine.action.ActionConstants.ActionComplete;
import static com.evilbird.engine.item.utility.ItemOperations.getScreenCenter;
import static com.evilbird.warcraft.item.WarcraftItemConstants.TILE_HEIGHT;
import static com.evilbird.warcraft.item.WarcraftItemConstants.TILE_WIDTH;
import static com.evilbird.warcraft.item.common.query.UnitOperations.getPlayer;

/**
 * An {@link Action} that adds a selector to the game world.
 *
 * @author Blair Butterworth
 */
public class SelectorCreate extends BasicAction
{
    private ItemFactory factory;
    private SelectorEvents events;

    @Inject
    public SelectorCreate(SelectorEvents events, ItemFactory factory) {
        this.events = events;
        this.factory = factory;
    }

    @Override
    public boolean act(float time) {
        Unit subject = (Unit)getItem();
        Player player = getPlayer(subject);

        Item selector = factory.get(selectorType());
        selector.setPosition(selectorPosition(subject));
        player.addItem(selector);

        subject.setAssociatedItem(selector);
        events.notifySelectorAdded(subject, selector);

        return ActionComplete;
    }

    private SelectorType selectorType() {
        SelectorActions action = (SelectorActions)getIdentifier();
        return action.getSelector();
    }

    private Vector2 selectorPosition(Item builder) {
        Vector2 screenCenter = getScreenCenter(builder);
        screenCenter.x = Math.round(screenCenter.x/TILE_WIDTH) * TILE_WIDTH;
        screenCenter.y = Math.round(screenCenter.y/TILE_HEIGHT) * TILE_HEIGHT;
        return screenCenter;
    }
}
