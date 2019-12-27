/*
 * Copyright (c) 2019, Blair Butterworth
 *
 * This work is licensed under the MIT License. To view a copy of this
 * license, visit
 *
 *        https://opensource.org/licenses/MIT
 */

package com.evilbird.warcraft.object.unit.conjured;

import com.evilbird.engine.audio.sound.Sound;
import com.evilbird.engine.audio.sound.SoundCatalog;
import com.evilbird.warcraft.object.unit.UnitSound;

/**
 * Defines a catalog of {@link Sound Sounds} used by traps.
 *
 * @author Blair Butterworth
 */
public class ConjuredAreaSounds extends SoundCatalog
{
    public ConjuredAreaSounds(ConjuredAssets assets) {
        super(1);
        sound(UnitSound.Background, assets.getBaseSound());
    }
}
