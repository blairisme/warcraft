/*
 * Copyright (c) 2020, Blair Butterworth
 *
 * This Source Code Form is subject to the terms of the Mozilla Public
 * License, v. 2.0. If a copy of the MPL was not distributed with this
 * file, You can obtain one at http://mozilla.org/MPL/2.0/.
 */

package com.evilbird.warcraft.object.unit.combatant;

import com.evilbird.engine.audio.sound.Sound;
import com.evilbird.engine.audio.sound.SoundCatalog;

import static com.evilbird.warcraft.object.unit.UnitSound.Acknowledge;
import static com.evilbird.warcraft.object.unit.UnitSound.Attack;
import static com.evilbird.warcraft.object.unit.UnitSound.Captured;
import static com.evilbird.warcraft.object.unit.UnitSound.Die;
import static com.evilbird.warcraft.object.unit.UnitSound.Ready;
import static com.evilbird.warcraft.object.unit.UnitSound.Rescued;
import static com.evilbird.warcraft.object.unit.UnitSound.Selected;

/**
 * Defines a catalog of {@link Sound Sounds} used by combatants.
 *
 * @author Blair Butterworth
 */
public class CombatantSounds extends SoundCatalog
{
    /**
     * Creates a new instance of this class given a {@link CombatantAssets}
     * bundle, containing the sound effect assets used by the sound catalog.
     */
    public CombatantSounds(CombatantAssets assets) {
        super(7);
        sound(Acknowledge, assets.getAcknowledgeSound());
        sound(Selected, assets.getSelectedSound());
        sound(Attack, assets.getAttackSound());
        sound(Die, assets.getDieSound());
        sound(Ready, assets.getReadySound());
        sound(Captured, assets.getCaptureSound());
        sound(Rescued, assets.getRescueSound());
    }
}
