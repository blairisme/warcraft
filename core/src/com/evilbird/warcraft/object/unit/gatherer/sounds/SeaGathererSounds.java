/*
 * Copyright (c) 2019, Blair Butterworth
 *
 * This work is licensed under the MIT License. To view a copy of this
 * license, visit
 *
 *        https://opensource.org/licenses/MIT
 */

package com.evilbird.warcraft.object.unit.gatherer.sounds;

import com.evilbird.engine.common.audio.sound.Sound;
import com.evilbird.engine.common.audio.sound.SoundCatalog;
import com.evilbird.warcraft.object.unit.gatherer.GathererAssets;

import static com.evilbird.warcraft.object.unit.UnitSound.Acknowledge;
import static com.evilbird.warcraft.object.unit.UnitSound.Build;
import static com.evilbird.warcraft.object.unit.UnitSound.Complete;
import static com.evilbird.warcraft.object.unit.UnitSound.Die;
import static com.evilbird.warcraft.object.unit.UnitSound.Ready;
import static com.evilbird.warcraft.object.unit.UnitSound.Selected;

/**
 * Defines a catalog of {@link Sound Sounds} used by sea gatherers.
 *
 * @author Blair Butterworth
 */
public class SeaGathererSounds extends SoundCatalog
{
    public SeaGathererSounds(GathererAssets assets) {
        super(6);
        sound(Selected, assets.getSelectedSound());
        sound(Acknowledge, assets.getAcknowledgeSound());
        sound(Build, assets.getConstructSound());
        sound(Complete, assets.getCompleteSound());
        sound(Ready, assets.getReadySound());
        sound(Die, assets.getDeadSound());
    }
}
