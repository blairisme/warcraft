/*
 * Copyright (c) 2020, Blair Butterworth
 *
 * This Source Code Form is subject to the terms of the Mozilla Public
 * License, v. 2.0. If a copy of the MPL was not distributed with this
 * file, You can obtain one at http://mozilla.org/MPL/2.0/.
 */

package com.evilbird.warcraft.object.unit.combatant.naval;

import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.evilbird.engine.object.AnimatedObjectStyle;
import com.evilbird.warcraft.object.common.capability.OffensiveCapability;
import com.evilbird.warcraft.object.common.capability.TerrainType;
import com.evilbird.warcraft.object.unit.combatant.RangedCombatant;

/**
 * A {@link RangedCombatant} specialization that can traverse and attack
 * other game objects under water.
 *
 * @author Blair Butterworth
 */
public class Submarine extends RangedCombatant
{
    /**
     * Constructs a new instance of this class given a {@link Skin} describing
     * its visual and auditory presentation.
     *
     * @param skin a {@link Skin} instance containing, amongst others, a
     *             {@link AnimatedObjectStyle}.
     */
    public Submarine(Skin skin) {
        super(skin);
    }

    /**
     * Returns the attack capability of the {@code RangedCombatant}.
     */
    @Override
    public OffensiveCapability getAttackCapability() {
        return OffensiveCapability.Water;
    }

    @Override
    public TerrainType getTerrainType() {
        return TerrainType.Water;
    }
}
