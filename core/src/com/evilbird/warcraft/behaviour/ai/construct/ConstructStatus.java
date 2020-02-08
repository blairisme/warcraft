/*
 * Copyright (c) 2020, Blair Butterworth
 *
 * This Source Code Form is subject to the terms of the Mozilla Public
 * License, v. 2.0. If a copy of the MPL was not distributed with this
 * file, You can obtain one at http://mozilla.org/MPL/2.0/.
 */

package com.evilbird.warcraft.behaviour.ai.construct;

import com.evilbird.engine.object.GameObject;
import com.evilbird.warcraft.object.unit.combatant.gatherer.Gatherer;

/**
 * Provides utility functions for determining if game objects represent
 * builders or buildings that can be used by the player assigned the construct
 * behaviour.
 *
 * @author Blair Butterworth
 */
public class ConstructStatus
{
    private ConstructStatus() {
    }

    public static boolean validBuilder(GameObject gameObject) {
        if (gameObject instanceof Gatherer) {
            Gatherer gatherer = (Gatherer)gameObject;
            return gatherer.isAlive() && gatherer.getVisible();
        }
        return false;
    }
}
