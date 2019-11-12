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
import com.evilbird.engine.common.graphics.FlashingRenderable;
import com.evilbird.engine.common.graphics.SpriteRenderable;
import com.evilbird.engine.object.specialized.ViewableStyle;
import com.evilbird.warcraft.item.unit.UnitAnimation;
import com.evilbird.warcraft.item.unit.UnitStyle;
import com.evilbird.warcraft.item.unit.UnitType;
import com.evilbird.warcraft.item.unit.combatant.animations.DaemonAnimations;
import com.evilbird.warcraft.item.unit.combatant.animations.DemolitionAnimations;
import com.evilbird.warcraft.item.unit.combatant.animations.DragonAnimations;
import com.evilbird.warcraft.item.unit.combatant.animations.EyeOfKilroggAnimations;
import com.evilbird.warcraft.item.unit.combatant.animations.FlyingMachineAnimations;
import com.evilbird.warcraft.item.unit.combatant.animations.GryphonAnimations;
import com.evilbird.warcraft.item.unit.combatant.animations.MeleeAnimations;
import com.evilbird.warcraft.item.unit.combatant.animations.NavalAnimations;
import com.evilbird.warcraft.item.unit.combatant.animations.RangedAnimations;
import com.evilbird.warcraft.item.unit.combatant.animations.SiegeAnimations;
import com.evilbird.warcraft.item.unit.combatant.animations.SpellCasterAnimations;
import com.evilbird.warcraft.item.unit.combatant.animations.SubmarineAnimations;
import com.evilbird.warcraft.item.unit.combatant.animations.ZeppelinAnimations;
import com.evilbird.warcraft.item.unit.combatant.sounds.ConjuredSounds;
import com.evilbird.warcraft.item.unit.combatant.sounds.MeleeSounds;
import com.evilbird.warcraft.item.unit.combatant.sounds.RangedSounds;

import java.util.Collections;
import java.util.Map;
import java.util.Random;

import static com.evilbird.warcraft.item.unit.UnitType.Daemon;
import static com.evilbird.warcraft.item.unit.UnitType.Dragon;
import static com.evilbird.warcraft.item.unit.UnitType.EyeOfKilrogg;
import static com.evilbird.warcraft.item.unit.UnitType.GnomishFlyingMachine;
import static com.evilbird.warcraft.item.unit.UnitType.GoblinZeppelin;
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
        setCommonAttributes(result);
        return result;
    }

    public RangedCombatant newRangedCombatant() {
        RangedCombatant result = new RangedCombatant(getSkin());
        result.setAnimation(UnitAnimation.Idle);
        setCommonAttributes(result);
        return result;
    }

    public SpellCaster newSpellCaster() {
        SpellCaster result = new SpellCaster(getSkin());
        result.setAnimation(UnitAnimation.Idle);
        setCommonAttributes(result);
        return result;
    }

    public Submarine newSubmarine() {
        Submarine result = new Submarine(getSkin());
        result.setAnimation(UnitAnimation.Idle);
        setCommonAttributes(result);
        return result;
    }

    private void setCommonAttributes(Combatant combatant) {
        combatant.setSelected(false);
        combatant.setSelectable(true);
        combatant.setTouchable(Touchable.enabled);
        combatant.setSize(assets.getSize());
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
        style.selection = new SpriteRenderable(assets.getSelectionTexture());
        style.highlight = new FlashingRenderable(assets.getHighlightTexture());
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
        } else if (type.isSpellCaster()) {
            return new SpellCasterAnimations(assets);
        } else if (type.isRanged()) {
            return new RangedAnimations(assets);
        } else if (type.isNavalUnit()) {
            return new NavalAnimations(assets);
        } else if (type.isSiege()) {
            return new SiegeAnimations(assets);
        } else if (type.isSubmarine()) {
            return new SubmarineAnimations(assets);
        } else if (type.isDemoTeam()) {
            return new DemolitionAnimations(assets);
        }
        return new MeleeAnimations(assets);
    }

    private AnimationCatalog newCustomAnimations() {
        if (type == Daemon) {
            return new DaemonAnimations(assets);
        } else if (type == Dragon) {
            return new DragonAnimations(assets);
        } else if (type == EyeOfKilrogg) {
            return new EyeOfKilroggAnimations(assets);
        } else if (type == GryphonRider) {
            return new GryphonAnimations(assets);
        } else if (type == GnomishFlyingMachine) {
            return new FlyingMachineAnimations(assets);
        } else if (type == GoblinZeppelin) {
            return new ZeppelinAnimations(assets);
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
        if (type.isRanged() || type.isNavalUnit() || type.isSiege()) {
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
