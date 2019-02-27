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
import com.evilbird.engine.item.animated.Audible;
import com.evilbird.engine.item.animated.SoundIdentifier;
import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;

/**
 * Instances of this class represent an Action that plays a sound.
 *
 * @author Blair Butterworth
 */
public class AudibleAction extends BasicAction
{
    private SoundIdentifier sound;

    public AudibleAction(){
        this(null);
    }

    public AudibleAction(SoundIdentifier sound){
        this.sound = sound;
    }

    public SoundIdentifier getSound() {
        return sound;
    }

    public void setSound(SoundIdentifier sound) {
        this.sound = sound;
    }

    @Override
    public boolean act(float delta) {
        Audible audible = (Audible)getItem();
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
            .isEquals();
    }

    @Override
    public int hashCode() {
        return new HashCodeBuilder(17, 37)
            .appendSuper(super.hashCode())
            .append(sound)
            .toHashCode();
    }
}
