/*
 * Blair Butterworth (c) 2019
 *
 * This work is licensed under the MIT License. To view a copy of this
 * license, visit
 *
 *      https://opensource.org/licenses/MIT
 */

package com.evilbird.warcraft.action.hud;

import com.badlogic.gdx.math.Vector2;
import com.evilbird.engine.action.ActionContext;
import com.evilbird.engine.action.ActionIdentifier;
import com.evilbird.engine.action.common.CreateAction;
import com.evilbird.engine.action.framework.Action;
import com.evilbird.engine.common.lang.NamedIdentifier;
import com.evilbird.engine.item.Item;
import com.evilbird.engine.item.ItemFactory;
import com.evilbird.engine.item.ItemOperations;
import com.evilbird.warcraft.action.ActionProvider;

import javax.inject.Inject;

/**
 * Instances of this class provide {@link Action Actions} that add a building
 * placeholder.
 *
 * @author Blair Butterworth
 */
public class Placeholder implements ActionProvider
{
    private ItemFactory itemFactory;

    @Inject
    public Placeholder(ItemFactory itemFactory)
    {
        this.itemFactory = itemFactory;
    }

    @Override
    public Action get(ActionIdentifier action, ActionContext context)
    {
        Item item = context.getItem();

        PlaceholderActions placeholder = (PlaceholderActions)action;
        NamedIdentifier identifier = new NamedIdentifier();

        Vector2 location = ItemOperations.getScreenCenter(item.getRoot());
        return new CreateAction(item.getParent(), placeholder.getPlaceholderType(), itemFactory, identifier, location, true);
    }
}
