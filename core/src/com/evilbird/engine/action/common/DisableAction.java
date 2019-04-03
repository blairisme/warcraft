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
import com.evilbird.engine.common.lang.Selectable;
import com.evilbird.engine.item.Item;
import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;

import javax.inject.Inject;

/**
 * Instances of this class update the disabled status of an {@link Item}.
 *
 * @author Blair Butterworth
 */
public class DisableAction extends BasicAction
{
    private boolean disabled;
    private ActionTarget source;

    @Inject
    public DisableAction() {
        this(true);
    }

    public DisableAction(boolean disabled) {
        this(ActionTarget.Item, disabled);
    }

    public DisableAction(ActionTarget source, boolean disabled) {
        this.source = source;
        this.disabled = disabled;
    }

    public static DisableAction enable() {
        return new DisableAction(false);
    }

    public static DisableAction disable() {
        return new DisableAction(true);
    }

    @Override
    public boolean act(float delta) {
        Selectable selectable = getSelectable();
        selectable.setSelectable(disabled);
        return true;
    }

    private Selectable getSelectable() {
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

        DisableAction that = (DisableAction)obj;
        return new EqualsBuilder()
            .appendSuper(super.equals(obj))
            .append(disabled, that.disabled)
            .append(source, that.source)
            .isEquals();
    }

    @Override
    public int hashCode() {
        return new HashCodeBuilder(17, 37)
            .appendSuper(super.hashCode())
            .append(disabled)
            .append(source)
            .toHashCode();
    }
}
