/*
 * Copyright (c) 2020, Blair Butterworth
 *
 * This Source Code Form is subject to the terms of the Mozilla Public
 * License, v. 2.0. If a copy of the MPL was not distributed with this
 * file, You can obtain one at http://mozilla.org/MPL/2.0/.
 */

package com.evilbird.warcraft.action.attack;

import com.evilbird.engine.action.Action;
import com.evilbird.warcraft.action.move.MoveWithinRangeAction;
import com.evilbird.warcraft.object.common.capability.PerishableObject;
import com.evilbird.warcraft.object.common.capability.RangedOffensiveObject;

import javax.inject.Inject;

/**
 * An {@link Action} that causes a given {@link RangedOffensiveObject} to
 * attack a {@link PerishableObject} over a distance, after first moving within
 * attack range.
 *
 * @author Blair Butterworth
 */
public class RangedAttack extends AttackSequence
{
    @Inject
    public RangedAttack(MoveWithinRangeAction move, ProjectileAttack attack) {
        super(move, attack);
    }
}
