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
import com.evilbird.engine.common.audio.sound.SoundCatalog;
import com.evilbird.warcraft.object.unit.building.BuildingAssets;

import static com.evilbird.warcraft.object.unit.UnitSound.Die;
import static com.evilbird.warcraft.object.unit.UnitSound.Placement;
import static com.evilbird.warcraft.object.unit.UnitSound.Selected;

/**
 * Defines a catalog of {@link Sound Sounds} used by buildings.
 *
 * @author Blair Butterworth
 */
public class BuildingSounds extends SoundCatalog
{
    public BuildingSounds(BuildingAssets assets) {
        super(3);
        sound(Die, assets.getDestroyedSound());
        sound(Selected, assets.getSelectedSound());
        sound(Placement, assets.getPlacementSound());
    }
}
