/*
 * Copyright (c) 2019, Blair Butterworth
 *
 * This work is licensed under the MIT License. To view a copy of this
 * license, visit
 *
 *        https://opensource.org/licenses/MIT
 */

package com.evilbird.warcraft.object.unit.building;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.scenes.scene2d.Touchable;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.evilbird.engine.common.audio.sound.SoundCatalog;
import com.evilbird.engine.common.collection.Maps;
import com.evilbird.engine.common.graphics.animation.AnimationCatalog;
import com.evilbird.engine.common.graphics.renderable.AnimationRenderable;
import com.evilbird.engine.common.graphics.renderable.FlashingRenderable;
import com.evilbird.engine.common.graphics.renderable.TextureRenderable;
import com.evilbird.engine.object.AnimatedObjectStyle;
import com.evilbird.warcraft.object.common.production.ProductionTimes;
import com.evilbird.warcraft.object.unit.UnitAnimation;
import com.evilbird.warcraft.object.unit.UnitStyle;
import com.evilbird.warcraft.object.unit.UnitType;
import com.evilbird.warcraft.object.unit.building.animations.BuildingAnimations;
import com.evilbird.warcraft.object.unit.building.animations.ExtractorAnimations;
import com.evilbird.warcraft.object.unit.building.animations.GoblinAlchemistAnimations;
import com.evilbird.warcraft.object.unit.building.animations.TowerAnimations;
import com.evilbird.warcraft.object.unit.building.sounds.BuildingSounds;
import com.evilbird.warcraft.object.unit.building.sounds.TowerSounds;
import org.apache.commons.lang3.Validate;

import java.util.Collections;
import java.util.Map;

import static com.evilbird.warcraft.object.unit.UnitAnimation.HeavyDamage;
import static com.evilbird.warcraft.object.unit.UnitAnimation.LightDamage;

/**
 * Creates a new {@link Building} instance whose visual and audible
 * presentation is defined by the given {@link BuildingAssets}.
 *
 * @author Blair Butterworth
 */
public class BuildingBuilder
{
    private UnitType type;
    private BuildingAssets assets;
    private ProductionTimes times;
    private SoundCatalog sounds;
    private AnimationCatalog animations;
    private Map<Texture, Texture> masks;

    public BuildingBuilder(BuildingAssets assets, ProductionTimes times, UnitType type) {
        Validate.notNull(assets);
        Validate.notNull(times);
        Validate.notNull(type);
        Validate.isTrue(type.isBuilding());

        this.type = type;
        this.assets = assets;
        this.times = times;
    }

    public Building build() {
        return createBuilding(new Building(getSkin()));
    }

    public Tower buildOffensiveBuilding() {
        return createBuilding(new Tower(getSkin()));
    }

    public OilPlatform buildExtractor() {
        return createBuilding(new OilPlatform(getSkin()));
    }

    private <T extends Building> T createBuilding(T building) {
        building.setAnimation(UnitAnimation.Idle);
        building.setSelected(false);
        building.setSelectable(true);
        building.setTouchable(Touchable.enabled);
        building.setSize(assets.getSize());
        building.setZIndex(0);
        return building;
    }

    private Skin getSkin() {
        BuildingStyle style = getStyle();
        Skin skin = new Skin();
        skin.add("default", style, AnimatedObjectStyle.class);
        skin.add("default", style, UnitStyle.class);
        skin.add("default", style, BuildingStyle.class);
        return skin;
    }

    private BuildingStyle getStyle() {
        SoundCatalog sounds = getSounds();
        AnimationCatalog animations = getAnimations();

        BuildingStyle style = new BuildingStyle();
        style.animations = animations.get();
        style.masks = getMasks();
        style.sounds = sounds.get();
        style.selection = new TextureRenderable(assets.getSelectionTexture());
        style.highlight = new FlashingRenderable(assets.getHighlightTexture());
        style.lightDamage = new AnimationRenderable(style.animations.get(LightDamage));
        style.heavyDamage = new AnimationRenderable(style.animations.get(HeavyDamage));
        return style;
    }

    private AnimationCatalog getAnimations() {
        if (animations == null) {
            animations = newAnimations();
        }
        return animations;
    }

    private AnimationCatalog newAnimations() {
        if (type.isResourceExtractor()) {
            return new ExtractorAnimations(assets, times);
        }
        if (type.isOffensiveTower()) {
            return new TowerAnimations(assets, times);
        }
        if (type == UnitType.GoblinAlchemist) {
            return new GoblinAlchemistAnimations(assets, times);
        }
        return new BuildingAnimations(assets, times);
    }

    private SoundCatalog getSounds() {
        if (sounds == null) {
            sounds = newSounds();
        }
        return sounds;
    }

    private SoundCatalog newSounds() {
        if (type.isOffensiveTower()) {
            return new TowerSounds(assets);
        }
        return new BuildingSounds(assets);
    }

    private Map<Texture, Texture> getMasks() {
        if (masks == null) {
            if (!type.isNeutral()) {
                masks = Maps.of(assets.getBaseTexture(), assets.getMaskTexture());
            } else {
                masks = Collections.emptyMap();
            }
        }
        return masks;
    }
}
