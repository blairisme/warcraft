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

/**
 * Creates a new {@link ConjuredObject} instance whose visual and audible
 * presentation is defined by the given {@link ConjuredAssets}.
 *
 * @author Blair Butterworth
 */
public abstract class ConjuredBuilder
{
    private ConjuredAssets assets;
    private AnimationCatalog animations;
    private SoundCatalog sounds;

    public ConjuredBuilder(ConjuredAssets assets) {
        this.assets = assets;
    }

    public ConjuredObject build() {
        ConjuredObject result = newObject(getSkin());
        result.setAnimation(UnitAnimation.Idle);
        result.setHealth(1);
        result.setHealthMaximum(1);
        result.setSelected(false);
        result.setSelectable(false);
        result.setSight(0);
        result.setTouchable(Touchable.enabled);
        result.setZIndex(0);
        return result;
    }

    protected abstract ConjuredObject newObject(Skin skin);

    protected Skin getSkin() {
        UnitStyle style = getStyle();
        Skin skin = new Skin();
        skin.add("default", style, ViewableStyle.class);
        skin.add("default", style, UnitStyle.class);
        return skin;
    }

    protected UnitStyle getStyle() {
        SoundCatalog sounds = getSounds();
        AnimationCatalog animations = getAnimations();

        UnitStyle style = new UnitStyle();
        style.animations = animations.get();
        style.sounds = sounds.get();
        return style;
    }

    protected AnimationCatalog getAnimations() {
        if (animations == null) {
            animations = newAnimations(assets);
        }
        return animations;
    }

    protected abstract AnimationCatalog newAnimations(ConjuredAssets assets);

    protected SoundCatalog getSounds() {
        if (sounds == null) {
            sounds = newSounds(assets);
        }
        return sounds;
    }

    protected abstract SoundCatalog newSounds(ConjuredAssets assets);
}
