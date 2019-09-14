/*
 * Copyright (c) 2019, Blair Butterworth
 *
 * This work is licensed under the MIT License. To view a copy of this
 * license, visit
 *
 *        https://opensource.org/licenses/MIT
 */

package com.evilbird.warcraft.item.unit.resource;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.scenes.scene2d.Touchable;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.evilbird.engine.common.audio.sound.Sound;
import com.evilbird.engine.common.graphics.Animation;
import com.evilbird.engine.common.lang.Identifier;
import com.evilbird.engine.item.specialized.ViewableStyle;
import com.evilbird.warcraft.item.common.animation.AnimationSetBuilder;
import com.evilbird.warcraft.item.unit.UnitAnimation;
import com.evilbird.warcraft.item.unit.UnitSound;
import com.evilbird.warcraft.item.unit.UnitStyle;

import java.util.HashMap;
import java.util.Map;

import static com.evilbird.warcraft.item.common.animation.AnimationLayouts.buildingDestructionScheme;
import static com.evilbird.warcraft.item.common.animation.AnimationLayouts.gatheringSchema;
import static com.evilbird.warcraft.item.common.animation.AnimationLayouts.idleSingularSchema;

/**
 * Creates a new {@link Resource} instance whose visual and audible
 * presentation is defined by the given {@link ResourceAssets}.
 *
 * @author Blair Butterworth
 */
public class ResourceBuilder
{
    private ResourceAssets assets;

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
        ViewableStyle viewableStyle = new ViewableStyle();
        viewableStyle.animations = getAnimations();
        viewableStyle.sounds = getSounds();
        return viewableStyle;
    }

    private Map<Identifier, Animation> getAnimations() {
        Texture general = assets.getGeneralTexture();
        Texture destruction = assets.getDestructionTexture();
        return getAnimations(general, destruction);
    }

    private Map<Identifier, Animation> getAnimations(Texture general, Texture destruction) {
        AnimationSetBuilder builder = new AnimationSetBuilder();
        builder.set(UnitAnimation.Idle, idleSingularSchema(96, 96), general);
        builder.set(UnitAnimation.Gathering, gatheringSchema(96, 96), general);
        builder.set(UnitAnimation.Death, buildingDestructionScheme(), destruction);
        return builder.build();
    }

    private Map<Identifier, Sound> getSounds() {
        Map<Identifier, Sound> sounds = new HashMap<>();
        sounds.put(UnitSound.Selected, assets.getSelectedSound());
        sounds.put(UnitSound.Die, assets.getDestroyedSound());
        return sounds;
    }

    private UnitStyle getUnitStyle() {
        UnitStyle unitStyle = new UnitStyle();
        unitStyle.selection = assets.getSelectionTexture();
        return unitStyle;
    }
}
