/*
 * Copyright (c) 2020, Blair Butterworth
 *
 * This Source Code Form is subject to the terms of the Mozilla Public
 * License, v. 2.0. If a copy of the MPL was not distributed with this
 * file, You can obtain one at http://mozilla.org/MPL/2.0/.
 */

package com.evilbird.warcraft.object.unit.resource;

import com.evilbird.engine.audio.sound.Sound;
import com.evilbird.engine.audio.sound.SoundCatalog;

import static com.evilbird.warcraft.object.unit.UnitSound.Die;
import static com.evilbird.warcraft.object.unit.UnitSound.Selected;

/**
 * Defines a catalog of {@link Sound Sounds} used by resources.
 *
 * @author Blair Butterworth
 */
public class ResourceSounds extends SoundCatalog
{
    public ResourceSounds(ResourceAssets assets) {
        super(2);
        sound(Selected, assets.getSelectedSound());
        sound(Die, assets.getDestroyedSound());
    }
}
