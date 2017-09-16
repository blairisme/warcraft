package com.evilbird.engine.action.replacement;

import com.badlogic.gdx.scenes.scene2d.Action;
import com.evilbird.engine.item.specialized.animated.AnimationIdentifier;
import com.evilbird.engine.item.specialized.animated.Audible;
import com.evilbird.engine.item.specialized.animated.SoundIdentifier;

/**
 * Created by blair on 15/09/2017.
 */
public class AudibleAction extends Action
{
    private Audible audible;
    private SoundIdentifier sound;

    public AudibleAction(Audible audible, SoundIdentifier sound)
    {
        this.audible = audible;
        this.sound = sound;
    }

    @Override
    public boolean act(float delta)
    {
        audible.setSound(sound);
        return true;
    }
}
