/*
 * Copyright (c) 2019, Blair Butterworth
 *
 * This work is licensed under the MIT License. To view a copy of this
 * license, visit
 *
 *        https://opensource.org/licenses/MIT
 */

package com.evilbird.warcraft.object.unit.combatant.flying;

import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.evilbird.engine.common.audio.sound.SoundCatalog;
import com.evilbird.engine.common.graphics.animation.AnimationCatalog;
import com.evilbird.warcraft.object.unit.UnitAnimation;
import com.evilbird.warcraft.object.unit.UnitStyle;
import com.evilbird.warcraft.object.unit.UnitType;
import com.evilbird.warcraft.object.unit.combatant.CombatantAssets;
import com.evilbird.warcraft.object.unit.combatant.CombatantBuilder;
import com.evilbird.warcraft.object.unit.combatant.CombatantSounds;
import com.evilbird.warcraft.object.unit.combatant.ConjuredUnitSounds;
import com.evilbird.warcraft.object.unit.combatant.flying.human.FlyingMachineAnimations;
import com.evilbird.warcraft.object.unit.combatant.flying.human.GryphonAnimations;
import com.evilbird.warcraft.object.unit.combatant.flying.neutral.DaemonAnimations;
import com.evilbird.warcraft.object.unit.combatant.flying.neutral.EyeOfKilroggAnimations;
import com.evilbird.warcraft.object.unit.combatant.flying.orc.DragonAnimations;
import com.evilbird.warcraft.object.unit.combatant.flying.orc.GoblinZeppelinAnimations;

import java.util.Random;

import static com.evilbird.engine.common.graphics.renderable.EmptyRenderable.BlankRenderable;

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
        result.setAnimation(UnitAnimation.Idle, random.nextFloat());
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
        style.shadow = BlankRenderable;
        return style;
    }

    @Override
    protected FlyingUnit newCombatant(Skin skin) {
        return new FlyingUnit(skin);
    }

    @Override
    protected AnimationCatalog newAnimations() {
        switch (type) {
            case Daemon: return new DaemonAnimations(assets);
            case Dragon: return new DragonAnimations(assets);
            case EyeOfKilrogg: return new EyeOfKilroggAnimations(assets);
            case GryphonRider: return new GryphonAnimations(assets);
            case GnomishFlyingMachine: return new FlyingMachineAnimations(assets);
            case GoblinZeppelin: return new GoblinZeppelinAnimations(assets);
            default: throw new UnsupportedOperationException();
        }
    }

    @Override
    protected SoundCatalog newSounds() {
        return type.isConjured() ? new ConjuredUnitSounds(assets) : new CombatantSounds(assets);
    }
}
