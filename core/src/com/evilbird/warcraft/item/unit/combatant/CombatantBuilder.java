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
import com.evilbird.engine.common.audio.SoundEffect;
import com.evilbird.engine.common.graphics.Animation;
import com.evilbird.engine.common.lang.Identifier;
import com.evilbird.engine.item.specialized.AnimatedItemStyle;
import com.evilbird.warcraft.item.common.animation.AnimationSchemas;
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
        skin.add("default", getAnimationStyle(assets, variety), AnimatedItemStyle.class);
        skin.add("default", getUnitStyle(assets), UnitStyle.class);
        return skin;
    }

    private AnimatedItemStyle getAnimationStyle(CombatantAssets assets, CombatantVariety variety) {
        AnimatedItemStyle animatedItemStyle = new AnimatedItemStyle();
        animatedItemStyle.animations = getAnimations(assets, variety);
        animatedItemStyle.sounds = getSounds(assets, variety);
        return animatedItemStyle;
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
        builder.set(UnitAnimation.Idle, AnimationSchemas.idleSchema(), general);
        builder.set(UnitAnimation.Move, AnimationSchemas.moveSchema(), general);
        builder.set(UnitAnimation.Attack, AnimationSchemas.meleeAttackSchema(), general);
        builder.set(UnitAnimation.Death, AnimationSchemas.deathSchema(), general);
        builder.set(UnitAnimation.Decompose, AnimationSchemas.decomposeSchema(), decompose);
        return builder.build();
    }

    private Map<Identifier, Animation> getSeaAnimations(Texture general, Texture decompose) {
        AnimationSetBuilder builder = new AnimationSetBuilder();
        builder.set(UnitAnimation.Idle, AnimationSchemas.idleSchema(88, 88), general);
        builder.set(UnitAnimation.Move, AnimationSchemas.idleSchema(88, 88), general);
        builder.set(UnitAnimation.Attack, AnimationSchemas.idleSchema(88, 88), general);
        builder.set(UnitAnimation.Death, AnimationSchemas.boatDeathSchema(), general);
        builder.set(UnitAnimation.Decompose, AnimationSchemas.boatDecomposeSchema(), decompose);
        return builder.build();
    }

    private Map<Identifier, Animation> getRangedAnimations(Texture general, Texture decompose) {
        AnimationSetBuilder builder = new AnimationSetBuilder();
        builder.set(UnitAnimation.Idle, AnimationSchemas.idleSchema(), general);
        builder.set(UnitAnimation.Move, AnimationSchemas.moveSchema(), general);
        builder.set(UnitAnimation.Attack, AnimationSchemas.rangedAttackSchema(), general);
        builder.set(UnitAnimation.Death, AnimationSchemas.deathSchema(), general);
        builder.set(UnitAnimation.Decompose, AnimationSchemas.decomposeSchema(), decompose);
        return builder.build();
    }

    private Map<Identifier, SoundEffect> getSounds(CombatantAssets assets, CombatantVariety variety) {
        switch(variety) {
            case MeleeCombatant: return getMeleeSounds(assets);
            case SeaCombatant:
            case RangedCombatant: return getRangedSounds(assets);
            default: throw new UnsupportedOperationException();
        }
    }

    private Map<Identifier, SoundEffect> getMeleeSounds(CombatantAssets assets) {
        Map<Identifier, SoundEffect> sounds = new HashMap<>();
        sounds.put(UnitSound.Acknowledge, assets.getAcknowledgeSound());
        sounds.put(UnitSound.Selected, assets.getSelectedSound());
        sounds.put(UnitSound.Attack, assets.getAttackSound());
        sounds.put(UnitSound.Die, assets.getDieSound());
        sounds.put(UnitSound.Ready, assets.getReadySound());
        sounds.put(UnitSound.Captured, assets.getCaptureSound());
        sounds.put(UnitSound.Rescued, assets.getRescueSound());
        return sounds;
    }

    private Map<Identifier, SoundEffect> getRangedSounds(CombatantAssets assets) {
        Map<Identifier, SoundEffect> sounds = getMeleeSounds(assets);
        sounds.put(UnitSound.Hit, assets.getHitSound());
        return sounds;
    }

    private UnitStyle getUnitStyle(CombatantAssets assets) {
        UnitStyle unitStyle = new UnitStyle();
        unitStyle.icon = assets.getIcon();
        unitStyle.selection = assets.getSelectionTexture();
        return unitStyle;
    }
}
