/*
 * Blair Butterworth (c) 2019
 *
 * This work is licensed under the MIT License. To view a copy of this
 * license, visit
 *
 *      https://opensource.org/licenses/MIT
 */

package com.evilbird.engine.action.common;

import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.scenes.scene2d.Action;
import com.evilbird.engine.action.framework.BasicAction;
import com.evilbird.engine.common.lang.Identifier;
import com.evilbird.engine.item.Item;
import com.evilbird.engine.item.ItemComposite;
import com.evilbird.engine.item.ItemFactory;
import com.evilbird.engine.item.ItemType;

/**
 * Instances of this {@link Action} create a new {@link Item} with the given
 * attributes.
 *
 * @author Blair Butterworth
 */
public class CreateAction extends BasicAction
{
    private ItemComposite parent;
    private Identifier id;
    private ItemType type;
    private ItemFactory factory;
    private Vector2 position;
    private boolean selected;

    public CreateAction(
        ItemComposite parent,
        ItemType type,
        ItemFactory factory,
        Identifier id,
        Vector2 position,
        boolean selected)
    {
        this.parent = parent;
        this.id = id;
        this.type = type;
        this.factory = factory;
        this.position = position;
        this.selected = selected;
    }

    @Override
    public boolean act(float delta) {
        Item item = factory.newItem(type);
        item.setId(id);
        item.setPosition(position);
        item.setSelected(selected);
        parent.addItem(item);
        return true;
    }

    @Override public void restart()
    {
    }
}
