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
import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;

public class VisibleAction extends BasicAction
{
    private boolean enabled;

    public VisibleAction() {
        this(true);
    }

    public VisibleAction(boolean enabled) {
        this.enabled = enabled;
    }

    @Override
    public boolean act(float time) {
        Visible visible = getItem();
        visible.setVisible(enabled);
        return true;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == this) return true;
        if (obj == null) return false;
        if (obj.getClass() != getClass()) return false;

        VisibleAction that = (VisibleAction)obj;
        return new EqualsBuilder()
            .appendSuper(super.equals(obj))
            .append(enabled, that.enabled)
            .isEquals();
    }

    @Override
    public int hashCode() {
        return new HashCodeBuilder(17, 37)
            .appendSuper(super.hashCode())
            .append(enabled)
            .toHashCode();
    }
}
