/*
 * Blair Butterworth (c) 2019
 *
 * This work is licensed under the MIT License. To view a copy of this
 * license, visit
 *
 *      https://opensource.org/licenses/MIT
 */

package com.evilbird.engine.action.common;

import com.badlogic.gdx.scenes.scene2d.Action;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.evilbird.engine.action.framework.BasicAction;
import com.evilbird.engine.item.Item;
import com.evilbird.engine.item.specialized.animated.Audible;
import com.evilbird.engine.item.specialized.animated.SoundIdentifier;

/**
 * Instances of this class represent an {@link Action} plays a sound.
 *
 * @author Blair Butterworth
 */
public class AudibleAction extends BasicAction
{
    private Audible audible;
    private SoundIdentifier sound;

    public AudibleAction() {
    }

    public AudibleAction(Audible audible, SoundIdentifier sound) {
        setAudible(audible);
        setSound(sound);
    }

    public Audible getAudible() {
        return audible;
    }

    public SoundIdentifier getSound() {
        return sound;
    }

    public void setAudible(Audible audible) {
        this.audible = audible;
    }

    public void setSound(SoundIdentifier sound) {
        this.sound = sound;
    }

    @Override
    public boolean act(float delta) {
        Audible audible = getAudible();
        audible.setSound(getSound());
        return true;
    }
}
