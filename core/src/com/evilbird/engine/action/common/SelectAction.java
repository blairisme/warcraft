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
    private Selectable selectable;
    private boolean selected;

    public SelectAction(Selectable selectable, boolean selected) {
        this.selectable = selectable;
        this.selected = selected;
    }

    @Override
    public boolean act(float time) {
        selectable.setSelected(selected);
        return true;
    }
}
