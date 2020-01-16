/*
 * Copyright (c) 2020, Blair Butterworth
 *
 * This Source Code Form is subject to the terms of the Mozilla Public
 * License, v. 2.0. If a copy of the MPL was not distributed with this
 * file, You can obtain one at http://mozilla.org/MPL/2.0/.
 */

package com.evilbird.warcraft.object.unit.building;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.scenes.scene2d.Touchable;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.evilbird.engine.audio.sound.SoundCatalog;
import com.evilbird.engine.common.collection.Maps;
import com.evilbird.engine.common.graphics.animation.AnimationCatalog;
import com.evilbird.engine.common.graphics.renderable.AnimationRenderable;
import com.evilbird.engine.common.graphics.renderable.FlashingRenderable;
import com.evilbird.engine.common.graphics.renderable.TextureRenderable;
import com.evilbird.engine.object.AnimatedObjectStyle;
import com.evilbird.warcraft.common.WarcraftFaction;
import com.evilbird.warcraft.object.unit.UnitAnimation;
import com.evilbird.warcraft.object.unit.UnitArchetype;
import com.evilbird.warcraft.object.unit.UnitAttack;
import com.evilbird.warcraft.object.unit.UnitStyle;
import com.evilbird.warcraft.object.unit.UnitType;
import com.evilbird.warcraft.object.unit.building.animations.BuildingAnimations;
import com.evilbird.warcraft.object.unit.building.animations.ExtractorAnimations;
import com.evilbird.warcraft.object.unit.building.animations.TowerAnimations;
import com.evilbird.warcraft.object.unit.building.animations.TwinklingAnimations;
import com.evilbird.warcraft.object.unit.building.sounds.BuildingSounds;
import com.evilbird.warcraft.object.unit.building.sounds.TowerSounds;
import org.apache.commons.lang3.Validate;

import java.util.Collections;
import java.util.Map;

import static com.evilbird.warcraft.object.unit.UnitAnimation.HeavyDamage;
import static com.evilbird.warcraft.object.unit.UnitAnimation.LightDamage;
import static com.evilbird.warcraft.object.unit.UnitZIndex.BuildingIndex;

/**
 * Creates a new {@link Building} instance whose visual and audible
 * presentation is defined by the given {@link BuildingAssets}.
 *
 * @author Blair Butterworth
 */
public class BuildingBuilder
{
    private UnitType type;
    private UnitArchetype archetype;
    private UnitAttack attack;
    private WarcraftFaction faction;
    private BuildingAssets assets;
    private SoundCatalog sounds;
    private AnimationCatalog animations;
    private Map<Texture, Texture> masks;

    public BuildingBuilder(BuildingAssets assets, UnitType type) {
        Validate.notNull(assets);
        Validate.notNull(type);

        this.assets = assets;
        this.type = type;
        this.archetype = type.getArchetype();
        this.attack = type.getAttack();
        this.faction = type.getFaction();
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
        building.setZIndex(BuildingIndex);
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
        style.damageSound = assets.getBurningSound();
        return style;
    }

    private AnimationCatalog getAnimations() {
        if (animations == null) {
            animations = newAnimations();
        }
        return animations;
    }

    private AnimationCatalog newAnimations() {
        if (archetype == UnitArchetype.OilProducer) {
            return new ExtractorAnimations(assets);
        }
        if (archetype == UnitArchetype.Tower && attack != UnitAttack.None) {
            return new TowerAnimations(assets);
        }
        if (type == UnitType.GoblinAlchemist || type == UnitType.Church) {
            return new TwinklingAnimations(assets);
        }
        return new BuildingAnimations(assets);
    }

    private SoundCatalog getSounds() {
        if (sounds == null) {
            sounds = newSounds();
        }
        return sounds;
    }

    private SoundCatalog newSounds() {
        if (archetype == UnitArchetype.Tower && attack != UnitAttack.None) {
            return new TowerSounds(assets);
        }
        return new BuildingSounds(assets);
    }

    private Map<Texture, Texture> getMasks() {
        if (masks == null) {
            if (faction != WarcraftFaction.Neutral) {
                masks = Maps.of(assets.getBaseTexture(), assets.getMaskTexture());
            } else {
                masks = Collections.emptyMap();
            }
        }
        return masks;
    }
}
