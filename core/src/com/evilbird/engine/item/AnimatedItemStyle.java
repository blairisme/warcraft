/*
 * Blair Butterworth (c) 2019
 *
 * This work is licensed under the MIT License. To view a copy of this
 * license, visit
 *
 *      https://opensource.org/licenses/MIT
 */

package com.evilbird.engine.item;

import com.badlogic.gdx.graphics.Texture;
import com.evilbird.engine.common.audio.SoundEffect;
import com.evilbird.engine.common.graphics.DirectionalAnimation;
import com.evilbird.engine.common.lang.Identifier;

import java.util.Map;

public class AnimatedItemStyle
{
    public Texture selection;
    public Map<Identifier, SoundEffect> sounds;
    public Map<Identifier, DirectionalAnimation> animations;
}
