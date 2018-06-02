package com.evilbird.warcraft.action.sequence;

import com.badlogic.gdx.scenes.scene2d.Action;
import com.evilbird.engine.action.common.AudibleAction;
import com.evilbird.engine.action.framework.DelayedAction;
import com.evilbird.engine.action.framework.RepeatedAction;
import com.evilbird.engine.action.framework.SequenceAction;
import com.evilbird.engine.action.framework.TimeDuration;
import com.evilbird.engine.item.Item;
import com.evilbird.engine.item.specialized.animated.Audible;
import com.evilbird.engine.item.specialized.animated.SoundIdentifier;

import javax.inject.Inject;

public class AudibleSequence
{
    @Inject
    public AudibleSequence()
    {
    }

    public Action get(Item item, SoundIdentifier soundId, int repetitions, float repetitionDelay)
    {
        Action sound = new AudibleAction((Audible)item, soundId);
        Action buffer = new DelayedAction(new TimeDuration(repetitionDelay));
        Action soundBuffer = new SequenceAction(sound, buffer);
        return new RepeatedAction(soundBuffer, repetitions);
    }
}