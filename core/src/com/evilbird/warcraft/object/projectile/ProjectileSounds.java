/*
 * Copyright (c) 2020, Blair Butterworth
 *
 * This Source Code Form is subject to the terms of the Mozilla Public
 * License, v. 2.0. If a copy of the MPL was not distributed with this
 * file, You can obtain one at http://mozilla.org/MPL/2.0/.
 */

package com.evilbird.warcraft.object.projectile;

import com.evilbird.engine.audio.sound.Sound;
import com.evilbird.engine.audio.sound.SoundCatalog;

import static com.evilbird.warcraft.object.unit.UnitSound.Hit;

/**
 * Defines a catalog of {@link Sound Sounds} used by {@link Projectile
 * Projectiles}. Only one sound is available to projectiles, the hit sound
 * played when a projectile strikes its target.
 *
 * @author Blair Butterworth
 */
public class ProjectileSounds extends SoundCatalog
{
    /**
     * Creates a new instance of this class given a {@link ProjectileAssets}
     * bundle, containing the sound played when a projectile strikes its
     * target.
     */
    public ProjectileSounds(ProjectileAssets assets, ProjectileType type) {
        super(1);
        sound(Hit, assets.getHitSound(type));
    }
}
