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
import com.evilbird.engine.item.Item;
import com.evilbird.engine.item.Reference;
import com.evilbird.engine.item.specialized.animated.Animated;
import com.evilbird.engine.item.specialized.animated.AnimationIdentifier;

import javax.inject.Inject;

/**
 * Instances of this class represent an action that changes the animation
 * applied to the given {@link Animated element}.
 *
 * @author Blair Butterworth
 */
public class AnimateAction extends BasicAction
{
    private Animated animated;
    private AnimationIdentifier animation;
    @Deprecated
    private Reference<? extends Animated> reference;

    @Inject
    public AnimateAction(){
    }

    public AnimateAction(AnimationIdentifier animation) {
        this.animation = animation;
    }

    @Deprecated
    public AnimateAction(Animated animated, AnimationIdentifier animation) {
        this.animated = animated;
        this.animation = animation;
    }

    @Deprecated
    public AnimateAction(Reference<? extends Animated> reference, AnimationIdentifier animation) {
        this.reference = reference;
        this.animation = animation;
    }

    @Override
    public void setItem(Item animated) {
        this.animated = (Animated)animated;
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
        if (reference != null){
            return reference.get();
        }
        return animated;
    }
}
