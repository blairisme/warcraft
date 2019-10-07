/*
 * Copyright (c) 2019, Blair Butterworth
 *
 * This work is licensed under the MIT License. To view a copy of this
 * license, visit
 *
 *        https://opensource.org/licenses/MIT
 */

package com.evilbird.warcraft.item.unit.combatant;

import com.badlogic.gdx.scenes.scene2d.Touchable;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.evilbird.engine.common.audio.sound.SoundCatalog;
import com.evilbird.engine.common.graphics.AnimationCatalog;
import com.evilbird.engine.item.specialized.ViewableStyle;
import com.evilbird.warcraft.item.unit.UnitAnimation;
import com.evilbird.warcraft.item.unit.UnitStyle;
import com.evilbird.warcraft.item.unit.combatant.animations.FlyingAnimations;
import com.evilbird.warcraft.item.unit.combatant.animations.MeleeAnimations;
import com.evilbird.warcraft.item.unit.combatant.animations.NavalAnimations;
import com.evilbird.warcraft.item.unit.combatant.animations.RangedAnimations;
import com.evilbird.warcraft.item.unit.combatant.animations.ScoutAnimations;
import com.evilbird.warcraft.item.unit.combatant.animations.SiegeAnimations;
import com.evilbird.warcraft.item.unit.combatant.animations.SubmarineAnimations;
import com.evilbird.warcraft.item.unit.combatant.sounds.MeleeSounds;
import com.evilbird.warcraft.item.unit.combatant.sounds.RangedSounds;

/**
 * Creates a new {@link Combatant} whose visual and audible presentation is
 * defined by the given {@link CombatantAssets}.
 *
 * @author Blair Butterworth
 */
public class CombatantBuilder
{
    private CombatantAssets assets;
    private CombatantVariety type;
    private SoundCatalog sounds;
    private AnimationCatalog animations;

    public CombatantBuilder(CombatantAssets assets, CombatantVariety type) {
        this.assets = assets;
        this.type = type;
        this.sounds = null;
        this.animations = null;
    }

    public Combatant newMeleeCombatant() {
        Combatant result = new Combatant(getSkin());
        result.setAnimation(UnitAnimation.Idle);
        result.setSelected(false);
        result.setSelectable(true);
        result.setTouchable(Touchable.enabled);
        result.setSize(assets.getSize());
        return result;
    }

    public RangedCombatant newRangedCombatant() {
        RangedCombatant result = new RangedCombatant(getSkin());
        result.setAnimation(UnitAnimation.Idle);
        result.setSelected(false);
        result.setSelectable(true);
        result.setTouchable(Touchable.enabled);
        result.setSize(assets.getSize());
        return result;
    }

    private Skin getSkin() {
        Skin skin = new Skin();
        skin.add("default", getAnimationStyle(), ViewableStyle.class);
        skin.add("default", getUnitStyle(), UnitStyle.class);
        return skin;
    }

    private ViewableStyle getAnimationStyle() {
        SoundCatalog sounds = getSounds();
        AnimationCatalog animations = getAnimations();

        ViewableStyle viewableStyle = new ViewableStyle();
        viewableStyle.animations = animations.get();
        viewableStyle.sounds = sounds.get();
        return viewableStyle;
    }

    private UnitStyle getUnitStyle() {
        UnitStyle unitStyle = new UnitStyle();
        unitStyle.selection = assets.getSelectionTexture();
        return unitStyle;
    }

    private AnimationCatalog getAnimations() {
        if (animations == null) {
            animations = newAnimations(assets, type);
        }
        return animations;
    }

    private AnimationCatalog newAnimations(CombatantAssets assets, CombatantVariety variety) {
        switch(variety) {
            case FlyingCombatant: return new FlyingAnimations(assets);
            case MeleeCombatant: return new MeleeAnimations(assets);
            case NavalCombatant: return new NavalAnimations(assets);
            case RangedCombatant: return new RangedAnimations(assets);
            case ScoutCombatant: return new ScoutAnimations(assets);
            case SiegeCombatant: return new SiegeAnimations(assets);
            case SubmarineCombatant: return new SubmarineAnimations(assets);
            default: throw new UnsupportedOperationException(variety.name());
        }
    }

    private SoundCatalog getSounds() {
        if (sounds == null) {
            sounds = newSounds(assets, type);
        }
        return sounds;
    }

    private SoundCatalog newSounds(CombatantAssets assets, CombatantVariety variety) {
        switch(variety) {
            case FlyingCombatant:
            case ScoutCombatant:
            case MeleeCombatant: return new MeleeSounds(assets);
            case NavalCombatant:
            case SiegeCombatant:
            case SubmarineCombatant:
            case RangedCombatant: return new RangedSounds(assets);
            default: throw new UnsupportedOperationException(variety.name());
        }
    }
}
