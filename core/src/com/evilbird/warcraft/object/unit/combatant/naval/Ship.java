/*
 * Copyright (c) 2019, Blair Butterworth
 *
 * This work is licensed under the MIT License. To view a copy of this
 * license, visit
 *
 *        https://opensource.org/licenses/MIT
 */

package com.evilbird.warcraft.object.unit.combatant.naval;

import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.evilbird.warcraft.object.common.capability.OffensiveCapability;
import com.evilbird.warcraft.object.unit.UnitStyle;
import com.evilbird.warcraft.object.unit.combatant.CombatantVessel;

import static com.evilbird.warcraft.object.common.capability.OffensiveCapability.Air;

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
        return Air;
    }
}
