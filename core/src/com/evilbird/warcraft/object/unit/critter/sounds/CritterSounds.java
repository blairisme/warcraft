/*
 * Copyright (c) 2019, Blair Butterworth
 *
 * This work is licensed under the MIT License. To view a copy of this
 * license, visit
 *
 *        https://opensource.org/licenses/MIT
 */

package com.evilbird.warcraft.object.unit.critter.sounds;

import com.evilbird.engine.audio.sound.Sound;
import com.evilbird.engine.audio.sound.SoundCatalog;
import com.evilbird.warcraft.object.unit.critter.CritterAssets;

import static com.evilbird.warcraft.object.unit.UnitSound.Die;
import static com.evilbird.warcraft.object.unit.UnitSound.Selected;

/**
 * Defines a catalog of {@link Sound Sounds} used by critters.
 *
 * @author Blair Butterworth
 */
public class CritterSounds extends SoundCatalog
{
    public CritterSounds(CritterAssets assets) {
        super(2);
        sound(Selected, assets.getSelectedSound());
        sound(Die, assets.getDieSound());
    }
}
