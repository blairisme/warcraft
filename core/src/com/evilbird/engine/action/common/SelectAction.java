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

public class SelectAction extends BasicAction
{
    private boolean selected;

    @Inject
    public SelectAction() {
    }

    @Deprecated
    public SelectAction(Selectable selectable, boolean selected) {
        setItem((Item)selectable);
        setSelected(selected);
    }

    public boolean getSelected() {
        return selected;
    }

    public void setSelected(boolean selected) {
        this.selected = selected;
    }

    @Override
    public boolean act(float time) {
        Selectable selectable = getItem();
        selectable.setSelected(selected);
        return true;
    }
}
