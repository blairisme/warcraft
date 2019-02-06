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

import javax.inject.Inject;

public class DisableAction extends BasicAction
{
    private boolean disabled;

    @Inject
    public DisableAction() {
    }

    public DisableAction(boolean disabled) {
        this.disabled = disabled;
    }

    @Deprecated
    public DisableAction(Selectable selectable, boolean disabled) {
        setItem((Item)selectable);
        this.disabled = disabled;
    }

    public void setDisabled(boolean disabled) {
        this.disabled = disabled;
    }

    @Override
    public boolean act(float delta) {
        Selectable selectable = getItem();
        selectable.setSelectable(disabled);
        return true;
    }
}
