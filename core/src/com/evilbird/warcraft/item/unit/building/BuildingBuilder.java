/*
 * Copyright (c) 2019, Blair Butterworth
 *
 * This work is licensed under the MIT License. To view a copy of this
 * license, visit
 *
 *        https://opensource.org/licenses/MIT
 */

package com.evilbird.warcraft.item.unit.building;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.GridPoint2;
import com.badlogic.gdx.scenes.scene2d.Touchable;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.evilbird.engine.common.audio.SoundEffect;
import com.evilbird.engine.common.graphics.Animation;
import com.evilbird.engine.common.lang.Identifier;
import com.evilbird.engine.item.specialized.AnimatedItemStyle;
import com.evilbird.warcraft.item.common.animation.AnimationSetBuilder;
import com.evilbird.warcraft.item.unit.UnitAnimation;
import com.evilbird.warcraft.item.unit.UnitSound;
import com.evilbird.warcraft.item.unit.UnitStyle;
import org.apache.commons.lang3.tuple.Pair;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

import static com.evilbird.warcraft.item.common.animation.AnimationLayouts.buildingDestructionScheme;
import static com.evilbird.warcraft.item.common.animation.AnimationLayouts.constructBeginSchema;
import static com.evilbird.warcraft.item.common.animation.AnimationLayouts.constructEndSchema;
import static com.evilbird.warcraft.item.common.animation.AnimationLayouts.constructStaticSchema;
import static com.evilbird.warcraft.item.common.animation.AnimationLayouts.idleSingularSchema;

/**
 * Creates a new {@link Building} instance whose visual and audible
 * presentation is defined by the given {@link BuildingAssets}.
 *
 * @author Blair Butterworth
 */
public class BuildingBuilder
{
    private BuildingAssets assets;

    public BuildingBuilder(BuildingAssets assets) {
        this.assets = assets;
    }

    public Building build() {
        Building result = new Building(getSkin(assets));
        result.setAnimation(UnitAnimation.Idle);
        result.setSelected(false);
        result.setSelectable(true);
        result.setTouchable(Touchable.enabled);
        result.setSize(assets.getSize());
        result.setZIndex(0);
        return result;
    }

    public Fort newFort() {
        Fort result = new Fort(getSkin(assets));
        result.setAnimation(UnitAnimation.Idle);
        result.setSelected(false);
        result.setSelectable(true);
        result.setTouchable(Touchable.enabled);
        result.setSize(assets.getSize());
        result.setZIndex(0);
        return result;
    }

    private Skin getSkin(BuildingAssets assets) {
        Skin skin = new Skin();
        skin.add("default", getAnimationStyle(assets), AnimatedItemStyle.class);
        skin.add("default", getUnitStyle(assets), UnitStyle.class);
        return skin;
    }

    private AnimatedItemStyle getAnimationStyle(BuildingAssets assets) {
        AnimatedItemStyle animatedItemStyle = new AnimatedItemStyle();
        animatedItemStyle.animations = getAnimations(assets);
        animatedItemStyle.sounds = getSounds(assets);
        return animatedItemStyle;
    }

    private Map<Identifier, Animation> getAnimations(BuildingAssets assets) {
        Texture general = assets.getBaseTexture();
        Texture construction = assets.getConstructionTexture();
        Texture destruction = assets.getDestructionTexture();
        return getAnimations(general, construction, destruction);
    }

    private Map<Identifier, Animation> getAnimations(Texture general, Texture construction, Texture destruction) {
        GridPoint2 size = assets.getSize();
        AnimationSetBuilder builder = new AnimationSetBuilder();
        builder.set(UnitAnimation.Idle, idleSingularSchema(size.x, size.y), general);
        builder.set(UnitAnimation.BuildingSite, constructStaticSchema(size.x, size.y), construction);
        builder.set(UnitAnimation.Construct, Arrays.asList(
            Pair.of(constructBeginSchema(size.x, size.y), construction),
            Pair.of(constructEndSchema(size.x, size.y), general)));
        builder.set(UnitAnimation.Death, buildingDestructionScheme(), destruction);
        return builder.build();
    }

    private Map<Identifier, SoundEffect> getSounds(BuildingAssets assets) {
        Map<Identifier, SoundEffect> sounds = new HashMap<>();
        sounds.put(UnitSound.Die, assets.getDestroyedSound());
        sounds.put(UnitSound.Selected, assets.getSelectedSound());
        return sounds;
    }

    private UnitStyle getUnitStyle(BuildingAssets assets) {
        UnitStyle unitStyle = new UnitStyle();
        unitStyle.selection = assets.getSelectionTexture();
        return unitStyle;
    }
}
