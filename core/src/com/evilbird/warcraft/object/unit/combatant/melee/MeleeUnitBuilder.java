/*
 * Copyright (c) 2019, Blair Butterworth
 *
 * This work is licensed under the MIT License. To view a copy of this
 * license, visit
 *
 *        https://opensource.org/licenses/MIT
 */

package com.evilbird.warcraft.object.unit.combatant.melee;

import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.evilbird.engine.common.audio.sound.SoundCatalog;
import com.evilbird.engine.common.graphics.animation.AnimationCatalog;
import com.evilbird.warcraft.object.unit.UnitType;
import com.evilbird.warcraft.object.unit.combatant.Combatant;
import com.evilbird.warcraft.object.unit.combatant.CombatantAssets;
import com.evilbird.warcraft.object.unit.combatant.CombatantBuilder;
import com.evilbird.warcraft.object.unit.combatant.CombatantSounds;
import com.evilbird.warcraft.object.unit.combatant.ConjuredUnitSounds;
import com.evilbird.warcraft.object.unit.combatant.melee.neutral.SkeletonAnimations;

/**
 * Creates a new melee units whose visual and audible presentation is defined
 * by the given {@link CombatantAssets}.
 *
 * @author Blair Butterworth
 */
public class MeleeUnitBuilder extends CombatantBuilder<Combatant>
{
    private UnitType type;
    private MeleeUnitAssets assets;

    public MeleeUnitBuilder(MeleeUnitAssets assets, UnitType type) {
        super(assets, type);
        this.assets = assets;
        this.type = type;
    }

    @Override
    protected Combatant newCombatant(Skin skin) {
        return new Combatant(skin);
    }

    @Override
    protected AnimationCatalog newAnimations() {
        if (type.isConjuredUnit()) {
            return new SkeletonAnimations(assets);
        }
        if (type.isDemoTeam()) {
            return new DemoUnitAnimations(assets);
        }
        return new MeleeUnitAnimations(assets);
    }

    @Override
    protected SoundCatalog newSounds() {
        if (type.isConjuredUnit()) {
            return new ConjuredUnitSounds(assets);
        }
        return new CombatantSounds(assets);
    }
}
