/*
 * Copyright (c) 2020, Blair Butterworth
 *
 * This Source Code Form is subject to the terms of the Mozilla Public
 * License, v. 2.0. If a copy of the MPL was not distributed with this
 * file, You can obtain one at http://mozilla.org/MPL/2.0/.
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
