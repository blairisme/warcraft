package com.evilbird.engine.item.specialized.animated;

import com.evilbird.engine.common.graphics.DirectionalAnimation;

public interface Animated
{
    public AnimationIdentifier getAnimation();

    public void setAnimation(AnimationIdentifier id);

    public DirectionalAnimation getAvailableAnimation(AnimationIdentifier id);

    public void setAvailableAnimation(AnimationIdentifier id, DirectionalAnimation animation);
}
