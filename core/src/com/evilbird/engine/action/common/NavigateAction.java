/*
 * Blair Butterworth (c) 2019
 *
 * This work is licensed under the MIT License. To view a copy of this
 * license, visit
 *
 *      https://opensource.org/licenses/MIT
 */

package com.evilbird.engine.action.common;

import com.evilbird.engine.action.framework.BasicAction;
import com.evilbird.engine.common.lang.GenericIdentifier;
import com.evilbird.engine.common.lang.Identifier;
import com.evilbird.engine.common.lang.Navigable;
import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;

/**
 * Instances of this class aid navigation through user interface menus.
 *
 * @author Blair Butterworth
 */
public class NavigateAction extends BasicAction
{
    private Identifier location;
    private ActionTarget source;

    public NavigateAction() {
        this(GenericIdentifier.Unknown, ActionTarget.Item);
    }

    public NavigateAction(Identifier location, ActionTarget source) {
        this.location = location;
        this.source = source;
    }

    public void setSource(ActionTarget source) {
        this.source = source;
    }

    public void setLocation(Identifier location) {
        this.location = location;
    }

    @Override
    public boolean act(float delta) {
        Navigable navigable = getNavigable();
        navigable.navigate(location);
        return true;
    }

    private Navigable getNavigable() {
        switch (source) {
            case Item: return (Navigable)getItem();
            case Target: return (Navigable)getTarget();
            case Parent: return (Navigable)getItem().getParent();
            default: throw new UnsupportedOperationException();
        }
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == this) return true;
        if (obj == null) return false;
        if (obj.getClass() != getClass()) return false;

        NavigateAction that = (NavigateAction)obj;
        return new EqualsBuilder()
            .appendSuper(super.equals(obj))
            .append(location, that.location)
            .append(source, that.source)
            .isEquals();
    }

    @Override
    public int hashCode() {
        return new HashCodeBuilder(17, 37)
            .appendSuper(super.hashCode())
            .append(location)
            .append(source)
            .toHashCode();
    }
}