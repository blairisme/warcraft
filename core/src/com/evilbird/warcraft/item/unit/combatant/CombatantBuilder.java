/*
 * Copyright (c) 2019, Blair Butterworth
 *
 * This work is licensed under the MIT License. To view a copy of this
 * license, visit
 *
 *        https://opensource.org/licenses/MIT
 */

package com.evilbird.warcraft.item.unit.combatant;

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

import static com.evilbird.warcraft.item.unit.combatant.CombatantVariety.MeleeCombatant;

/**
 * Creates a new {@link Combatant} whose visual and audible presentation is
 * defined by the given {@link CombatantAssets}.
 *
 * @author Blair Butterworth
 */
public class CombatantBuilder
{
    private CombatantAssets assets;

    public CombatantBuilder(CombatantAssets assets) {
        this.assets = assets;
    }

    public Combatant newMeleeCombatant() {
        return build(CombatantVariety.MeleeCombatant);
    }

    public RangedCombatant newRangedCombatant() {
        return (RangedCombatant)build(CombatantVariety.RangedCombatant);
    }

    public RangedCombatant newSeaCombatant() {
        return (RangedCombatant)build(CombatantVariety.SeaCombatant);
    }

    public Combatant build(CombatantVariety variety) {
        Skin skin = getSkin(assets, variety);
        Combatant result = variety == MeleeCombatant ? new Combatant(skin) : new RangedCombatant(skin);
        result.setAnimation(UnitAnimation.Idle);
        result.setSelected(false);
        result.setSelectable(true);
        result.setTouchable(Touchable.enabled);
        result.setSize(assets.getSize());
        return result;
    }

    private Skin getSkin(CombatantAssets assets, CombatantVariety variety) {
        Skin skin = new Skin();
        skin.add("default", getAnimationStyle(assets, variety), ViewableStyle.class);
        skin.add("default", getUnitStyle(assets), UnitStyle.class);
        return skin;
    }

    private ViewableStyle getAnimationStyle(CombatantAssets assets, CombatantVariety variety) {
        ViewableStyle viewableStyle = new ViewableStyle();
        viewableStyle.animations = getAnimations(assets, variety);
        viewableStyle.sounds = getSounds(assets, variety);
        return viewableStyle;
    }

    private Map<Identifier, Animation> getAnimations(CombatantAssets assets, CombatantVariety variety) {
        Texture general = assets.getBaseTexture();
        Texture decompose = assets.getDecomposeTexture();

        switch(variety) {
            case MeleeCombatant: return getMeleeAnimations(general, decompose);
            case SeaCombatant: return getSeaAnimations(general, decompose);
            case RangedCombatant: return getRangedAnimations(general, decompose);
            default: throw new UnsupportedOperationException();
        }
    }

    private Map<Identifier, Animation> getMeleeAnimations(Texture general, Texture decompose) {
        AnimationSetBuilder builder = new AnimationSetBuilder();
        builder.set(UnitAnimation.Idle, AnimationLayouts.idleSchema(), general);
        builder.set(UnitAnimation.Move, AnimationLayouts.moveSchema(), general);
        builder.set(UnitAnimation.Attack, AnimationLayouts.meleeAttackSchema(), general);
        builder.set(UnitAnimation.Death, AnimationLayouts.deathSchema(), general);
        builder.set(UnitAnimation.Decompose, AnimationLayouts.decomposeSchema(), decompose);
        return builder.build();
    }

    private Map<Identifier, Animation> getSeaAnimations(Texture general, Texture decompose) {
        AnimationSetBuilder builder = new AnimationSetBuilder();
        builder.set(UnitAnimation.Idle, AnimationLayouts.idleSchema(assets.getSize()), general);
        builder.set(UnitAnimation.Move, AnimationLayouts.idleSchema(assets.getSize()), general);
        builder.set(UnitAnimation.Attack, AnimationLayouts.idleSchema(assets.getSize()), general);
        builder.set(UnitAnimation.Death, AnimationLayouts.boatDeathSchema(), general);
        builder.set(UnitAnimation.Decompose, AnimationLayouts.boatDecomposeSchema(), decompose);
        return builder.build();
    }

    private Map<Identifier, Animation> getRangedAnimations(Texture general, Texture decompose) {
        AnimationSetBuilder builder = new AnimationSetBuilder();
        builder.set(UnitAnimation.Idle, AnimationLayouts.idleSchema(), general);
        builder.set(UnitAnimation.Move, AnimationLayouts.moveSchema(), general);
        builder.set(UnitAnimation.Attack, AnimationLayouts.rangedAttackSchema(), general);
        builder.set(UnitAnimation.Death, AnimationLayouts.deathSchema(), general);
        builder.set(UnitAnimation.Decompose, AnimationLayouts.decomposeSchema(), decompose);
        return builder.build();
    }

    private Map<Identifier, Sound> getSounds(CombatantAssets assets, CombatantVariety variety) {
        switch(variety) {
            case MeleeCombatant: return getMeleeSounds(assets);
            case SeaCombatant:
            case RangedCombatant: return getRangedSounds(assets);
            default: throw new UnsupportedOperationException();
        }
    }

    private Map<Identifier, Sound> getMeleeSounds(CombatantAssets assets) {
        Map<Identifier, Sound> sounds = new HashMap<>();
        sounds.put(UnitSound.Acknowledge, assets.getAcknowledgeSound());
        sounds.put(UnitSound.Selected, assets.getSelectedSound());
        sounds.put(UnitSound.Attack, assets.getAttackSound());
        sounds.put(UnitSound.Die, assets.getDieSound());
        sounds.put(UnitSound.Ready, assets.getReadySound());
        sounds.put(UnitSound.Captured, assets.getCaptureSound());
        sounds.put(UnitSound.Rescued, assets.getRescueSound());
        return sounds;
    }

    private Map<Identifier, Sound> getRangedSounds(CombatantAssets assets) {
        Map<Identifier, Sound> sounds = getMeleeSounds(assets);
        sounds.put(UnitSound.Hit, assets.getHitSound());
        return sounds;
    }

    private UnitStyle getUnitStyle(CombatantAssets assets) {
        UnitStyle unitStyle = new UnitStyle();
        unitStyle.selection = assets.getSelectionTexture();
        return unitStyle;
    }
}
