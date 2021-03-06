/*
 * Copyright (c) 2020, Blair Butterworth
 *
 * This Source Code Form is subject to the terms of the Mozilla Public
 * License, v. 2.0. If a copy of the MPL was not distributed with this
 * file, You can obtain one at http://mozilla.org/MPL/2.0/.
 */

package com.evilbird.warcraft.object.unit.combatant.flying;

import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.evilbird.engine.audio.sound.SoundCatalog;
import com.evilbird.engine.common.graphics.animation.AnimationCatalog;
import com.evilbird.engine.common.graphics.renderable.TextureRenderable;
import com.evilbird.warcraft.object.unit.UnitArchetype;
import com.evilbird.warcraft.object.unit.UnitAttack;
import com.evilbird.warcraft.object.unit.UnitStyle;
import com.evilbird.warcraft.object.unit.UnitType;
import com.evilbird.warcraft.object.unit.combatant.CombatantAssets;
import com.evilbird.warcraft.object.unit.combatant.CombatantBuilder;
import com.evilbird.warcraft.object.unit.combatant.CombatantSounds;
import com.evilbird.warcraft.object.unit.combatant.ConjuredUnitSounds;
import com.evilbird.warcraft.object.unit.combatant.flying.human.GnomishFlyingMachineAnimations;
import com.evilbird.warcraft.object.unit.combatant.flying.human.GryphonAnimations;
import com.evilbird.warcraft.object.unit.combatant.flying.neutral.DaemonAnimations;
import com.evilbird.warcraft.object.unit.combatant.flying.neutral.EyeOfKilroggAnimations;
import com.evilbird.warcraft.object.unit.combatant.flying.orc.DragonAnimations;
import com.evilbird.warcraft.object.unit.combatant.flying.orc.GoblinZeppelinAnimations;

import java.util.Random;

import static com.evilbird.warcraft.object.unit.UnitAnimation.Idle;
import static com.evilbird.warcraft.object.unit.UnitArchetype.ConjuredUnit;
import static com.evilbird.warcraft.object.unit.UnitAttack.None;
import static com.evilbird.warcraft.object.unit.UnitZIndex.FlyingIndex;

/**
 * Creates a new {@link FlyingUnit FlyingUnits} whose visual and audible
 * presentation is defined by the given {@link CombatantAssets}.
 *
 * @author Blair Butterworth
 */
public class FlyingUnitBuilder extends CombatantBuilder<FlyingUnit>
{
    private Random random;
    private UnitType type;
    private FlyingUnitAssets assets;

    public FlyingUnitBuilder(FlyingUnitAssets assets, UnitType type) {
        super(assets, type);
        this.assets = assets;
        this.type = type;
        this.random = new Random();
    }

    @Override
    public FlyingUnit build() {
        FlyingUnit result = super.build();
        result.setAnimation(Idle, random.nextFloat());
        result.setZIndex(FlyingIndex);
        return result;
    }

    @Override
    protected Skin getSkin() {
        Skin skin = super.getSkin();
        skin.add("default", skin.get(UnitStyle.class), FlyingUnitStyle.class);
        return skin;
    }

    @Override
    protected UnitStyle newStyle(AnimationCatalog animations, SoundCatalog sounds) {
        FlyingUnitStyle style = new FlyingUnitStyle(super.newStyle(animations, sounds));
        style.shadow = new TextureRenderable(assets.getShadowTexture());
        return style;
    }

    @Override
    protected FlyingUnit newCombatant(Skin skin) {
        UnitAttack attack = type.getAttack();
        return attack == None ? new FlyingScout(skin) : new FlyingUnit(skin);
    }

    @Override
    protected AnimationCatalog newAnimations() {
        switch (type) {
            case Daemon: return new DaemonAnimations(assets);
            case Dragon: return new DragonAnimations(assets);
            case EyeOfKilrogg: return new EyeOfKilroggAnimations(assets);
            case GryphonRider: return new GryphonAnimations(assets);
            case GnomishFlyingMachine: return new GnomishFlyingMachineAnimations(assets);
            case GoblinZeppelin: return new GoblinZeppelinAnimations(assets);
            default: throw new UnsupportedOperationException();
        }
    }

    @Override
    protected SoundCatalog newSounds() {
        UnitArchetype archetype = type.getArchetype();
        return archetype == ConjuredUnit ? new ConjuredUnitSounds(assets) : new CombatantSounds(assets);
    }
}
