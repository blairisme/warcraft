/*
 * Copyright (c) 2020, Blair Butterworth
 *
 * This Source Code Form is subject to the terms of the Mozilla Public
 * License, v. 2.0. If a copy of the MPL was not distributed with this
 * file, You can obtain one at http://mozilla.org/MPL/2.0/.
 */

package com.evilbird.warcraft.object.unit.combatant.siege;

import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.evilbird.engine.audio.sound.SoundCatalog;
import com.evilbird.engine.common.graphics.animation.AnimationCatalog;
import com.evilbird.warcraft.object.unit.UnitType;
import com.evilbird.warcraft.object.unit.combatant.CombatantAssets;
import com.evilbird.warcraft.object.unit.combatant.CombatantBuilder;
import com.evilbird.warcraft.object.unit.combatant.CombatantSounds;
import com.evilbird.warcraft.object.unit.combatant.RangedCombatant;

/**
 * Creates a new siege combatant whose visual and audible presentation is
 * defined by the given {@link CombatantAssets}.
 *
 * @author Blair Butterworth
 */
public class SiegeUnitBuilder extends CombatantBuilder<RangedCombatant>
{
    private SiegeUnitAssets assets;

    public SiegeUnitBuilder(SiegeUnitAssets assets, UnitType type) {
        super(assets, type);
        this.assets = assets;
    }

    @Override
    protected RangedCombatant newCombatant(Skin skin) {
        return new RangedCombatant(skin);
    }

    @Override
    protected AnimationCatalog newAnimations() {
        return new SiegeUnitAnimations(assets);
    }

    @Override
    protected SoundCatalog newSounds() {
        return new CombatantSounds(assets);
    }
}
