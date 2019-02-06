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
import com.evilbird.engine.common.lang.Identifier;
import com.evilbird.engine.item.specialized.animated.Audible;
import com.evilbird.engine.item.specialized.animated.SoundIdentifier;

/**
 * Instances of this class represent an Action that plays a sound.
 *
 * @author Blair Butterworth
 */
public class AudibleAction extends BasicAction
{
    private SoundIdentifier sound;

    public AudibleAction(){
    }

    public AudibleAction(SoundIdentifier sound){
        this.sound = sound;
    }

    @Deprecated
    public AudibleAction(Audible audible, SoundIdentifier sound) {
        setSound(sound);
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
}
