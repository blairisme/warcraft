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
import com.evilbird.engine.common.audio.sound.SoundCatalog;
import com.evilbird.engine.common.collection.Maps;
import com.evilbird.engine.common.graphics.AnimationCatalog;
import com.evilbird.engine.item.specialized.ViewableStyle;
import com.evilbird.warcraft.item.unit.UnitAnimation;
import com.evilbird.warcraft.item.unit.UnitStyle;
import com.evilbird.warcraft.item.unit.UnitType;
import com.evilbird.warcraft.item.unit.combatant.animations.DaemonAnimations;
import com.evilbird.warcraft.item.unit.combatant.animations.DemolitionAnimations;
import com.evilbird.warcraft.item.unit.combatant.animations.DragonAnimations;
import com.evilbird.warcraft.item.unit.combatant.animations.EyeOfKilroggAnimations;
import com.evilbird.warcraft.item.unit.combatant.animations.GryphonAnimations;
import com.evilbird.warcraft.item.unit.combatant.animations.MeleeAnimations;
import com.evilbird.warcraft.item.unit.combatant.animations.NavalAnimations;
import com.evilbird.warcraft.item.unit.combatant.animations.RangedAnimations;
import com.evilbird.warcraft.item.unit.combatant.animations.ScoutAnimations;
import com.evilbird.warcraft.item.unit.combatant.animations.SiegeAnimations;
import com.evilbird.warcraft.item.unit.combatant.animations.SpellCasterAnimations;
import com.evilbird.warcraft.item.unit.combatant.animations.SubmarineAnimations;
import com.evilbird.warcraft.item.unit.combatant.sounds.ConjuredSounds;
import com.evilbird.warcraft.item.unit.combatant.sounds.MeleeSounds;
import com.evilbird.warcraft.item.unit.combatant.sounds.RangedSounds;

import java.util.Collections;
import java.util.Map;
import java.util.Random;

import static com.evilbird.warcraft.item.unit.UnitType.Daemon;
import static com.evilbird.warcraft.item.unit.UnitType.Dragon;
import static com.evilbird.warcraft.item.unit.UnitType.EyeOfKilrogg;
import static com.evilbird.warcraft.item.unit.UnitType.GryphonRider;

/**
 * Creates a new {@link Combatant} whose visual and audible presentation is
 * defined by the given {@link CombatantAssets}.
 *
 * @author Blair Butterworth
 */
public class CombatantBuilder
{
    private Random random;
    private UnitType type;
    private CombatantAssets assets;
    private SoundCatalog sounds;
    private AnimationCatalog animations;

    public CombatantBuilder(CombatantAssets assets, UnitType type) {
        this.assets = assets;
        this.type = type;
        this.sounds = null;
        this.animations = null;
        this.random = new Random();
    }

    public Combatant newMeleeCombatant() {
        Combatant result = new Combatant(getSkin());
        result.setAnimation(UnitAnimation.Idle, random.nextFloat());
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
        UnitStyle style = getStyle();
        Skin skin = new Skin();
        skin.add("default", style, ViewableStyle.class);
        skin.add("default", style, UnitStyle.class);
        return skin;
    }

    private UnitStyle getStyle() {
        SoundCatalog sounds = getSounds();
        AnimationCatalog animations = getAnimations();

        UnitStyle style = new UnitStyle();
        style.animations = animations.get();
        style.sounds = sounds.get();
        style.selection = assets.getSelectionTexture();
        style.masks = getMasks();
        return style;
    }

    private AnimationCatalog getAnimations() {
        if (animations == null) {
            animations = newAnimations();
        }
        return animations;
    }

    private AnimationCatalog newAnimations() {
        AnimationCatalog customAnimations = newCustomAnimations();

        if (customAnimations != null) {
            return customAnimations;
        }
        if (type.isSpellCaster()) {
            return new SpellCasterAnimations(assets);
        }
        if (type.isScout()) {
            return new ScoutAnimations(assets);
        }
        if (type.isRanged()) {
            return new RangedAnimations(assets);
        }
        if (type.isNaval()) {
            return new NavalAnimations(assets);
        }
        if (type.isSiege()) {
            return new SiegeAnimations(assets);
        }
        if (type.isSubmarine()) {
            return new SubmarineAnimations(assets);
        }
        if (type.isDemoTeam()) {
            return new DemolitionAnimations(assets);
        }
        return new MeleeAnimations(assets);
    }

    private AnimationCatalog newCustomAnimations() {
        if (type == Daemon) {
            return new DaemonAnimations(assets);
        }
        if (type == Dragon) {
            return new DragonAnimations(assets);
        }
        if (type == EyeOfKilrogg) {
            return new EyeOfKilroggAnimations(assets);
        }
        if (type == GryphonRider) {
            return new GryphonAnimations(assets);
        }
        return null;
    }

    private SoundCatalog getSounds() {
        if (sounds == null) {
            sounds = newSounds();
        }
        return sounds;
    }

    private SoundCatalog newSounds() {
        if (type.isConjured()) {
            return new ConjuredSounds(assets);
        }
        if (type.isRanged() || type.isNaval() || type.isSiege()) {
            return new RangedSounds(assets);
        }
        return new MeleeSounds(assets);
    }

    private Map<Texture, Texture> getMasks() {
        if (! type.isNeutral()) {
            return Maps.of(assets.getBaseTexture(), assets.getMaskTexture());
        }
        return Collections.emptyMap();
    }
}
