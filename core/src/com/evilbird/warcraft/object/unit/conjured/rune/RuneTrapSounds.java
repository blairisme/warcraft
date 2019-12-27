/*
 * Copyright (c) 2019, Blair Butterworth
 *
 * This work is licensed under the MIT License. To view a copy of this
 * license, visit
 *
 *        https://opensource.org/licenses/MIT
 */

package com.evilbird.warcraft.object.unit.conjured.rune;

import com.evilbird.engine.audio.sound.SoundCatalog;
import com.evilbird.warcraft.object.unit.UnitSound;
import com.evilbird.warcraft.object.unit.conjured.ConjuredAssets;

public class RuneTrapSounds extends SoundCatalog
{
    public RuneTrapSounds(ConjuredAssets assets) {
        super(1);
        sound(UnitSound.Attack, assets.getBaseSound());
    }
}
