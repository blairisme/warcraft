/*
 * Copyright (c) 2019, Blair Butterworth
 *
 * This work is licensed under the MIT License. To view a copy of this
 * license, visit
 *
 *        https://opensource.org/licenses/MIT
 */

package com.evilbird.warcraft.object.unit.building.sounds;

import com.evilbird.engine.common.audio.sound.Sound;
import com.evilbird.warcraft.object.unit.building.BuildingAssets;

import static com.evilbird.warcraft.object.unit.UnitSound.Attack;
import static com.evilbird.warcraft.object.unit.UnitSound.Hit;

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
        sound(Hit, assets.getHitSound());
    }
}
