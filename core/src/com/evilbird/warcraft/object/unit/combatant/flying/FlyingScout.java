/*
 * Copyright (c) 2020, Blair Butterworth
 *
 * This Source Code Form is subject to the terms of the Mozilla Public
 * License, v. 2.0. If a copy of the MPL was not distributed with this
 * file, You can obtain one at http://mozilla.org/MPL/2.0/.
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
     * Returns no offensive attack capability, as {@code FlyingScouts}
     * cannot attack.
     */
    @Override
    public OffensiveCapability getAttackCapability() {
        return None;
    }
}
