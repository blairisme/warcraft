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
import com.evilbird.engine.action.framework.Action;
import com.evilbird.engine.action.framework.BasicAction;
import com.evilbird.engine.common.lang.Identifier;
import com.evilbird.engine.common.lang.NamedIdentifier;
import com.evilbird.engine.item.*;

import java.util.Arrays;
import java.util.Collection;

/**
 * Instances of this {@link Action} create a new {@link Item} with the given
 * attributes.
 *
 * @author Blair Butterworth
 */
public class CreateAction extends BasicAction
{
    private ItemType type;
    private ItemFactory factory;
    private Vector2 position;
    private Collection<Action> dependents;

    public CreateAction(
        ItemComposite parent,
        ItemType type,
        ItemFactory factory,
        Identifier id,
        Vector2 position,
        boolean selected)
    {
    }

    public CreateAction(ItemFactory factory, ItemType type, Action ... dependents) {
        this.factory = factory;
        this.type = type;
        this.dependents = Arrays.asList(dependents);
    }

    public void setPosition(Vector2 position) {
        this.position = position;
    }

    @Override
    public boolean act(float delta) {
        Item item = factory.newItem(type);
        item.setPosition(position);

        ItemComposite parent = getParent();
        parent.addItem(item);

        dependents.forEach((action) -> action.setItem(item));
        return true;
    }

    private ItemComposite getParent() {
        Item item = getItem();
        if (item instanceof ItemComposite) {
            return (ItemComposite)item;
        }
        return item.getParent();
    }
}
