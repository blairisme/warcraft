/*
 * Copyright (c) 2020, Blair Butterworth
 *
 * This Source Code Form is subject to the terms of the Mozilla Public
 * License, v. 2.0. If a copy of the MPL was not distributed with this
 * file, You can obtain one at http://mozilla.org/MPL/2.0/.
 */

package com.evilbird.warcraft.object.unit.building.sounds;

import com.evilbird.engine.audio.sound.Sound;
import com.evilbird.warcraft.object.unit.building.BuildingAssets;

import static com.evilbird.warcraft.object.unit.UnitSound.Attack;

/**
 * Defines a catalog of {@link Sound Sounds} used by offensive buildings:
 * towers.
 *
 * @author Blair Butterworth
 */
public class TowerSounds extends BuildingSounds
{
    public TowerSounds(BuildingAssets assets) {
        super(assets);
        sound(Attack, assets.getAttackSound());
    }
}
