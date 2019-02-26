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
import com.evilbird.engine.action.framework.*;
import com.evilbird.engine.common.function.Predicate;
import com.evilbird.engine.item.animated.SoundIdentifier;

import static com.evilbird.warcraft.action.common.query.ActionPredicates.counter;

/**
 * Instances of this class represent an {@link Action} that plays a sound
 * repetitively.
 *
 * @author Blair Butterworth
 */
public class RepeatedAudibleAction extends DelegateAction
{
    public RepeatedAudibleAction(SoundIdentifier sound, int repetitions, float delay) {
        this(sound, delay, counter(repetitions));
    }

    public RepeatedAudibleAction(SoundIdentifier sound, float delay, Predicate<Action> repeat) {
        Action audible = new AudibleAction(sound);
        Action buffer = new DelayedAction(delay);
        Action soundBuffer = new SequenceAction(audible, buffer);
        delegate = new RepeatedAction(soundBuffer, repeat);
    }
}
