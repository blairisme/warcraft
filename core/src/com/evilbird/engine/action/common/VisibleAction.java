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
import com.evilbird.engine.common.lang.Visible;
import com.evilbird.engine.item.Item;
import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;

import javax.inject.Inject;

/**
 * Instances of this {@link BasicAction Action} modify an {@link Item Items}
 * visibility: whether its rendered or not.
 *
 * @author Blair Butterworth
 */
public class VisibleAction extends BasicAction
{
    private boolean enabled;
    private ActionTarget source;

    @Inject
    public VisibleAction() {
        this(true);
    }

    public VisibleAction(boolean enabled) {
        this(ActionTarget.Item, enabled);
    }

    public VisibleAction(ActionTarget target, boolean enabled) {
        this.enabled = enabled;
        this.source = target;
    }

    public static VisibleAction show() {
        return show(ActionTarget.Item);
    }

    public static VisibleAction show(ActionTarget target) {
        return new VisibleAction(target, true);
    }

    public static VisibleAction hide() {
        return hide(ActionTarget.Item);
    }

    public static VisibleAction hide(ActionTarget target) {
        return new VisibleAction(target, false);
    }

    @Override
    public boolean act(float time) {
        Visible visible = getVisible();
        visible.setVisible(enabled);
        return true;
    }

    private Visible getVisible() {
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

        VisibleAction that = (VisibleAction)obj;
        return new EqualsBuilder()
            .appendSuper(super.equals(obj))
            .append(enabled, that.enabled)
            .append(source, that.source)
            .isEquals();
    }

    @Override
    public int hashCode() {
        return new HashCodeBuilder(17, 37)
            .appendSuper(super.hashCode())
            .append(enabled)
            .append(source)
            .toHashCode();
    }
}
