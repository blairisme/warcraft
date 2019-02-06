/*
 * Blair Butterworth (c) 2019
 *
 * This work is licensed under the MIT License. To view a copy of this
 * license, visit
 *
 *      https://opensource.org/licenses/MIT
 */

package com.evilbird.engine.action.common;

import com.evilbird.engine.action.framework.*;
import com.evilbird.engine.action.framework.duration.TimeDuration;
import com.evilbird.engine.common.function.Predicate;
import com.evilbird.engine.common.function.Supplier;
import com.evilbird.engine.common.function.Suppliers;
import com.evilbird.engine.item.Item;
import com.evilbird.engine.item.specialized.animated.Audible;
import com.evilbird.engine.item.specialized.animated.SoundIdentifier;

import static com.evilbird.warcraft.action.common.ActionPredicates.counter;

/**
 * Instances of this class represent an {@link Action} that plays a sound
 * repetitively.
 *
 * @author Blair Butterworth
 */
public class RepeatedAudibleAction extends DelegateAction
{
    public RepeatedAudibleAction(SoundIdentifier sound, float delay, Predicate<Action> repeat) {
        Action audible = new AudibleAction(sound);
        Action buffer = new DelayedAction(new TimeDuration(delay));
        Action soundBuffer = new SequenceAction(audible, buffer);
        delegate = new RepeatedAction(soundBuffer, repeat);
    }

    @Deprecated
    public RepeatedAudibleAction(Item item, SoundIdentifier sound, int repetitions, float delay) {
        this(sound, delay, counter(repetitions));
    }

//    @Deprecated
//    public RepeatedAudibleAction(Item item, SoundIdentifier sound, float delay, Supplier<Boolean> repeat) {
//        Action audible = new AudibleAction((Audible)item, sound);
//        Action buffer = new DelayedAction(new TimeDuration(delay));
//        Action soundBuffer = new SequenceAction(audible, buffer);
//        delegate = new RepeatedAction(soundBuffer, repeat);
//    }

    @Override
    public boolean act(float delta) {
        return delegate.act(delta);
    }
}
