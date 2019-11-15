/*
 * Copyright (c) 2019, Blair Butterworth
 *
 * This work is licensed under the MIT License. To view a copy of this
 * license, visit
 *
 *        https://opensource.org/licenses/MIT
 */

package com.evilbird.warcraft.object.unit.combatant;

import com.evilbird.engine.common.audio.sound.Sound;
import com.evilbird.engine.common.audio.sound.SoundCatalog;

import static com.evilbird.warcraft.object.unit.UnitSound.Acknowledge;
import static com.evilbird.warcraft.object.unit.UnitSound.Attack;
import static com.evilbird.warcraft.object.unit.UnitSound.Selected;

/**
 * Defines a catalog of {@link Sound Sounds} used by conjured combatants.
 *
 * @author Blair Butterworth
 */
public class ConjuredUnitSounds extends SoundCatalog
{
    /**
     * Creates a new instance of this class given a {@link CombatantAssets}
     * bundle, containing the acknowledge, attack, selected and death sound
     * effect assets used by the sound catalog.
     */
    public ConjuredUnitSounds(CombatantAssets assets) {
        super(4);
        sound(Acknowledge, assets.getAcknowledgeSound());
        sound(Selected, assets.getSelectedSound());
        sound(Attack, assets.getAttackSound());
        //sound(Die, assets.getDieSound());
    }
}
