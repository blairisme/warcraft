/*
 * Copyright (c) 2019, Blair Butterworth
 *
 * This work is licensed under the MIT License. To view a copy of this
 * license, visit
 *
 *        https://opensource.org/licenses/MIT
 */

package com.evilbird.warcraft.object.unit.gatherer;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.scenes.scene2d.Touchable;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.evilbird.engine.common.audio.sound.SoundCatalog;
import com.evilbird.engine.common.graphics.animation.AnimationCatalog;
import com.evilbird.engine.common.graphics.renderable.FlashingRenderable;
import com.evilbird.engine.common.graphics.renderable.TextureRenderable;
import com.evilbird.engine.object.AnimatedObjectStyle;
import com.evilbird.warcraft.object.unit.UnitAnimation;
import com.evilbird.warcraft.object.unit.UnitStyle;
import com.evilbird.warcraft.object.unit.UnitType;
import com.evilbird.warcraft.object.unit.gatherer.animations.LandGathererAnimations;
import com.evilbird.warcraft.object.unit.gatherer.animations.SeaGathererAnimations;
import com.evilbird.warcraft.object.unit.gatherer.sounds.LandGathererSounds;
import com.evilbird.warcraft.object.unit.gatherer.sounds.SeaGathererSounds;
import org.apache.commons.lang3.Validate;

import java.util.HashMap;
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
    private Map<Texture, Texture> masks;

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
        skin.add("default", style, AnimatedObjectStyle.class);
        skin.add("default", style, UnitStyle.class);
        return skin;
    }

    private UnitStyle getStyle() {
        SoundCatalog sounds = getSounds();
        AnimationCatalog animations = getAnimations();

        UnitStyle style = new UnitStyle();
        style.animations = animations.get();
        style.sounds = sounds.get();
        style.selection = new TextureRenderable(assets.getSelectionTexture());
        style.highlight = new FlashingRenderable(assets.getHighlightTexture());
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
        if (masks == null) {
            masks = new HashMap<>();
            masks.put(assets.getBaseTexture(), assets.getMaskTexture());

            if (type.isNaval()) {
                masks.put(assets.getMoveWithOilTexture(), assets.getMoveWithOilMask());
            } else {
                masks.put(assets.getMoveWithGoldTexture(), assets.getMoveWithGoldMask());
                masks.put(assets.getMoveWithWoodTexture(), assets.getMoveWithWoodMask());
            }
        }
        return masks;
    }
}
