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
import com.badlogic.gdx.math.GridPoint2;
import com.evilbird.engine.common.inject.AssetProvider;
import com.evilbird.engine.device.Device;
import com.evilbird.engine.item.Item;
import com.evilbird.warcraft.item.unit.building.Building;
import com.evilbird.warcraft.item.unit.building.BuildingAssets;
import com.evilbird.warcraft.item.unit.building.BuildingBuilder;

import javax.inject.Inject;

import static com.evilbird.engine.common.lang.TextIdentifier.objectIdentifier;
import static com.evilbird.warcraft.item.WarcraftItemConstants.TILE_WIDTH;
import static com.evilbird.warcraft.item.unit.UnitType.TownHall;

/**
 * Instances of this class create {@link Building Town Halls}, loading the
 * necessary assets and defining the appropriate attributes.
 *
 * @author Blair Butterworth
 */
public class TownHallFactory implements AssetProvider<Item>
{
    private static final GridPoint2 ICON = new GridPoint2(0, 304);
    private static final GridPoint2 SIZE = new GridPoint2(128, 128);

    private BuildingAssets assets;
    private BuildingBuilder builder;

    @Inject
    public TownHallFactory(Device device) {
        this(device.getAssetStorage());
    }

    public TownHallFactory(AssetManager manager) {
        this.assets = new BuildingAssets(manager, TownHall, ICON, SIZE);
        this.builder = new BuildingBuilder(assets);
    }

    @Override
    public void load() {
        assets.load();
    }

    @Override
    public Item get() {
        Building result = builder.build();
        result.setHealth(1200);
        result.setHealthMaximum(1200);
        result.setIdentifier(objectIdentifier("TownHall", result));
        result.setName("Town Hall");
        result.setSight(TILE_WIDTH);
        result.setType(TownHall);
        return result;
    }


//    private static final String BASE = "data/textures/human/winter/town_hall.png";
//    private static final String ICONS = "data/textures/neutral/perennial/icons.png";
//    private static final String CONSTRUCTION = "data/textures/neutral/perennial/construction_large.png";
//    private static final String DESTRUCTION = "data/textures/neutral/winter/destroyed_site.png";
//    private static final String DESTROYED = "data/sounds/common/building/destroyed/";
//    private static final String SELECTED = "data/sounds/common/building/selected/1.mp3";
//
//    private AssetManager assets;
//
//    @Inject
//    public TownHallFactory(Device device) {
//        this(device.getAssetStorage());
//    }
//
//    public TownHallFactory(AssetManager assets) {
//        this.assets = assets;
//    }
//
//    @Override
//    public void load() {
//        loadTextures();
//        loadSounds();
//    }
//
//    private void loadTextures() {
//        assets.load(BASE, Texture.class);
//        assets.load(CONSTRUCTION, Texture.class);
//        assets.load(DESTRUCTION, Texture.class);
//        assets.load(ICONS, Texture.class);
//    }
//
//    private void loadSounds() {
//        loadSoundSet(assets, DESTROYED, MP3, 3);
//        assets.load(SELECTED, Sound.class);
//    }
//
//    @Override
//    public Item get() {
//        Building result = new Building(getSkin());
//        result.setAnimation(UnitAnimation.Idle);
//        result.setSight(1);  //1
//        result.setHealth(1200);
//        result.setHealthMaximum(1200);
//        result.setName("Town Hall");
//        result.setType(UnitType.TownHall);
//        result.setIdentifier(objectIdentifier("TownHall", result));
//        result.setSize(128, 128);
//        result.setZIndex(0);
//        return result;
//    }
//
//    private Skin getSkin() {
//        Skin skin = new Skin();
//        skin.add("default", getAnimationStyle(), AnimatedItemStyle.class);
//        skin.add("default", getUnitStyle(), UnitStyle.class);
//        return skin;
//    }
//
//    private AnimatedItemStyle getAnimationStyle() {
//        AnimatedItemStyle animatedItemStyle = new AnimatedItemStyle();
//        animatedItemStyle.animations = getAnimations();
//        animatedItemStyle.sounds = getSounds();
//        return animatedItemStyle;
//    }
//
//    private Map<Identifier, Animation> getAnimations() {
//        Texture general = assets.get(BASE, Texture.class);
//        Texture construct = assets.get(CONSTRUCTION, Texture.class);
//        Texture destruction = assets.get(DESTRUCTION, Texture.class);
//        return AnimationSets.buildingAnimations(general, construct, destruction, 128, 128);
//    }
//
//    private Map<Identifier, SoundEffect> getSounds() {
//        return Maps.of(
//            UnitSound.Die, newSoundEffect(assets, DESTROYED, MP3, 3),
//            UnitSound.Selected, newSoundEffect(assets, SELECTED));
//    }
//
//    private UnitStyle getUnitStyle() {
//        UnitStyle unitStyle = new UnitStyle();
//        unitStyle.icon = TextureUtils.getDrawable(assets, ICONS, 0, 304, 46, 38);
//        unitStyle.selection = TextureUtils.getRectangle(96, 96, Colours.FOREST_GREEN);
//        return unitStyle;
//    }
}
