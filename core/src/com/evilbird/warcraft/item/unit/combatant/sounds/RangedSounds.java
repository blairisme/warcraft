/*
 * Copyright (c) 2019, Blair Butterworth
 *
 * This work is licensed under the MIT License. To view a copy of this
 * license, visit
 *
 *        https://opensource.org/licenses/MIT
 */

package com.evilbird.warcraft.item.unit.combatant.sounds;

import com.evilbird.engine.common.audio.sound.Sound;
import com.evilbird.engine.common.audio.sound.SoundCatalog;
import com.evilbird.warcraft.item.unit.combatant.CombatantAssets;

import static com.evilbird.warcraft.item.unit.UnitSound.Acknowledge;
import static com.evilbird.warcraft.item.unit.UnitSound.Attack;
import static com.evilbird.warcraft.item.unit.UnitSound.Captured;
import static com.evilbird.warcraft.item.unit.UnitSound.Die;
import static com.evilbird.warcraft.item.unit.UnitSound.Hit;
import static com.evilbird.warcraft.item.unit.UnitSound.Ready;
import static com.evilbird.warcraft.item.unit.UnitSound.Rescued;
import static com.evilbird.warcraft.item.unit.UnitSound.Selected;

/**
 * Defines a catalog of {@link Sound Sounds} used by ranged combatants.
 *
 * @author Blair Butterworth
 */
public class RangedSounds extends SoundCatalog
{
    public RangedSounds(CombatantAssets assets) {
        super(8);
        sound(Acknowledge, assets.getAcknowledgeSound());
        sound(Selected, assets.getSelectedSound());
        sound(Attack, assets.getAttackSound());
        sound(Die, assets.getDieSound());
        sound(Ready, assets.getReadySound());
        sound(Captured, assets.getCaptureSound());
        sound(Rescued, assets.getRescueSound());
        sound(Hit, assets.getHitSound());
    }
}
