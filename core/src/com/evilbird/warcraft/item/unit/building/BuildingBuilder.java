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
import com.evilbird.engine.common.audio.sound.Sound;
import com.evilbird.engine.common.graphics.Animation;
import com.evilbird.engine.common.lang.Identifier;
import com.evilbird.engine.item.specialized.ViewableStyle;
import com.evilbird.warcraft.item.common.animation.AnimationSetBuilder;
import com.evilbird.warcraft.item.common.production.ProductionTimes;
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
import static com.evilbird.warcraft.item.common.animation.AnimationLayouts.gatheringOilSchema;
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
    private ProductionTimes times;

    public BuildingBuilder(BuildingAssets assets, ProductionTimes times) {
        this.assets = assets;
        this.times = times;
    }

    public Building build() {
        return createBuilding(new Building(getSkin(assets)));
    }

    public Fort newFort() {
        return createBuilding(new Fort(getSkin(assets)));
    }

    public ResourceExtractor newResourceExtractor() {
        return createBuilding(new ResourceExtractor(getExtractorSkin(assets)));
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

    private Skin getSkin(BuildingAssets assets) {
        Skin skin = new Skin();
        skin.add("default", getAnimationStyle(assets), ViewableStyle.class);
        skin.add("default", getUnitStyle(assets), UnitStyle.class);
        return skin;
    }

    private Skin getExtractorSkin(BuildingAssets assets) {
        Skin skin = new Skin();
        skin.add("default", getExtractorAnimationStyle(assets), ViewableStyle.class);
        skin.add("default", getUnitStyle(assets), UnitStyle.class);
        return skin;
    }

    private ViewableStyle getAnimationStyle(BuildingAssets assets) {
        ViewableStyle viewableStyle = new ViewableStyle();
        viewableStyle.animations = getAnimations(assets);
        viewableStyle.sounds = getSounds(assets);
        return viewableStyle;
    }

    private ViewableStyle getExtractorAnimationStyle(BuildingAssets assets) {
        ViewableStyle viewableStyle = new ViewableStyle();
        viewableStyle.animations = getExtractorAnimations(assets);
        viewableStyle.sounds = getSounds(assets);
        return viewableStyle;
    }

    private Map<Identifier, Animation> getAnimations(BuildingAssets assets) {
        Texture general = assets.getBaseTexture();
        Texture construction = assets.getConstructionTexture();
        Texture destruction = assets.getDestructionTexture();
        return getAnimations(general, construction, destruction);
    }

    private Map<Identifier, Animation> getAnimations(Texture general, Texture build, Texture destroy) {
        GridPoint2 size = assets.getSize();
        AnimationSetBuilder builder = new AnimationSetBuilder();
        addGeneralAnimations(builder, general, destroy, size);
        addBuildingAnimation(builder, general, build, size);
        return builder.build();
    }

    private Map<Identifier, Animation> getExtractorAnimations(BuildingAssets assets) {
        Texture general = assets.getBaseTexture();
        Texture construction = assets.getConstructionTexture();
        Texture destruction = assets.getDestructionTexture();
        return getExtractorAnimations(general, construction, destruction);
    }

    private Map<Identifier, Animation> getExtractorAnimations(Texture general, Texture build, Texture destroy) {
        GridPoint2 size = assets.getSize();
        AnimationSetBuilder builder = new AnimationSetBuilder();
        addGeneralAnimations(builder, general, destroy, size);
        addBuildingAnimation(builder, general, build, size);
        addGatheringAnimation(builder, general, size);
        return builder.build();
    }

    private void addGeneralAnimations(AnimationSetBuilder builder, Texture general, Texture destroy, GridPoint2 size) {
        builder.set(UnitAnimation.Idle, idleSingularSchema(size.x, size.y), general);
        builder.set(UnitAnimation.Death, buildingDestructionScheme(), destroy);
    }

    private void addBuildingAnimation(AnimationSetBuilder builder, Texture general, Texture build, GridPoint2 size) {
        float duration = times.buildTime(assets.getType()) / 2f;
        builder.set(UnitAnimation.BuildingSite, constructStaticSchema(size.x, size.y), build);
        builder.set(UnitAnimation.Construct, Arrays.asList(
            Pair.of(constructBeginSchema(size.x, size.y, duration), build),
            Pair.of(constructEndSchema(size.x, size.y, duration), general)));
    }

    private void addGatheringAnimation(AnimationSetBuilder builder, Texture general, GridPoint2 size) {
        builder.set(UnitAnimation.Gathering, gatheringOilSchema(size.x, size.y), general);
    }

    private Map<Identifier, Sound> getSounds(BuildingAssets assets) {
        Map<Identifier, Sound> sounds = new HashMap<>();
        sounds.put(UnitSound.Die, assets.getDestroyedSound());
        sounds.put(UnitSound.Selected, assets.getSelectedSound());
        sounds.put(UnitSound.Placement, assets.getPlacementSound());
        return sounds;
    }

    private UnitStyle getUnitStyle(BuildingAssets assets) {
        UnitStyle unitStyle = new UnitStyle();
        unitStyle.selection = assets.getSelectionTexture();
        return unitStyle;
    }
}
