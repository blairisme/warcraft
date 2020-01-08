/*
 * Copyright (c) 2020, Blair Butterworth
 *
 * This Source Code Form is subject to the terms of the Mozilla Public
 * License, v. 2.0. If a copy of the MPL was not distributed with this
 * file, You can obtain one at http://mozilla.org/MPL/2.0/.
 */

package com.evilbird.warcraft.object.common.capability;

/**
 * Defines options for offensive capability: an offensive objects ability to
 * attack others.
 *
 * @author Blair Butterworth
 */
public enum OffensiveCapability
{
    /**
     * Indicates that an object has no offensive capability.
     */
    None,

    /**
     * Indicates an ability to attack in close proximity. E.g., swords.
     */
    Proximity,

    /**
     * Indicates an ability to attack through the air. E.e., arrows.
     */
    Air,

    /**
     * Indicates an ability to attack through water. E.g., torpedos.
     */
    Water;

    /**
     * Returns objects with this {@code OffensiveCapability} can attack objects
     * with the given {@link TerrainType}.
     */
    public boolean supports(TerrainType terrain) {
        switch (this) {
            case None: return false;
            case Air: return true;
            case Water: return terrain == TerrainType.Water;
            case Proximity: return terrain != TerrainType.Air;
            default: throw new UnsupportedOperationException();
        }
    }
}
