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
import com.evilbird.engine.action.framework.DelegateAction;
import com.evilbird.engine.action.framework.RepeatedAction;
import com.evilbird.engine.action.framework.SequenceAction;
import com.evilbird.engine.action.framework.TemporalAction;
import com.evilbird.engine.common.lang.Identifier;

import java.util.function.Predicate;

import static com.evilbird.engine.action.predicates.ActionPredicates.invocationCount;

/**
 * Instances of this class represent an {@link Action} that plays a sound
 * repetitively.
 *
 * @author Blair Butterworth
 */
public class RepeatedAudibleAction extends DelegateAction
{
    public RepeatedAudibleAction(Identifier sound, float delay, Predicate<Action> repeat) {
        Action audible = new AudibleAction(sound);
        Action buffer = new TemporalAction(delay);
        Action soundBuffer = new SequenceAction(audible, buffer);
        delegate = new RepeatedAction(soundBuffer, repeat);
    }

    public static RepeatedAudibleAction playRepeat(Identifier sound, int repetitions) {
        return new RepeatedAudibleAction(sound, 1, invocationCount(repetitions));
    }

    public static RepeatedAudibleAction playRepeat(Identifier sound, int repetitions, float interval) {
        return new RepeatedAudibleAction(sound, interval, invocationCount(repetitions));
    }

    public static RepeatedAudibleAction playRepeat(Identifier sound, Predicate<Action> condition) {
        return new RepeatedAudibleAction(sound, 1, condition);
    }

    public static RepeatedAudibleAction playRepeat(Identifier sound, float delay, Predicate<Action> condition) {
        return new RepeatedAudibleAction(sound, delay, condition);
    }
}
