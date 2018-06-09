package com.evilbird.engine.action.common;

import com.badlogic.gdx.scenes.scene2d.Action;
import com.evilbird.engine.action.framework.BasicAction;
import com.evilbird.engine.item.Reference;
import com.evilbird.engine.item.specialized.animated.Animated;
import com.evilbird.engine.item.specialized.animated.AnimationIdentifier;

/**
 * Created by blair on 15/09/2017.
 */
public class AnimateAction extends BasicAction
{
    private Animated animated;
    private AnimationIdentifier animation;
    private Reference<Animated> reference;

    public AnimateAction(Animated animated, AnimationIdentifier animation)
    {
        this.animated = animated;
        this.animation = animation;
    }

    public AnimateAction(Reference<Animated> reference, AnimationIdentifier animation)
    {
        this.reference = reference;
        this.animation = animation;
    }

    @Override
    public boolean act(float delta)
    {
        Animated animated = getAnimated();
        animated.setAnimation(animation);
        return true;
    }

    private Animated getAnimated()
    {
        if (reference != null){
            return reference.get();
        }
        return animated;
    }
}
