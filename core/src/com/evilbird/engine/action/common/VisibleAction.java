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
import com.evilbird.engine.object.GameObject;
import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;

import javax.inject.Inject;

import static com.evilbird.engine.action.common.ActionUtils.getRecipient;

/**
 * Instances of this {@link BasicAction Action} modify an {@link GameObject Items}
 * visibility: whether its rendered or not.
 *
 * @author Blair Butterworth
 */
public class VisibleAction extends BasicAction
{
    private boolean enabled;
    private ActionRecipient recipient;

    @Inject
    public VisibleAction() {
        this(true);
    }

    public VisibleAction(boolean enabled) {
        this(ActionRecipient.Subject, enabled);
    }

    public VisibleAction(ActionRecipient target, boolean enabled) {
        this.enabled = enabled;
        this.recipient = target;
    }

    public static VisibleAction show() {
        return show(ActionRecipient.Subject);
    }

    public static VisibleAction show(ActionRecipient recipient) {
        return new VisibleAction(recipient, true);
    }

    public static VisibleAction hide() {
        return hide(ActionRecipient.Subject);
    }

    public static VisibleAction hide(ActionRecipient recipient) {
        return new VisibleAction(recipient, false);
    }

    @Override
    public boolean act(float time) {
        Visible visible = getRecipient(this, recipient);
        visible.setVisible(enabled);
        return true;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == null) { return false; }
        if (obj == this) { return true; }
        if (obj.getClass() != getClass()) { return false; }

        VisibleAction that = (VisibleAction)obj;
        return new EqualsBuilder()
            .appendSuper(super.equals(obj))
            .append(enabled, that.enabled)
            .append(recipient, that.recipient)
            .isEquals();
    }

    @Override
    public int hashCode() {
        return new HashCodeBuilder(17, 37)
            .appendSuper(super.hashCode())
            .append(enabled)
            .append(recipient)
            .toHashCode();
    }
}
