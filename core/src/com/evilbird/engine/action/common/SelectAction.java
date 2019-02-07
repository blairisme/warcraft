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

    public boolean getSelected() {
        return selected;
    }

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
}
