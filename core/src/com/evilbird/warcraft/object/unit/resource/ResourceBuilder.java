/*
 * Copyright (c) 2020, Blair Butterworth
 *
 * This Source Code Form is subject to the terms of the Mozilla Public
 * License, v. 2.0. If a copy of the MPL was not distributed with this
 * file, You can obtain one at http://mozilla.org/MPL/2.0/.
 */

package com.evilbird.warcraft.object.unit.resource;

import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.evilbird.engine.common.graphics.renderable.FlashingRenderable;
import com.evilbird.engine.common.graphics.renderable.TextureRenderable;
import com.evilbird.engine.object.AnimatedObjectStyle;
import com.evilbird.warcraft.object.unit.UnitStyle;

import static com.badlogic.gdx.scenes.scene2d.Touchable.enabled;
import static com.evilbird.warcraft.object.unit.UnitAnimation.Idle;
import static com.evilbird.warcraft.object.unit.UnitZIndex.ResourceIndex;

/**
 * Creates a new {@link Resource} instance whose visual and audible
 * presentation is defined by the given {@link ResourceAssets}.
 *
 * @author Blair Butterworth
 */
public class ResourceBuilder
{
    private ResourceAssets assets;
    private ResourceAnimations animations;
    private ResourceSounds sounds;

    public ResourceBuilder(ResourceAssets assets) {
        this.assets = assets;
    }

    public Resource build() {
        Resource result = new Resource(getSkin());
        result.setAnimation(Idle);
        result.setSelected(false);
        result.setSelectable(true);
        result.setTouchable(enabled);
        result.setSize(96, 96);
        result.setZIndex(ResourceIndex);
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
        ResourceSounds sounds = getSounds();
        ResourceAnimations animations = getAnimations();

        UnitStyle style = new UnitStyle();
        style.animations = animations.get();
        style.sounds = sounds.get();
        style.selection = new TextureRenderable(assets.getSelectionTexture());
        style.highlight = new FlashingRenderable(assets.getHighlightTexture());
        return style;
    }

    private ResourceAnimations getAnimations() {
        if (animations == null) {
            animations = new ResourceAnimations(assets);
        }
        return animations;
    }

    private ResourceSounds getSounds() {
        if (sounds == null) {
            sounds = new ResourceSounds(assets);
        }
        return sounds;
    }
}
