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
import com.evilbird.warcraft.object.common.capability.OffensiveCapability;

import static com.evilbird.warcraft.object.common.capability.OffensiveCapability.None;

/**
 * A {@link FlyingUnit} specialization that cannot attack other units.
 *
 * @author Blair Butterworth
 */
public class FlyingScout extends FlyingUnit
{
    /**
     * Constructs a new instance of this class given a {@link Skin} describing
     * its visual and auditory presentation.
     *
     * @param skin a {@link Skin} instance containing, amongst others, a
     *             {@link FlyingUnitStyle}.
     */
    public FlyingScout(Skin skin) {
        super(skin);
    }

    /**
     * Returns the no offensive attack capability, as {@code FlyingScouts}
     * cannot attack.
     */
    @Override
    public OffensiveCapability getAttackCapability() {
        return None;
    }
}
