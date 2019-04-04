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

/**
 * Instances of this class represent an Action that plays a sound.
 *
 * @author Blair Butterworth
 */
public class AudibleAction extends BasicAction
{
    private Identifier sound;
    private ActionTarget source;

    @SerializedConstructor
    private AudibleAction(){
    }

    public AudibleAction(Identifier sound) {
        this(ActionTarget.Item, sound);
    }

    public AudibleAction(ActionTarget source, Identifier sound){
        this.sound = sound;
        this.source = source;
    }

    public static AudibleAction play(Identifier sound) {
        return play(ActionTarget.Item, sound);
    }

    public static AudibleAction play(ActionTarget source, Identifier sound) {
        return new AudibleAction(source, sound);
    }

    @Override
    public boolean act(float delta) {
        Audible audible = getAudible();
        audible.setSound(sound);
        return true;
    }

    private Audible getAudible() {
        switch (source) {
            case Item: return (Audible)getItem();
            case Target: return (Audible)getTarget();
            default: throw new UnsupportedOperationException();
        }
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
            .append(source, that.source)
            .isEquals();
    }

    @Override
    public int hashCode() {
        return new HashCodeBuilder(17, 37)
            .appendSuper(super.hashCode())
            .append(sound)
            .append(source)
            .toHashCode();
    }
}
