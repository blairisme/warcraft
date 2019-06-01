/*
 * Copyright (c) 2019, Blair Butterworth
 *
 * This work is licensed under the MIT License. To view a copy of this
 * license, visit
 *
 *        https://opensource.org/licenses/MIT
 */

package com.evilbird.test.data.item;

import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.evilbird.engine.common.audio.SoundEffect;
import com.evilbird.engine.common.collection.Maps;
import com.evilbird.engine.common.graphics.Animation;
import com.evilbird.engine.item.specialized.AnimatedItemStyle;
import com.evilbird.warcraft.item.unit.UnitAnimation;
import com.evilbird.warcraft.item.unit.UnitSound;
import com.evilbird.warcraft.item.unit.UnitStyle;
import org.mockito.Mockito;

public class TestSkin
{
    private TestSkin() {
    }

    public static Skin newTestSkin() {
        Skin skin = new Skin();
        skin.add("default", newAnimationStyle(), AnimatedItemStyle.class);
        skin.add("default", new UnitStyle(), UnitStyle.class);
        return skin;
    }

    private static AnimatedItemStyle newAnimationStyle() {
        AnimatedItemStyle animatedItemStyle = new AnimatedItemStyle();
        animatedItemStyle.animations = Maps.of(UnitAnimation.Build, Mockito.mock(Animation.class));
        animatedItemStyle.sounds = Maps.of(UnitSound.Attack, Mockito.mock(SoundEffect.class));
        return animatedItemStyle;
    }
}
