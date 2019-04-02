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
import com.evilbird.engine.common.serialization.SerializedConstructor;
import com.evilbird.engine.item.Item;
import com.evilbird.engine.item.ItemComposite;
import com.evilbird.engine.item.ItemFactory;
import com.evilbird.engine.item.ItemType;
import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;

/**
 * Instances of this {@link Action} create a new {@link Item} with the given
 * attributes.
 *
 * @author Blair Butterworth
 */
public class CreateAction extends BasicAction
{
    private ItemType type;
    private Vector2 position;
    private Collection<Action> dependents;
    private transient ItemFactory factory;

    @SerializedConstructor
    private CreateAction() {
    }

    public CreateAction(ItemFactory factory, Action ... dependents) {
        this(factory, null, dependents);
    }

    public CreateAction(ItemFactory factory, ItemType type, Action ... dependents) {
        this.factory = factory;
        this.type = type;
        this.dependents = new ArrayList<>();
        Collections.addAll(this.dependents, dependents);
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
        setPosition(item);
        setParent(item);
        setDependencies(item);
        return true;
    }

    private void setPosition(Item item) {
        if (position != null) {
            item.setPosition(position);
        }
    }

    private void setParent(Item item) {
        ItemComposite parent = getParent();
        parent.addItem(item);
    }

    private ItemComposite getParent() {
        Item item = getItem();
        if (item instanceof ItemComposite) {
            return (ItemComposite)item;
        }
        return item.getParent();
    }

    private void setDependencies(Item item) {
        dependents.forEach((action) -> action.setTarget(action.getItem()));
        dependents.forEach((action) -> action.setItem(item));
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == this) return true;
        if (obj == null) return false;
        if (obj.getClass() != getClass()) return false;

        CreateAction that = (CreateAction)obj;
        return new EqualsBuilder()
            .appendSuper(super.equals(obj))
            .append(type, that.type)
            .append(position, that.position)
            .append(dependents, that.dependents)
            .isEquals();
    }

    @Override
    public int hashCode() {
        return new HashCodeBuilder(17, 37)
            .appendSuper(super.hashCode())
            .append(type)
            .append(position)
            .append(dependents)
            .toHashCode();
    }
}
