/*
 * Copyright (c) 2019, Blair Butterworth
 *
 * This work is licensed under the MIT License. To view a copy of this
 * license, visit
 *
 *        https://opensource.org/licenses/MIT
 */

package com.evilbird.warcraft.item.unit.conjured;

import com.badlogic.gdx.scenes.scene2d.Touchable;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.evilbird.engine.common.audio.sound.SoundCatalog;
import com.evilbird.engine.common.graphics.AnimationCatalog;
import com.evilbird.engine.item.specialized.ViewableStyle;
import com.evilbird.warcraft.item.unit.UnitAnimation;
import com.evilbird.warcraft.item.unit.UnitStyle;
import com.evilbird.warcraft.item.unit.UnitType;

/**
 * @author Blair Butterworth
 */
public class ConjuredBuilder
{
    private UnitType type;
    private ConjuredAssets assets;
    private AnimationCatalog animations;
    private SoundCatalog sounds;

    public ConjuredBuilder(ConjuredAssets assets, UnitType type) {
        this.assets = assets;
        this.type = type;
    }

    public ConjuredObject build() {
        ConjuredObject result = new ConjuredObject(getSkin());
        result.setAnimation(UnitAnimation.Idle);
        result.setSelected(false);
        result.setSelectable(false);
        result.setTouchable(Touchable.enabled);
        result.setSize(32, 32);
        result.setZIndex(0);
        return result;
    }

    private Skin getSkin() {
        UnitStyle style = getStyle();
        Skin skin = new Skin();
        skin.add("default", style, ViewableStyle.class);
        skin.add("default", style, UnitStyle.class);
        return skin;
    }

    private UnitStyle getStyle() {
        SoundCatalog sounds = getSounds();
        AnimationCatalog animations = getAnimations();

        UnitStyle style = new UnitStyle();
        style.animations = animations.get();
        style.sounds = sounds.get();
        return style;
    }

    private AnimationCatalog getAnimations() {
        if (animations == null) {
            animations = newAnimations();
        }
        return animations;
    }

    private AnimationCatalog newAnimations() {
        switch (type) {
            case RuneTrap: return new ConjuredAnimations(assets.getRune());
            default: throw new UnsupportedOperationException();
        }
    }

    private SoundCatalog getSounds() {
        if (sounds == null) {
            sounds = new ConjuredSounds(assets);
        }
        return sounds;
    }
}
