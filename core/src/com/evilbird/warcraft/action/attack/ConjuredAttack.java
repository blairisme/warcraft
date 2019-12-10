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
import com.evilbird.engine.action.framework.EmptyAction;
import com.evilbird.warcraft.object.common.capability.OffensiveObject;
import com.evilbird.warcraft.object.common.capability.PerishableObject;
import com.evilbird.warcraft.object.unit.conjured.ConjuredObject;

import javax.inject.Inject;

/**
 * An {@link Action} that causes a given {@link ConjuredObject} to attack a
 * {@link PerishableObject}.
 *
 * @author Blair Butterworth
 */
public class ConjuredAttack extends AttackSequence
{
    @Inject
    public ConjuredAttack(InstantAttack attack) {
        super(new EmptyAction(), attack);
    }

    @Override
    protected boolean moveRequired(OffensiveObject attacker, PerishableObject target) {
        return false;
    }
}
