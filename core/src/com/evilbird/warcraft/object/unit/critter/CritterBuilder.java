/*
 * Copyright (c) 2020, Blair Butterworth
 *
 * This Source Code Form is subject to the terms of the Mozilla Public
 * License, v. 2.0. If a copy of the MPL was not distributed with this
 * file, You can obtain one at http://mozilla.org/MPL/2.0/.
 */

package com.evilbird.warcraft.object.unit.critter;

import com.badlogic.gdx.scenes.scene2d.Touchable;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.evilbird.engine.common.graphics.renderable.FlashingRenderable;
import com.evilbird.engine.common.graphics.renderable.TextureRenderable;
import com.evilbird.engine.object.AnimatedObjectStyle;
import com.evilbird.warcraft.object.unit.UnitAnimation;
import com.evilbird.warcraft.object.unit.UnitStyle;
import com.evilbird.warcraft.object.unit.critter.animations.CritterAnimations;
import com.evilbird.warcraft.object.unit.critter.sounds.CritterSounds;

import static com.evilbird.warcraft.object.unit.UnitZIndex.UnitIndex;

/**
 * Creates a new {@link Critter} whose visual and audible presentation is
 * defined by the given {@link CritterAssets}.
 *
 * @author Blair Butterworth
 */
public class CritterBuilder
{
    private CritterAssets assets;
    private CritterAnimations animations;
    private CritterSounds sounds;

    public CritterBuilder(CritterAssets assets) {
        this.assets = assets;
    }

    public Critter build() {
        Critter result = new Critter(getSkin());
        result.setAnimation(UnitAnimation.Idle);
        result.setSelected(false);
        result.setSelectable(true);
        result.setTouchable(Touchable.enabled);
        result.setSize(32, 32);
        result.setZIndex(UnitIndex);
        return result;
    }

    private Skin getSkin() {
        UnitStyle style = getStyle();
        Skin skin = new Skin();
        skin.add("default", style, AnimatedObjectStyle.class);
        skin.add("default", style, UnitStyle.class);
        return skin;
    }

    private UnitStyle getStyle() {
        CritterSounds sounds = getSounds();
        CritterAnimations animations = getAnimations();

        UnitStyle style = new UnitStyle();
        style.animations = animations.get();
        style.sounds = sounds.get();
        style.selection = new TextureRenderable(assets.getSelectionTexture());
        style.highlight = new FlashingRenderable(assets.getHighlightTexture());
        return style;
    }

    private CritterAnimations getAnimations() {
        if (animations == null) {
            animations = new CritterAnimations(assets);
        }
        return animations;
    }

    private CritterSounds getSounds() {
        if (sounds == null) {
            sounds = new CritterSounds(assets);
        }
        return sounds;
    }
}
