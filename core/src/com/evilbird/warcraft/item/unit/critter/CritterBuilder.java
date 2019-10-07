/*
 * Copyright (c) 2019, Blair Butterworth
 *
 * This work is licensed under the MIT License. To view a copy of this
 * license, visit
 *
 *        https://opensource.org/licenses/MIT
 */

package com.evilbird.warcraft.item.unit.critter;

import com.badlogic.gdx.scenes.scene2d.Touchable;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.evilbird.engine.item.specialized.ViewableStyle;
import com.evilbird.warcraft.item.unit.UnitAnimation;
import com.evilbird.warcraft.item.unit.UnitStyle;
import com.evilbird.warcraft.item.unit.critter.animations.CritterAnimations;
import com.evilbird.warcraft.item.unit.critter.sounds.CritterSounds;

/**
 * Creates a new {@link Critter} whose visual and audible presentation is
 * defined by the given {@link CritterAssets}.
 *
 * @author Blair Butterworth
 */
public class CritterBuilder
{
    private CritterAssets assets;
    private com.evilbird.warcraft.item.unit.critter.animations.CritterAnimations animations;
    private com.evilbird.warcraft.item.unit.critter.sounds.CritterSounds sounds;

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
        return result;
    }

    private Skin getSkin() {
        Skin skin = new Skin();
        skin.add("default", getAnimationStyle(), ViewableStyle.class);
        skin.add("default", getUnitStyle(), UnitStyle.class);
        return skin;
    }

    private ViewableStyle getAnimationStyle() {
        com.evilbird.warcraft.item.unit.critter.sounds.CritterSounds sounds = getSounds();
        com.evilbird.warcraft.item.unit.critter.animations.CritterAnimations animations = getAnimations();

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

    private com.evilbird.warcraft.item.unit.critter.animations.CritterAnimations getAnimations() {
        if (animations == null) {
            animations = new CritterAnimations(assets);
        }
        return animations;
    }

    private com.evilbird.warcraft.item.unit.critter.sounds.CritterSounds getSounds() {
        if (sounds == null) {
            sounds = new CritterSounds(assets);
        }
        return sounds;
    }
}
