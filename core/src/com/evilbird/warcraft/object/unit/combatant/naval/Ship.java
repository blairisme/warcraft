/*
 * Copyright (c) 2020, Blair Butterworth
 *
 * This Source Code Form is subject to the terms of the Mozilla Public
 * License, v. 2.0. If a copy of the MPL was not distributed with this
 * file, You can obtain one at http://mozilla.org/MPL/2.0/.
 */

package com.evilbird.warcraft.object.unit.combatant.naval;

import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.evilbird.warcraft.object.common.capability.OffensiveCapability;
import com.evilbird.warcraft.object.common.capability.TerrainType;
import com.evilbird.warcraft.object.unit.UnitStyle;
import com.evilbird.warcraft.object.unit.combatant.CombatantVessel;

/**
 * A {@link CombatantVessel} specialization that can traverse and attack
 * other game objects over the water.
 *
 * @author Blair Butterworth
 */
public class Ship extends CombatantVessel
{
    /**
     * Constructs a new instance of this class given a {@link Skin} describing
     * its visual and auditory presentation.
     *
     * @param skin  a {@link Skin} instance containing, amongst others, a
     *              {@link UnitStyle}.
     */
    public Ship(Skin skin) {
        super(skin);
    }

    /**
     * Returns the attack capability of the {@code Ship},
     * {@link OffensiveCapability#Air}.
     */
    @Override
    public OffensiveCapability getAttackCapability() {
        return OffensiveCapability.Air;
    }

    @Override
    public TerrainType getTerrainType() {
        return TerrainType.Water;
    }
}
