/*
 * Blair Butterworth (c) 2019
 *
 * This work is licensed under the MIT License. To view a copy of this
 * license, visit
 *
 *      https://opensource.org/licenses/MIT
 */

package com.evilbird.engine.item.animated;

import com.evilbird.engine.common.graphics.DirectionalAnimation;

public interface Animated
{
    AnimationIdentifier getAnimation();

    void setAnimation(AnimationIdentifier id);

    DirectionalAnimation getAvailableAnimation(AnimationIdentifier id);

    void setAvailableAnimation(AnimationIdentifier id, DirectionalAnimation animation);
}
