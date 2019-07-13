/*
 * Copyright (c) 2019, Blair Butterworth
 *
 * This work is licensed under the MIT License. To view a copy of this
 * license, visit
 *
 *        https://opensource.org/licenses/MIT
 */

package com.evilbird.warcraft.common;

import com.evilbird.engine.game.GameContext;
import com.evilbird.warcraft.item.unit.UnitFaction;

/**
 * @author Blair Butterworth
 */
public class WarcraftGameContext implements GameContext
{
    public UnitFaction getFaction() {
        return null;
    }

    public GraphicalContext getGraphicalContext() {
        return null;
    }
}
