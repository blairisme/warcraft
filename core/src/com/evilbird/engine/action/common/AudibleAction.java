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
import com.evilbird.engine.item.Item;
import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;

import javax.inject.Inject;

/**
 * Instances of this class represent an Action that plays a sound.
 *
 * @author Blair Butterworth
 */
public class AudibleAction extends BasicAction
{
    private Identifier sound;

    @Inject
    public AudibleAction(){
        this(null);
    }

    public AudibleAction(Identifier sound){
        this.sound = sound;
    }

    public static AudibleAction play(Identifier sound) {
        return new AudibleAction(sound);
    }

    public Identifier getSound() {
        return sound;
    }

    @Deprecated //TODO: Immutableize
    public void setSound(Identifier sound) {
        this.sound = sound;
    }

    @Override
    public boolean act(float delta) {
        Item item = getItem();
        if (item instanceof Audible) {
            Audible audible = (Audible)item;
            audible.setSound(sound);
        }
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
