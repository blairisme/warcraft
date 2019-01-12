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
import com.evilbird.engine.item.Selectable;

public class DisableAction extends BasicAction
{
    private Selectable selectable;
    private boolean disabled;

    public DisableAction(Selectable selectable, boolean disabled) {
        this.selectable = selectable;
        this.disabled = disabled;
    }

    @Override
    public boolean act(float delta) {
        selectable.setSelectable(disabled);
        return true;
    }
}
