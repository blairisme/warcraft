/*
 * Blair Butterworth (c) 2019
 *
 * This work is licensed under the MIT License. To view a copy of this
 * license, visit
 *
 *      https://opensource.org/licenses/MIT
 */

package com.evilbird.engine.action.framework;

import com.evilbird.engine.action.Action;
import com.evilbird.engine.action.ActionException;
import com.evilbird.engine.common.lang.GenericIdentifier;
import com.evilbird.engine.common.lang.Identifier;
import com.evilbird.engine.device.UserInput;
import com.evilbird.engine.item.Item;
import com.evilbird.engine.item.ItemComposite;
import com.evilbird.engine.item.ItemReference;
import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;
import org.apache.commons.lang3.builder.ToStringBuilder;

/**
 * Instances of this class represent a base class for {@link Action Actions},
 * containing common methods and properties utilised by many custom actions.
 *
 * @author Blair Butterworth
 */
public abstract class BasicAction implements Action
{
    private Identifier identifier;
    private ActionException error;
    private UserInput cause;
    private ItemReference item;
    private ItemReference target;

    public BasicAction() {
    }

    @Override
    public void cancel() {
        Item item = getItem();
        if (item != null) {
            item.clearActions();
        }
    }

    public void reset() {
        error = null;
    }

    @Override
    public void restart() {
        error = null;
    }

    @Override
    public Item getItem() {
        return item != null ? item.get() : null;
    }

    public ItemReference getItemReference() {
        return item;
    }

    @Override
    public Item getTarget() {
        return target != null ? target.get() : null;
    }

    public ItemReference getTargetReference() {
        return target;
    }

    @Override
    public UserInput getCause() {
        return cause;
    }

    @Override
    public ActionException getError() {
        return error;
    }

    @Override
    public Identifier getIdentifier() {
        if (identifier == null) {
            return GenericIdentifier.Unknown;
        }
        return identifier;
    }

    @Override
    public boolean hasError() {
        return getError() != null;
    }

    @Override
    public void setCause(UserInput cause) {
        this.cause = cause;
    }

    @Override
    public void setItem(Item item) {
        this.item = item != null ? new ItemReference(item) : null;
    }

    public void setItemReference(ItemReference reference) {
        this.item = reference;
    }

    @Override
    public void setTarget(Item target) {
        this.target = target != null ? new ItemReference(target) : null;
    }

    public void setTargetReference(ItemReference reference) {
        this.target = reference;
    }

    @Override
    public void setRoot(ItemComposite root) {
        if (item != null) {
            item.setParent(root);
        }
        if (target != null) {
            target.setParent(root);
        }
    }

    @Override
    public void setError(ActionException error) {
        this.error = error;
    }

    public void setIdentifier(Identifier identifier) {
        this.identifier = identifier;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this)
            .append("identifier", identifier)
            .append("error", error)
            .append("cause", cause)
            .append("item", item)
            .append("target", target)
            .toString();
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == null) { return false; }
        if (obj == this) { return true; }
        if (obj.getClass() != getClass()) { return false; }

        BasicAction that = (BasicAction)obj;
        return new EqualsBuilder()
            .append(identifier, that.identifier)
            .append(error, that.error)
            .append(cause, that.cause)
            .append(item, that.item)
            .append(target, that.target)
            .isEquals();
    }

    @Override
    public int hashCode() {
        return new HashCodeBuilder(17, 37)
            .append(identifier)
            .append(error)
            .append(cause)
            .append(item)
            .append(target)
            .toHashCode();
    }
}
