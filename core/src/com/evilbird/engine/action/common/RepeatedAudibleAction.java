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
import com.evilbird.engine.action.framework.DelayedAction;
import com.evilbird.engine.action.framework.DelegateAction;
import com.evilbird.engine.action.framework.RepeatedAction;
import com.evilbird.engine.action.framework.SequenceAction;
import com.evilbird.engine.action.framework.duration.TimeDuration;
import com.evilbird.engine.item.Item;
import com.evilbird.engine.item.specialized.animated.Audible;
import com.evilbird.engine.item.specialized.animated.SoundIdentifier;

/**
 * Instances of this class represent an {@link Action} that plays a sound
 * repetitively.
 *
 * @author Blair Butterworth
 */
public class RepeatedAudibleAction extends DelegateAction
{
    public RepeatedAudibleAction(Item item, SoundIdentifier soundId, int repetitions, float repetitionDelay) {
        Action sound = new AudibleAction((Audible)item, soundId);
        Action buffer = new DelayedAction(new TimeDuration(repetitionDelay));
        Action soundBuffer = new SequenceAction(sound, buffer);
        delegate = new RepeatedAction(soundBuffer, repetitions);
    }

    @Override
    public boolean act(float delta) {
        return delegate.act(delta);
    }
}
