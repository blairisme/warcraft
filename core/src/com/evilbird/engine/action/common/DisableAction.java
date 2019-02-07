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

public class DisableAction extends BasicAction
{
    private boolean disabled;
    private ActionTarget source;

    @Inject
    public DisableAction() {
        this(false);
    }

    public DisableAction(boolean disabled) {
        this(ActionTarget.Item, disabled);
    }

    public DisableAction(ActionTarget source, boolean disabled) {
        this.source = source;
        this.disabled = disabled;
    }

    public void setDisabled(boolean disabled) {
        this.disabled = disabled;
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
}
