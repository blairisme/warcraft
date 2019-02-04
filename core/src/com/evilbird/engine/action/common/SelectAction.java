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

public class SelectAction extends BasicAction
{
    private boolean selected;
    private Selectable selectable;

    public SelectAction() {
    }

    public SelectAction(Selectable selectable, boolean selected) {
        setSelectable(selectable);
        setSelected(selected);
    }

    public Selectable getSelectable() {
        return selectable;
    }

    public boolean getSelected() {
        return selected;
    }

    public void setSelectable(Selectable selectable) {
        this.selectable = selectable;
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
}
