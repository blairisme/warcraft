/*
 * Copyright (c) 2020, Blair Butterworth
 *
 * This Source Code Form is subject to the terms of the Mozilla Public
 * License, v. 2.0. If a copy of the MPL was not distributed with this
 * file, You can obtain one at http://mozilla.org/MPL/2.0/.
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
