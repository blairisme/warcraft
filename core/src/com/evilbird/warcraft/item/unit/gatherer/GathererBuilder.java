/*
 * Copyright (c) 2019, Blair Butterworth
 *
 * This work is licensed under the MIT License. To view a copy of this
 * license, visit
 *
 *        https://opensource.org/licenses/MIT
 */

package com.evilbird.warcraft.item.unit.gatherer;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.scenes.scene2d.Touchable;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.evilbird.engine.common.audio.sound.SoundCatalog;
import com.evilbird.engine.common.collection.Maps;
import com.evilbird.engine.common.graphics.AnimationCatalog;
import com.evilbird.engine.item.specialized.ViewableStyle;
import com.evilbird.warcraft.item.unit.UnitAnimation;
import com.evilbird.warcraft.item.unit.UnitStyle;
import com.evilbird.warcraft.item.unit.UnitType;
import com.evilbird.warcraft.item.unit.gatherer.animations.LandGathererAnimations;
import com.evilbird.warcraft.item.unit.gatherer.animations.SeaGathererAnimations;
import com.evilbird.warcraft.item.unit.gatherer.sounds.LandGathererSounds;
import com.evilbird.warcraft.item.unit.gatherer.sounds.SeaGathererSounds;
import org.apache.commons.lang3.Validate;

import java.util.Collections;
import java.util.Map;

/**
 * Creates a new {@link Gatherer} instance whose visual and audible
 * presentation is defined by the given {@link GathererAssets}.
 *
 * @author Blair Butterworth
 */
public class GathererBuilder
{
    private UnitType type;
    private GathererAssets assets;
    private AnimationCatalog animations;
    private SoundCatalog sounds;

    public GathererBuilder(GathererAssets assets, UnitType type) {
        Validate.notNull(assets);
        Validate.notNull(type);
        Validate.isTrue(type.isGatherer());

        this.assets = assets;
        this.type = type;
    }

    public Gatherer build() {
        Gatherer result = new Gatherer(getSkin());
        result.setAnimation(UnitAnimation.Idle);
        result.setSelected(false);
        result.setSelectable(true);
        result.setTouchable(Touchable.enabled);
        result.setSize(assets.getSize());
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
        style.selection = assets.getSelectionTexture();
        style.masks = getMasks();
        return style;
    }

    private AnimationCatalog getAnimations() {
        if (animations == null) {
            animations = type.isNavalUnit()
                ? new SeaGathererAnimations(assets)
                : new LandGathererAnimations(assets);
        }
        return animations;
    }

    private SoundCatalog getSounds() {
        if (sounds == null) {
            sounds = type.isNavalUnit()
                ? new SeaGathererSounds(assets)
                : new LandGathererSounds(assets);
        }
        return sounds;
    }

    private Map<Texture, Texture> getMasks() {
        if (! type.isNeutral()) {
            return Maps.of(assets.getBaseTexture(), assets.getMaskTexture());
        }
        return Collections.emptyMap();
    }
}
