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
import com.evilbird.engine.action.Action;
import com.evilbird.engine.action.framework.BasicAction;
import com.evilbird.engine.item.Item;
import com.evilbird.engine.item.ItemComposite;
import com.evilbird.engine.item.ItemFactory;
import com.evilbird.engine.item.ItemType;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;

/**
 * Instances of this {@link Action} create a new {@link Item} with the given
 * attributes.
 *
 * @author Blair Butterworth
 */
//TODO: tidy
public class CreateAction extends BasicAction
{
    private ItemType type;
    private ItemFactory factory;
    private Vector2 position;
    private Collection<Action> dependents;

    public CreateAction(ItemFactory factory, Action ... dependents) {
        this(factory, null, dependents);
    }

    public CreateAction(ItemFactory factory, ItemType type, Action ... dependents) {
        this.factory = factory;
        this.type = type;
        this.dependents = new ArrayList<>();
        this.dependents.addAll(Arrays.asList(dependents));
    }

    public ItemType getType() {
        return type;
    }

    public Vector2 getPosition() {
        return position;
    }

    public void setType(ItemType type) {
        this.type = type;
    }

    public void setPosition(Vector2 position) {
        this.position = position;
    }

    public void addDependency(Action dependency) {
        dependents.add(dependency);
    }

    @Override
    public boolean act(float delta) {
        Item item = factory.newItem(type);

        if (position != null) {
            item.setPosition(position);
        }

        ItemComposite parent = getParent();
        parent.addItem(item);

        dependents.forEach((action) -> action.setTarget(action.getItem()));
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
