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

public class VisibleAction extends BasicAction
{
    private boolean enabled;

    public VisibleAction(boolean enabled) {
        this.enabled = enabled;
    }

    @Override
    public boolean act(float time) {
        Visible visible = getItem();
        visible.setVisible(enabled);
        return true;
    }
}
