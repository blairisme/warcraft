/*
 * Copyright (c) 2019, Blair Butterworth
 *
 * This work is licensed under the MIT License. To view a copy of this
 * license, visit
 *
 *        https://opensource.org/licenses/MIT
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
