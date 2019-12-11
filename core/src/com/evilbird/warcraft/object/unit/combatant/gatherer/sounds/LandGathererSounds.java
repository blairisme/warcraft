/*
 * Copyright (c) 2019, Blair Butterworth
 *
 * This work is licensed under the MIT License. To view a copy of this
 * license, visit
 *
 *        https://opensource.org/licenses/MIT
 */

package com.evilbird.warcraft.object.unit.combatant.gatherer.sounds;

import com.evilbird.engine.common.audio.sound.Sound;
import com.evilbird.engine.common.audio.sound.SoundCatalog;
import com.evilbird.warcraft.object.unit.combatant.gatherer.GathererAssets;

import static com.evilbird.warcraft.object.unit.UnitSound.Acknowledge;
import static com.evilbird.warcraft.object.unit.UnitSound.Attack;
import static com.evilbird.warcraft.object.unit.UnitSound.Build;
import static com.evilbird.warcraft.object.unit.UnitSound.ChopWood;
import static com.evilbird.warcraft.object.unit.UnitSound.Complete;
import static com.evilbird.warcraft.object.unit.UnitSound.Die;
import static com.evilbird.warcraft.object.unit.UnitSound.Ready;
import static com.evilbird.warcraft.object.unit.UnitSound.Selected;

/**
 * Defines a catalog of {@link Sound Sounds} used by land gatherers.
 *
 * @author Blair Butterworth
 */
public class LandGathererSounds extends SoundCatalog
{
    public LandGathererSounds(GathererAssets assets) {
        super(8);
        sound(Selected, assets.getSelectedSound());
        sound(Acknowledge, assets.getAcknowledgeSound());
        sound(Build, assets.getConstructSound());
        sound(Complete, assets.getCompleteSound());
        sound(Ready, assets.getReadySound());
        sound(Attack, assets.getAttackSound());
        sound(Die, assets.getDeadSound());
        sound(ChopWood, assets.getChoppingSound());
    }
}
