/*
 * Copyright (c) 2019, Blair Butterworth
 *
 * This work is licensed under the MIT License. To view a copy of this
 * license, visit
 *
 *        https://opensource.org/licenses/MIT
 */

package com.evilbird.warcraft.item.unit.building.sounds;

import com.evilbird.engine.common.audio.sound.Sound;
import com.evilbird.warcraft.item.unit.building.BuildingAssets;

import static com.evilbird.warcraft.item.unit.UnitSound.Attack;
import static com.evilbird.warcraft.item.unit.UnitSound.Hit;

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
