/*
 * Copyright (c) 2020, Blair Butterworth
 *
 * This Source Code Form is subject to the terms of the Mozilla Public
 * License, v. 2.0. If a copy of the MPL was not distributed with this
 * file, You can obtain one at http://mozilla.org/MPL/2.0/.
 */

package com.evilbird.warcraft.object.unit.combatant.naval;

import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.evilbird.engine.audio.sound.SoundCatalog;
import com.evilbird.engine.common.graphics.animation.AnimationCatalog;
import com.evilbird.warcraft.object.unit.UnitArchetype;
import com.evilbird.warcraft.object.unit.UnitType;
import com.evilbird.warcraft.object.unit.combatant.CombatantAssets;
import com.evilbird.warcraft.object.unit.combatant.CombatantBuilder;
import com.evilbird.warcraft.object.unit.combatant.CombatantSounds;
import com.evilbird.warcraft.object.unit.combatant.RangedCombatant;
import com.evilbird.warcraft.object.unit.combatant.naval.animations.ShipAnimations;
import com.evilbird.warcraft.object.unit.combatant.naval.animations.SubmarineAnimations;

/**
 * Creates a new naval combatants whose visual and audible presentation is
 * defined by the given {@link CombatantAssets}.
 *
 * @author Blair Butterworth
 */
public class NavalUnitBuilder extends CombatantBuilder<RangedCombatant>
{
    private NavalUnitAssets assets;
    private UnitArchetype archetype;

    public NavalUnitBuilder(NavalUnitAssets assets, UnitType type) {
        super(assets, type);
        this.assets = assets;
        this.archetype = type.getArchetype();
    }

    @Override
    protected RangedCombatant newCombatant(Skin skin) {
        if (archetype == UnitArchetype.Submarine) {
            return new Submarine(skin);
        }
        if (archetype == UnitArchetype.Transportation) {
            return new Transport(skin);
        }
        return new Ship(skin);
    }

    @Override
    protected AnimationCatalog newAnimations() {
        return archetype == UnitArchetype.Submarine ? new SubmarineAnimations(assets) : new ShipAnimations(assets);
    }

    @Override
    protected SoundCatalog newSounds() {
        return new CombatantSounds(assets);
    }
}
