/*
 * Copyright (c) 2019, Blair Butterworth
 *
 * This work is licensed under the MIT License. To view a copy of this
 * license, visit
 *
 *        https://opensource.org/licenses/MIT
 */

package com.evilbird.warcraft.item.unit.building;

import com.badlogic.gdx.scenes.scene2d.Touchable;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.evilbird.engine.common.audio.sound.SoundCatalog;
import com.evilbird.engine.common.graphics.AnimationCatalog;
import com.evilbird.engine.item.specialized.ViewableStyle;
import com.evilbird.warcraft.item.common.production.ProductionTimes;
import com.evilbird.warcraft.item.unit.UnitAnimation;
import com.evilbird.warcraft.item.unit.UnitStyle;
import com.evilbird.warcraft.item.unit.UnitType;
import com.evilbird.warcraft.item.unit.building.animations.BuildingAnimations;
import com.evilbird.warcraft.item.unit.building.animations.ExtractorAnimations;
import com.evilbird.warcraft.item.unit.building.animations.TowerAnimations;
import com.evilbird.warcraft.item.unit.building.sounds.BuildingSounds;
import com.evilbird.warcraft.item.unit.building.sounds.TowerSounds;
import org.apache.commons.lang3.Validate;

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
    private AnimationCatalog animations;
    private SoundCatalog sounds;

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

    public ResourceExtractor buildExtractor() {
        return createBuilding(new ResourceExtractor(getSkin()));
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
        Skin skin = new Skin();
        skin.add("default", getAnimationStyle(), ViewableStyle.class);
        skin.add("default", getUnitStyle(), UnitStyle.class);
        return skin;
    }

    private ViewableStyle getAnimationStyle() {
        SoundCatalog sounds = getSounds();
        AnimationCatalog animations = getAnimations();

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
}
