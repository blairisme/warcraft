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
    private Visible visible;
    private boolean enabled;

    public VisibleAction(Visible visible, boolean enabled) {
        this.visible = visible;
        this.enabled = enabled;
    }

    @Override
    public boolean act(float time) {
        visible.setVisible(enabled);
        return true;
    }
}
