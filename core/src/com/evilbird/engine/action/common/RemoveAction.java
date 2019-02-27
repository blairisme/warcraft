/*
 * Blair Butterworth (c) 2019
 *
 * This work is licensed under the MIT License. To view a copy of this
 * license, visit
 *
 *      https://opensource.org/licenses/MIT
 */

package com.evilbird.engine.action.common;

import com.evilbird.engine.action.Action;
import com.evilbird.engine.action.framework.BasicAction;
import com.evilbird.engine.item.Item;
import com.evilbird.engine.item.ItemGroup;
import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;

import javax.inject.Inject;

/**
 * Instances of this {@link Action} remove a given {@link Item} when invoked.
 *
 * @author Blair Butterworth
 */
public class RemoveAction extends BasicAction
{
    private ActionTarget source;

    @Inject
    public RemoveAction() {
        this(ActionTarget.Item);
    }

    public RemoveAction(ActionTarget source) {
        this.source = source;
    }

    public void setSource(ActionTarget source) {
        this.source = source;
    }

    @Override
    public boolean act(float delta) {
        Item item = getRemoveItem();
        ItemGroup parent = item.getParent();
        parent.removeItem(item);
        return true;
    }

    private Item getRemoveItem() {
        switch (source) {
            case Item: return getItem();
            case Target: return getTarget();
            default: throw new UnsupportedOperationException();
        }
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == this) return true;
        if (obj == null) return false;
        if (obj.getClass() != getClass()) return false;

        RemoveAction that = (RemoveAction)obj;
        return new EqualsBuilder()
            .appendSuper(super.equals(obj))
            .append(source, that.source)
            .isEquals();
    }

    @Override
    public int hashCode() {
        return new HashCodeBuilder(17, 37)
            .appendSuper(super.hashCode())
            .append(source)
            .toHashCode();
    }
}
