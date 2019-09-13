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
import com.evilbird.engine.common.audio.sound.Sound;
import com.evilbird.engine.common.graphics.Animation;
import com.evilbird.engine.common.lang.Identifier;
import com.evilbird.engine.item.specialized.ViewableStyle;
import com.evilbird.warcraft.item.common.animation.AnimationLayouts;
import com.evilbird.warcraft.item.common.animation.AnimationSetBuilder;
import com.evilbird.warcraft.item.unit.UnitAnimation;
import com.evilbird.warcraft.item.unit.UnitSound;
import com.evilbird.warcraft.item.unit.UnitStyle;

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
    private GathererAssets assets;

    public GathererBuilder(GathererAssets assets) {
        this.assets = assets;
    }

    public Gatherer newLandGatherer() {
        return newGatherer(getLandGathererSkin());
    }

    public Gatherer newSeaGatherer() {
        return newGatherer(getSeaGathererSkin());
    }

    public Gatherer newGatherer(Skin skin) {
        Gatherer result = new Gatherer(skin);
        result.setAnimation(UnitAnimation.Idle);
        result.setSelected(false);
        result.setSelectable(true);
        result.setTouchable(Touchable.enabled);
        result.setSize(assets.getSize());
        result.setZIndex(0);
        return result;
    }

    private Skin getLandGathererSkin() {
        Skin skin = new Skin();
        skin.add("default", getAnimationStyle(getLandAnimations()), ViewableStyle.class);
        skin.add("default", getUnitStyle(), UnitStyle.class);
        return skin;
    }

    private Skin getSeaGathererSkin() {
        Skin skin = new Skin();
        skin.add("default", getAnimationStyle(getSeaAnimations()), ViewableStyle.class);
        skin.add("default", getUnitStyle(), UnitStyle.class);
        return skin;
    }

    private ViewableStyle getAnimationStyle(Map<Identifier, Animation> animations) {
        ViewableStyle viewableStyle = new ViewableStyle();
        viewableStyle.animations = animations;
        viewableStyle.sounds = getSounds();
        return viewableStyle;
    }

    private Map<Identifier, Animation> getLandAnimations() {
        Texture general = assets.getBaseTexture();
        Texture moveGold = assets.getMoveWithGoldTexture();
        Texture moveWood = assets.getMoveWithWoodTexture();
        Texture decompose = assets.getDecomposeTexture();
        return getLandAnimations(general, moveGold, moveWood, decompose);
    }

    private Map<Identifier, Animation> getLandAnimations(Texture base, Texture gold, Texture wood, Texture decompose) {
        AnimationSetBuilder builder = new AnimationSetBuilder();

        builder.set(UnitAnimation.IdleBasic, AnimationLayouts.idleSchema(), base);
        builder.set(UnitAnimation.IdleGold, AnimationLayouts.idleSchema(), gold);
        builder.set(UnitAnimation.IdleWood, AnimationLayouts.idleSchema(), wood);
        builder.associate(UnitAnimation.Idle, UnitAnimation.IdleBasic);

        builder.set(UnitAnimation.MoveBasic, AnimationLayouts.moveSchema(), base);
        builder.set(UnitAnimation.MoveGold, AnimationLayouts.moveSchema(), gold);
        builder.set(UnitAnimation.MoveWood, AnimationLayouts.moveSchema(), wood);
        builder.associate(UnitAnimation.Move, UnitAnimation.MoveBasic);

        builder.set(UnitAnimation.Attack, AnimationLayouts.meleeAttackSchema(), base);
        builder.set(UnitAnimation.Death, AnimationLayouts.deathSchema(), base);
        builder.set(UnitAnimation.Decompose, AnimationLayouts.decomposeSchema(), decompose);
        builder.set(UnitAnimation.GatherWood, AnimationLayouts.gatherWoodSchema(), base);

        return builder.build();
    }

    private Map<Identifier, Animation> getSeaAnimations() {
        Texture general = assets.getBaseTexture();
        Texture decompose = assets.getDecomposeTexture();
        Texture withOil = assets.getMoveWithOilTexture();
        return getSeaAnimations(general, decompose, withOil);
    }

    private Map<Identifier, Animation> getSeaAnimations(Texture general, Texture decompose, Texture withOil) {
        AnimationSetBuilder builder = new AnimationSetBuilder();

        builder.set(UnitAnimation.IdleBasic, AnimationLayouts.idleSchema(assets.getSize()), general);
        builder.set(UnitAnimation.IdleOil, AnimationLayouts.idleSchema(assets.getSize()), withOil);
        builder.associate(UnitAnimation.Idle, UnitAnimation.IdleBasic);

        builder.set(UnitAnimation.MoveBasic, AnimationLayouts.idleSchema(assets.getSize()), general);
        builder.set(UnitAnimation.MoveOil, AnimationLayouts.idleSchema(assets.getSize()), withOil);
        builder.associate(UnitAnimation.Move, UnitAnimation.MoveBasic);

        builder.set(UnitAnimation.Attack, AnimationLayouts.idleSchema(assets.getSize()), general);
        builder.set(UnitAnimation.Death, AnimationLayouts.boatDeathSchema(), general);
        builder.set(UnitAnimation.Decompose, AnimationLayouts.boatDecomposeSchema(), decompose);

        return builder.build();
    }

    private Map<Identifier, Sound> getSounds() {
        Map<Identifier, Sound> sounds = new HashMap<>();
        sounds.put(UnitSound.Selected, assets.getSelectedSound());
        sounds.put(UnitSound.Acknowledge, assets.getAcknowledgeSound());
        sounds.put(UnitSound.Build, assets.getConstructSound());
        sounds.put(UnitSound.Complete, assets.getCompleteSound());
        sounds.put(UnitSound.Ready, assets.getReadySound());
        sounds.put(UnitSound.Attack, assets.getAttackSound());
        sounds.put(UnitSound.Die, assets.getDeadSound());
        sounds.put(UnitSound.ChopWood, assets.getChoppingSound());
        return sounds;
    }

    private UnitStyle getUnitStyle() {
        UnitStyle unitStyle = new UnitStyle();
        unitStyle.selection = assets.getSelectionTexture();
        return unitStyle;
    }
}
