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
import com.evilbird.engine.common.audio.SoundEffect;
import com.evilbird.engine.common.graphics.Animation;
import com.evilbird.engine.common.lang.Identifier;
import com.evilbird.engine.item.specialized.AnimatedItemStyle;
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
        skin.add("default", getAnimationStyle(getLandAnimations()), AnimatedItemStyle.class);
        skin.add("default", getUnitStyle(), UnitStyle.class);
        return skin;
    }

    private Skin getSeaGathererSkin() {
        Skin skin = new Skin();
        skin.add("default", getAnimationStyle(getSeaAnimations()), AnimatedItemStyle.class);
        skin.add("default", getUnitStyle(), UnitStyle.class);
        return skin;
    }

    private AnimatedItemStyle getAnimationStyle(Map<Identifier, Animation> animations) {
        AnimatedItemStyle animatedItemStyle = new AnimatedItemStyle();
        animatedItemStyle.animations = animations;
        animatedItemStyle.sounds = getSounds();
        return animatedItemStyle;
    }

    private Map<Identifier, Animation> getLandAnimations() {
        Texture general = assets.getBaseTexture();
        Texture moveGold = assets.getMoveWithGoldTexture();
        Texture moveWood = assets.getMoveWithWoodTexture();
        Texture decompose = assets.getDecomposeTexture();
        return getLandAnimations(general, moveGold, moveWood, decompose);
    }

    private Map<Identifier, Animation> getLandAnimations(Texture general, Texture gold, Texture wood, Texture decompose) {
        AnimationSetBuilder builder = new AnimationSetBuilder();

        builder.set(UnitAnimation.IdleBasic, AnimationLayouts.idleSchema(), general);
        builder.set(UnitAnimation.IdleGold, AnimationLayouts.idleSchema(), gold);
        builder.set(UnitAnimation.IdleWood, AnimationLayouts.idleSchema(), wood);
        builder.associate(UnitAnimation.Idle, UnitAnimation.IdleBasic);

        builder.set(UnitAnimation.MoveBasic, AnimationLayouts.moveSchema(), general);
        builder.set(UnitAnimation.MoveGold, AnimationLayouts.moveSchema(), gold);
        builder.set(UnitAnimation.MoveWood, AnimationLayouts.moveSchema(), wood);
        builder.associate(UnitAnimation.Move, UnitAnimation.MoveBasic);

        builder.set(UnitAnimation.Attack, AnimationLayouts.meleeAttackSchema(), general);
        builder.set(UnitAnimation.Death, AnimationLayouts.deathSchema(), general);
        builder.set(UnitAnimation.Decompose, AnimationLayouts.decomposeSchema(), decompose);
        builder.set(UnitAnimation.GatherWood, AnimationLayouts.gatherWoodSchema(), general);

        return builder.build();
    }

    private Map<Identifier, Animation> getSeaAnimations() {
        Texture general = assets.getBaseTexture();
        Texture decompose = assets.getDecomposeTexture();
        return getSeaAnimations(general, decompose);
    }

    private Map<Identifier, Animation> getSeaAnimations(Texture general, Texture decompose) {
        AnimationSetBuilder builder = new AnimationSetBuilder();

        builder.set(UnitAnimation.Idle, AnimationLayouts.idleSchema(88, 88), general);
        builder.set(UnitAnimation.Move, AnimationLayouts.idleSchema(88, 88), general);
        builder.set(UnitAnimation.Attack, AnimationLayouts.idleSchema(88, 88), general);
        builder.set(UnitAnimation.Death, AnimationLayouts.boatDeathSchema(), general);
        builder.set(UnitAnimation.Decompose, AnimationLayouts.boatDecomposeSchema(), decompose);

        return builder.build();
    }

    private Map<Identifier, SoundEffect> getSounds() {
        Map<Identifier, SoundEffect> sounds = new HashMap<>();
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
        unitStyle.icon = assets.getIcon();
        unitStyle.selection = assets.getSelectionTexture();
        return unitStyle;
    }
}
