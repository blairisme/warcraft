/*
 * Copyright (c) 2019, Blair Butterworth
 *
 * This work is licensed under the MIT License. To view a copy of this
 * license, visit
 *
 *        https://opensource.org/licenses/MIT
 */

package com.evilbird.warcraft.item.unit.building.human;

import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.evilbird.engine.common.audio.SoundEffect;
import com.evilbird.engine.common.collection.Maps;
import com.evilbird.engine.common.graphics.Animation;
import com.evilbird.engine.common.graphics.Colours;
import com.evilbird.engine.common.graphics.TextureUtils;
import com.evilbird.engine.common.inject.AssetProvider;
import com.evilbird.engine.common.lang.Identifier;
import com.evilbird.engine.device.Device;
import com.evilbird.engine.item.Item;
import com.evilbird.engine.item.specialized.AnimatedItemStyle;
import com.evilbird.warcraft.item.common.animation.AnimationSets;
import com.evilbird.warcraft.item.unit.UnitAnimation;
import com.evilbird.warcraft.item.unit.UnitSound;
import com.evilbird.warcraft.item.unit.UnitStyle;
import com.evilbird.warcraft.item.unit.UnitType;
import com.evilbird.warcraft.item.unit.building.Building;

import javax.inject.Inject;
import java.util.Map;

import static com.evilbird.engine.common.assets.AssetUtilities.loadSoundSet;
import static com.evilbird.engine.common.audio.SoundUtils.newSoundEffect;
import static com.evilbird.engine.common.file.FileType.MP3;
import static com.evilbird.engine.common.lang.TextIdentifier.objectIdentifier;

/**
 * Instances of this class create {@link Building Town Halls}, loading the
 * necessary assets and defining the appropriate attributes.
 *
 * @author Blair Butterworth
 */
public class TownHallFactory implements AssetProvider<Item>
{
    private static final String BASE = "data/textures/human/winter/town_hall.png";
    private static final String ICONS = "data/textures/neutral/perennial/icons.png";
    private static final String CONSTRUCTION = "data/textures/neutral/perennial/construction_large.png";
    private static final String DESTRUCTION = "data/textures/neutral/winter/destroyed_site.png";
    private static final String DESTROYED = "data/sounds/neutral/building/destroyed/";
    private static final String SELECTED = "data/sounds/neutral/building/selected/1.mp3";

    private AssetManager assets;

    @Inject
    public TownHallFactory(Device device) {
        this(device.getAssetStorage());
    }

    public TownHallFactory(AssetManager assets) {
        this.assets = assets;
    }

    @Override
    public void load() {
        loadTextures();
        loadSounds();
    }

    private void loadTextures() {
        assets.load(BASE, Texture.class);
        assets.load(CONSTRUCTION, Texture.class);
        assets.load(DESTRUCTION, Texture.class);
        assets.load(ICONS, Texture.class);
    }

    private void loadSounds() {
        loadSoundSet(assets, DESTROYED, MP3, 3);
        assets.load(SELECTED, Sound.class);
    }

    @Override
    public Item get() {
        Building result = new Building(getSkin());
        result.setAnimation(UnitAnimation.Idle);
        result.setSight(1);  //1
        result.setHealth(1200);
        result.setHealthMaximum(1200);
        result.setName("Town Hall");
        result.setType(UnitType.TownHall);
        result.setIdentifier(objectIdentifier("TownHall", result));
        result.setSize(128, 128);
        result.setZIndex(0);
        return result;
    }

    private Skin getSkin() {
        Skin skin = new Skin();
        skin.add("default", getAnimationStyle(), AnimatedItemStyle.class);
        skin.add("default", getUnitStyle(), UnitStyle.class);
        return skin;
    }

    private AnimatedItemStyle getAnimationStyle() {
        AnimatedItemStyle animatedItemStyle = new AnimatedItemStyle();
        animatedItemStyle.animations = getAnimations();
        animatedItemStyle.sounds = getSounds();
        return animatedItemStyle;
    }

    private Map<Identifier, Animation> getAnimations() {
        Texture general = assets.get(BASE, Texture.class);
        Texture construct = assets.get(CONSTRUCTION, Texture.class);
        Texture destruction = assets.get(DESTRUCTION, Texture.class);
        return AnimationSets.buildingAnimations(general, construct, destruction, 128, 128);
    }

    private Map<Identifier, SoundEffect> getSounds() {
        return Maps.of(
            UnitSound.Die, newSoundEffect(assets, DESTROYED, MP3, 3),
            UnitSound.Selected, newSoundEffect(assets, SELECTED));
    }

    private UnitStyle getUnitStyle() {
        UnitStyle unitStyle = new UnitStyle();
        unitStyle.icon = TextureUtils.getDrawable(assets, ICONS, 0, 304, 46, 38);
        unitStyle.selection = TextureUtils.getRectangle(96, 96, Colours.FOREST_GREEN);
        return unitStyle;
    }
}
