/*
 * Copyright (c) 2019, Blair Butterworth
 *
 * This work is licensed under the MIT License. To view a copy of this
 * license, visit
 *
 *        https://opensource.org/licenses/MIT
 */

package com.evilbird.warcraft.item.unit.critter;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.scenes.scene2d.Touchable;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.evilbird.engine.common.audio.sound.Sound;
import com.evilbird.engine.common.graphics.Animation;
import com.evilbird.engine.common.lang.Identifier;
import com.evilbird.engine.item.specialized.ViewableStyle;
import com.evilbird.warcraft.item.common.animation.AnimationLayouts;
import com.evilbird.warcraft.item.common.animation.AnimationSetBuilder;
import com.evilbird.warcraft.item.unit.UnitAnimation;
import com.evilbird.warcraft.item.unit.UnitSound;
import com.evilbird.warcraft.item.unit.UnitStyle;

import java.util.HashMap;
import java.util.Map;

/**
 * Creates a new {@link Critter} whose visual and audible presentation is
 * defined by the given {@link CritterAssets}.
 *
 * @author Blair Butterworth
 */
public class CritterBuilder
{
    private CritterAssets assets;

    public CritterBuilder(CritterAssets assets) {
        this.assets = assets;
    }

    public Critter build() {
        Skin skin = getSkin(assets);
        Critter result = new Critter(skin);
        result.setAnimation(UnitAnimation.Idle);
        result.setSelected(false);
        result.setSelectable(true);
        result.setTouchable(Touchable.enabled);
        result.setSize(32, 32);
        return result;
    }

    private Skin getSkin(CritterAssets assets) {
        Skin skin = new Skin();
        skin.add("default", getAnimationStyle(assets), ViewableStyle.class);
        skin.add("default", getUnitStyle(assets), UnitStyle.class);
        return skin;
    }

    private ViewableStyle getAnimationStyle(CritterAssets assets) {
        ViewableStyle viewableStyle = new ViewableStyle();
        viewableStyle.animations = getAnimations(assets);
        viewableStyle.sounds = getSounds(assets);
        return viewableStyle;
    }

    private Map<Identifier, Animation> getAnimations(CritterAssets assets) {
        Texture general = assets.getBaseTexture();
        Texture decompose = assets.getDecomposeTexture();

        AnimationSetBuilder builder = new AnimationSetBuilder();
        builder.set(UnitAnimation.Idle, AnimationLayouts.idleSchema(32, 32), general);
        builder.set(UnitAnimation.Move, AnimationLayouts.idleSchema(32, 32), general);
        builder.set(UnitAnimation.Death, AnimationLayouts.critterDeathSchema(), general);
        builder.set(UnitAnimation.Decompose, AnimationLayouts.critterDeathSchema(), decompose);
        return builder.build();
    }

    private Map<Identifier, Sound> getSounds(CritterAssets assets) {
        Map<Identifier, Sound> sounds = new HashMap<>();
        sounds.put(UnitSound.Selected, assets.getSelectedSound());
        sounds.put(UnitSound.Die, assets.getDieSound());
        return sounds;
    }

    private UnitStyle getUnitStyle(CritterAssets assets) {
        UnitStyle unitStyle = new UnitStyle();
        unitStyle.selection = assets.getSelectionTexture();
        return unitStyle;
    }
}
