/*
 * Blair Butterworth (c) 2018
 *
 * This work is licensed under the MIT License. To view a copy of this
 * license, visit
 *
 *      https://opensource.org/licenses/MIT
 */

package com.evilbird.warcraft.action.component;

import com.badlogic.gdx.scenes.scene2d.Action;
import com.evilbird.engine.common.graphics.DirectionalAnimation;
import com.evilbird.engine.item.specialized.animated.Animated;
import com.evilbird.engine.item.specialized.animated.AnimationIdentifier;

/**
 * Instances of this class assigned an alias to a given animation identifier.
 *
 * @author Blair Butterworth
 */
public class AnimationAliasAction extends Action
{
    private Animated item;
    private AnimationIdentifier id;
    private AnimationIdentifier alias;

    public AnimationAliasAction(Animated item, AnimationIdentifier id, AnimationIdentifier alias) {
        this.item = item;
        this.id = id;
        this.alias = alias;
    }

    @Override
    public boolean act(float delta) {
        DirectionalAnimation newAnimations = item.getAvailableAnimation(alias);
        item.setAvailableAnimation(id, newAnimations);
        return true;
    }
}
