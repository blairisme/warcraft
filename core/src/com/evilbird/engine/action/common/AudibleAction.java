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
import com.evilbird.engine.action.framework.BasicAction;
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

    public AudibleAction(Audible audible, SoundIdentifier sound) {
        this.audible = audible;
        this.sound = sound;
    }

    @Override
    public boolean act(float delta) {
        audible.setSound(sound);
        return true;
    }
}
