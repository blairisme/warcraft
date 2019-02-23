/*
 * Blair Butterworth (c) 2019
 *
 * This work is licensed under the MIT License. To view a copy of this
 * license, visit
 *
 *      https://opensource.org/licenses/MIT
 */

package com.evilbird.engine.action.common;

import com.evilbird.engine.action.framework.BasicAction;
import com.evilbird.engine.item.animated.Animated;
import com.evilbird.engine.item.animated.AnimationIdentifier;

import javax.inject.Inject;

/**
 * Instances of this class represent an action that changes the animation
 * applied to the given {@link Animated element}.
 *
 * @author Blair Butterworth
 */
public class AnimateAction extends BasicAction
{
    private ActionTarget source;
    private AnimationIdentifier animation;

    @Inject
    public AnimateAction() {
        this(null);
    }

    public AnimateAction(AnimationIdentifier animation) {
        this(ActionTarget.Item, animation);
    }

    public AnimateAction(ActionTarget source, AnimationIdentifier animation) {
        this.source = source;
        this.animation = animation;
    }

    public AnimationIdentifier getAnimation() {
        return animation;
    }

    public void setAnimation(AnimationIdentifier animation) {
        this.animation = animation;
    }

    @Override
    public boolean act(float delta) {
        Animated animated = getAnimated();
        animated.setAnimation(animation);
        return true;
    }

    private Animated getAnimated() {
        switch (source) {
            case Item: return (Animated)getItem();
            case Target: return (Animated)getTarget();
            default: throw new UnsupportedOperationException();
        }
    }
}
