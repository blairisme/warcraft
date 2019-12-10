/*
 * Copyright (c) 2019, Blair Butterworth
 *
 * This work is licensed under the MIT License. To view a copy of this
 * license, visit
 *
 *        https://opensource.org/licenses/MIT
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
