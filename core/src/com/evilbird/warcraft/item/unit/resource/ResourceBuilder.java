/*
 * Copyright (c) 2019, Blair Butterworth
 *
 * This work is licensed under the MIT License. To view a copy of this
 * license, visit
 *
 *        https://opensource.org/licenses/MIT
 */

package com.evilbird.warcraft.item.unit.resource;

import com.badlogic.gdx.scenes.scene2d.Touchable;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.evilbird.engine.item.specialized.ViewableStyle;
import com.evilbird.warcraft.item.unit.UnitAnimation;
import com.evilbird.warcraft.item.unit.UnitStyle;

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
        result.setAnimation(UnitAnimation.Idle);
        result.setSelected(false);
        result.setSelectable(true);
        result.setTouchable(Touchable.enabled);
        result.setSize(96, 96);
        return result;
    }

    private Skin getSkin() {
        Skin skin = new Skin();
        skin.add("default", getAnimationStyle(), ViewableStyle.class);
        skin.add("default", getUnitStyle(), UnitStyle.class);
        return skin;
    }

    private ViewableStyle getAnimationStyle() {
        ResourceSounds sounds = getSounds();
        ResourceAnimations animations = getAnimations();

        ViewableStyle viewableStyle = new ViewableStyle();
        viewableStyle.animations = animations.get();
        viewableStyle.sounds = sounds.get();
        return viewableStyle;
    }

    private UnitStyle getUnitStyle() {
        UnitStyle unitStyle = new UnitStyle();
        unitStyle.selection = assets.getSelectionTexture();
        return unitStyle;
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
