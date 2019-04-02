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
import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;

import javax.inject.Inject;

public class SelectAction extends BasicAction
{
    private boolean selected;
    private ActionTarget source;

    @Inject
    public SelectAction() {
        this(false);
    }

    public SelectAction(boolean selected) {
        this(ActionTarget.Item, selected);
    }

    public SelectAction(ActionTarget source, boolean selected) {
        this.source = source;
        this.selected = selected;
    }

    public static SelectAction select() {
        return new SelectAction(true);
    }

    public static SelectAction deselect() {
        return new SelectAction(false);
    }

    public boolean getSelected() {
        return selected;
    }

    @Deprecated //TODO: Immutable
    public void setSelected(boolean selected) {
        this.selected = selected;
    }

    @Override
    public boolean act(float time) {
        Selectable selectable = getSelectable();
        selectable.setSelected(selected);
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

        SelectAction that = (SelectAction)obj;
        return new EqualsBuilder()
            .appendSuper(super.equals(obj))
            .append(selected, that.selected)
            .append(source, that.source)
            .isEquals();
    }

    @Override
    public int hashCode() {
        return new HashCodeBuilder(17, 37)
            .appendSuper(super.hashCode())
            .append(selected)
            .append(source)
            .toHashCode();
    }
}
