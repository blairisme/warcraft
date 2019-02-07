/*
 * Blair Butterworth (c) 2018
 *
 * This work is licensed under the MIT License. To view a copy of this
 * license, visit
 *
 *      https://opensource.org/licenses/MIT
 */

package com.evilbird.warcraft.action.common;

import com.evilbird.engine.action.framework.BasicAction;
import com.evilbird.engine.common.graphics.DirectionalAnimation;
import com.evilbird.engine.item.specialized.animated.Animated;
import com.evilbird.engine.item.specialized.animated.AnimationIdentifier;

/**
 * Instances of this class assigned an alias to a given animation identifier.
 *
 * @author Blair Butterworth
 */
public class AnimationAliasAction extends BasicAction
{
    private AnimationIdentifier id;
    private AnimationIdentifier alias;

    public AnimationAliasAction(AnimationIdentifier id, AnimationIdentifier alias) {
        this.id = id;
        this.alias = alias;
    }

    @Override
    public boolean act(float delta) {
        Animated item = (Animated)getItem();
        DirectionalAnimation newAnimations = item.getAvailableAnimation(alias);
        item.setAvailableAnimation(id, newAnimations);
        return true;
    }
}
