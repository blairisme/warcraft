/*
 * Copyright (c) 2020, Blair Butterworth
 *
 * This Source Code Form is subject to the terms of the Mozilla Public
 * License, v. 2.0. If a copy of the MPL was not distributed with this
 * file, You can obtain one at http://mozilla.org/MPL/2.0/.
 */

package com.evilbird.warcraft.object.unit.combatant.melee;

import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.evilbird.engine.audio.sound.SoundCatalog;
import com.evilbird.engine.common.graphics.animation.AnimationCatalog;
import com.evilbird.warcraft.object.unit.UnitArchetype;
import com.evilbird.warcraft.object.unit.UnitType;
import com.evilbird.warcraft.object.unit.combatant.Combatant;
import com.evilbird.warcraft.object.unit.combatant.CombatantAssets;
import com.evilbird.warcraft.object.unit.combatant.CombatantBuilder;
import com.evilbird.warcraft.object.unit.combatant.CombatantSounds;
import com.evilbird.warcraft.object.unit.combatant.ConjuredUnitSounds;
import com.evilbird.warcraft.object.unit.combatant.melee.neutral.SkeletonAnimations;

import static com.evilbird.warcraft.object.unit.UnitArchetype.ConjuredUnit;
import static com.evilbird.warcraft.object.unit.UnitArchetype.Demolition;

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
        UnitArchetype archetype = type.getArchetype();

        if (archetype == ConjuredUnit) {
            return new SkeletonAnimations(assets);
        }
        if (archetype == Demolition) {
            return new DemoUnitAnimations(assets);
        }
        return new MeleeUnitAnimations(assets);
    }

    @Override
    protected SoundCatalog newSounds() {
        UnitArchetype archetype = type.getArchetype();

        if (archetype == ConjuredUnit) {
            return new ConjuredUnitSounds(assets);
        }
        return new CombatantSounds(assets);
    }
}
