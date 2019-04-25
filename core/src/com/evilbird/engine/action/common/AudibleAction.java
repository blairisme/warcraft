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
import com.evilbird.engine.common.lang.Audible;
import com.evilbird.engine.common.lang.Identifier;
import com.evilbird.engine.common.serialization.SerializedConstructor;
import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;

import static com.evilbird.engine.action.common.ActionUtils.getRecipient;

/**
 * Instances of this class represent an Action that plays a sound.
 *
 * @author Blair Butterworth
 */
public class AudibleAction extends BasicAction
{
    private Identifier sound;
    private ActionRecipient recipient;

    @SerializedConstructor
    private AudibleAction(){
    }

    public AudibleAction(Identifier sound) {
        this(ActionRecipient.Subject, sound);
    }

    public AudibleAction(ActionRecipient recipient, Identifier sound){
        this.sound = sound;
        this.recipient = recipient;
    }

    public static AudibleAction play(Identifier sound) {
        return play(ActionRecipient.Subject, sound);
    }

    public static AudibleAction play(ActionRecipient source, Identifier sound) {
        return new AudibleAction(source, sound);
    }

    @Override
    public boolean act(float delta) {
        Audible audible = (Audible)getRecipient(this, recipient);
        audible.setSound(sound);
        return true;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == this) return true;
        if (obj == null) return false;
        if (obj.getClass() != getClass()) return false;

        AudibleAction that = (AudibleAction)obj;
        return new EqualsBuilder()
            .appendSuper(super.equals(obj))
            .append(sound, that.sound)
            .append(recipient, that.recipient)
            .isEquals();
    }

    @Override
    public int hashCode() {
        return new HashCodeBuilder(17, 37)
            .appendSuper(super.hashCode())
            .append(sound)
            .append(recipient)
            .toHashCode();
    }
}
