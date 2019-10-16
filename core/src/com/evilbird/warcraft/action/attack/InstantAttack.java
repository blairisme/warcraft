/*
 * Copyright (c) 2019, Blair Butterworth
 *
 * This work is licensed under the MIT License. To view a copy of this
 * license, visit
 *
 *        https://opensource.org/licenses/MIT
 */

package com.evilbird.warcraft.action.attack;

import com.evilbird.warcraft.common.WarcraftPreferences;

import javax.inject.Inject;

/**
 * A {@link ProximityAttack} that only attacks once.
 *
 * @author Blair Butterworth
 */
public class InstantAttack extends ProximityAttack
{
    private transient boolean complete;

    @Inject
    public InstantAttack(WarcraftPreferences preferences) {
        super(preferences);
        complete = false;
    }

    public boolean isComplete() {
        return complete;
    }

    @Override
    protected boolean attackTarget() {
        super.attackTarget();
        complete = true;
        return true;
    }

    @Override
    public void reset() {
        super.reset();
        complete = false;
    }

    @Override
    public void restart() {
        super.restart();
        complete = false;
    }
}

