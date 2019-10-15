/*
 * Copyright (c) 2019, Blair Butterworth
 *
 * This work is licensed under the MIT License. To view a copy of this
 * license, visit
 *
 *        https://opensource.org/licenses/MIT
 */

package com.evilbird.warcraft.item.unit.combatant;

import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.evilbird.engine.item.specialized.ViewableStyle;
import com.evilbird.warcraft.item.common.state.OffensiveCapability;

import static com.evilbird.warcraft.item.common.state.OffensiveCapability.Water;

/**
 * Represents a ranged combatant specialization that can traverse and attack
 * other game objects under the water.
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
     *             {@link ViewableStyle}.
     */
    public Submarine(Skin skin) {
        super(skin);
    }

    /**
     * Returns the attack capability of the {@code RangedCombatant}.
     */
    @Override
    public OffensiveCapability getAttackCapability() {
        return Water;
    }
}
