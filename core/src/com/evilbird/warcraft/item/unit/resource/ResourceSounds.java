/*
 * Copyright (c) 2019, Blair Butterworth
 *
 * This work is licensed under the MIT License. To view a copy of this
 * license, visit
 *
 *        https://opensource.org/licenses/MIT
 */

package com.evilbird.warcraft.item.unit.resource;

import com.evilbird.engine.common.audio.sound.Sound;
import com.evilbird.engine.common.audio.sound.SoundCatalog;
import com.evilbird.warcraft.item.unit.resource.ResourceAssets;

import static com.evilbird.warcraft.item.unit.UnitSound.Die;
import static com.evilbird.warcraft.item.unit.UnitSound.Selected;

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
