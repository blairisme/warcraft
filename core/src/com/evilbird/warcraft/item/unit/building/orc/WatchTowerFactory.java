/*
 * Copyright (c) 2019, Blair Butterworth
 *
 * This work is licensed under the MIT License. To view a copy of this
 * license, visit
 *
 *        https://opensource.org/licenses/MIT
 */

package com.evilbird.warcraft.item.unit.building.orc;

import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.scenes.scene2d.Touchable;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.evilbird.engine.common.graphics.Animation;
import com.evilbird.engine.common.graphics.Colours;
import com.evilbird.engine.common.inject.AssetProvider;
import com.evilbird.engine.common.lang.Identifier;
import com.evilbird.engine.device.Device;
import com.evilbird.engine.item.Item;
import com.evilbird.engine.item.specialized.AnimatedItemStyle;
import com.evilbird.warcraft.item.common.animation.AnimationSetBuilder;
import com.evilbird.warcraft.item.unit.UnitStyle;
import com.evilbird.warcraft.item.unit.UnitType;
import com.evilbird.warcraft.item.unit.building.Building;

import javax.inject.Inject;
import java.util.Collections;
import java.util.Map;

import static com.evilbird.engine.common.graphics.TextureUtils.getDrawable;
import static com.evilbird.engine.common.graphics.TextureUtils.getRectangle;
import static com.evilbird.engine.common.lang.TextIdentifier.objectIdentifier;
import static com.evilbird.warcraft.item.common.animation.AnimationSchemas.idleSingularSchema;
import static com.evilbird.warcraft.item.unit.UnitAnimation.Idle;

/**
 * Instances of this class create {@link Building Watch Towers}, loading the
 * necessary assets and defining the appropriate attributes.
 *
 * @author Blair Butterworth
 */
public class WatchTowerFactory implements AssetProvider<Item>
{
    private static final String BASE = "data/textures/orc/winter/watch_tower.png";
    private static final String ICONS = "data/textures/neutral/perennial/icons.png";

    private AssetManager assets;

    @Inject
    public WatchTowerFactory(Device device) {
        this(device.getAssetStorage());
    }

    public WatchTowerFactory(AssetManager assets) {
        this.assets = assets;
    }

    @Override
    public void load() {
        assets.load(BASE, Texture.class);
        assets.load(ICONS, Texture.class);
    }

    @Override
    public Item get() {
        Building result = new Building(getSkin());
        result.setAnimation(Idle);
        result.setSight(160);
        result.setHealth(100);
        result.setHealthMaximum(100);
        result.setName("Watch Tower");
        result.setSelected(false);
        result.setSelectable(true);
        result.setTouchable(Touchable.enabled);
        result.setType(UnitType.WatchTower);
        result.setIdentifier(objectIdentifier("WatchTower", result));
        result.setSize(64, 64);
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
        animatedItemStyle.sounds = Collections.emptyMap();
        return animatedItemStyle;
    }

    private Map<Identifier, Animation> getAnimations() {
        AnimationSetBuilder builder = new AnimationSetBuilder();
        builder.set(Idle, idleSingularSchema(64, 64), assets.get(BASE, Texture.class));
        return builder.build();
    }

    private UnitStyle getUnitStyle() {
        UnitStyle unitStyle = new UnitStyle();
        unitStyle.icon = getDrawable(assets, ICONS, 46, 456, 46, 38);
        unitStyle.selection = getRectangle(64, 64, Colours.FOREST_GREEN);
        return unitStyle;
    }
}
