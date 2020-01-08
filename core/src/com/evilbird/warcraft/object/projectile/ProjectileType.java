/*
 * Copyright (c) 2020, Blair Butterworth
 *
 * This Source Code Form is subject to the terms of the Mozilla Public
 * License, v. 2.0. If a copy of the MPL was not distributed with this
 * file, You can obtain one at http://mozilla.org/MPL/2.0/.
 */

package com.evilbird.warcraft.object.projectile;

import com.evilbird.engine.common.collection.EnumUtils;
import com.evilbird.engine.object.GameObjectType;

/**
 * Defines identifiers for projectile varieties.
 *
 * @author Blair Butterworth
 */
public enum ProjectileType implements GameObjectType
{
    Arrow,
    Axe,
    Bolt,
    Cannonball,
    DaemonFire,
    Fireball,
    FlamingCannonball,
    FlamingRock,
    GryphonHammer,
    Lightning,
    Torpedo,
    TouchOfDeath;

    public boolean isExplosive() {
        return EnumUtils.isBetween(this, Bolt, TouchOfDeath);
    }
}
