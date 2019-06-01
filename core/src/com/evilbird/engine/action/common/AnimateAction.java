/*
 * Blair Butterworth (c) 2019
 *
 * This work is licensed under the MIT License. To view a copy of this
 * license, visit
 *
 *      https://opensource.org/licenses/MIT
 */

package com.evilbird.engine.action.common;

import com.evilbird.engine.action.Action;
import com.evilbird.engine.action.framework.BasicAction;
import com.evilbird.engine.common.lang.Animated;
import com.evilbird.engine.common.lang.Identifier;
import com.evilbird.engine.common.serialization.SerializedConstructor;
import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;
import org.apache.commons.lang3.builder.ToStringBuilder;

import static com.evilbird.engine.action.common.ActionUtils.getRecipient;

/**
 * Instances of this class represent an {@link Action} that changes the
 * animation applied to the given {@link Animated element}.
 *
 * @author Blair Butterworth
 */
public class AnimateAction extends BasicAction
{
    private ActionRecipient recipient;
    private Identifier animation;

    @SerializedConstructor
    private AnimateAction(){
    }

    public AnimateAction(Identifier animation) {
        this(ActionRecipient.Subject, animation);
    }

    public AnimateAction(ActionRecipient recipient, Identifier animation) {
        this.recipient = recipient;
        this.animation = animation;
    }

    public static AnimateAction animate(Identifier animation) {
        return new AnimateAction(animation);
    }

    public static AnimateAction animate(ActionRecipient recipient, Identifier animation) {
        return new AnimateAction(recipient, animation);
    }

    @Override
    public boolean act(float delta) {
        Animated animated = (Animated)getRecipient(this, recipient);
        animated.setAnimation(animation);
        return true;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == null) { return false; }
        if (obj == this) { return true; }
        if (obj.getClass() != getClass()) { return false; }

        AnimateAction that = (AnimateAction)obj;
        return new EqualsBuilder()
            .appendSuper(super.equals(obj))
            .append(recipient, that.recipient)
            .append(animation, that.animation)
            .isEquals();
    }

    @Override
    public int hashCode() {
        return new HashCodeBuilder(17, 37)
            .appendSuper(super.hashCode())
            .append(recipient)
            .append(animation)
            .toHashCode();
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this)
            .append("recipient", recipient)
            .append("animation", animation)
            .toString();
    }
}
