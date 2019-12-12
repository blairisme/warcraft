/*
 * Copyright (c) 2019, Blair Butterworth
 *
 * This work is licensed under the MIT License. To view a copy of this
 * license, visit
 *
 *        https://opensource.org/licenses/MIT
 */

package com.evilbird.warcraft.object.unit.combatant.naval;

import com.badlogic.gdx.assets.AssetManager;
import com.evilbird.engine.common.audio.sound.Sound;
import com.evilbird.warcraft.object.unit.UnitType;
import com.evilbird.warcraft.object.unit.combatant.CombatantAssets;

import static com.evilbird.engine.common.audio.sound.SilentSound.SilentSoundEffect;

/**
 * Defines the assets that are required to display naval combatants
 * as well as any sound effects used by it.
 *
 * @author Blair Butterworth
 */
public class NavalUnitAssets extends CombatantAssets
{
    /**
     * Constructs a new instance of this class given an {@link AssetManager}
     * from which assets will be obtained and the {@link UnitType} whose assets
     * will be obtained.
     *
     * @param manager an asset manager used to load and unload assets.
     * @param type    the type of unit whose assets will be loaded and
     *                unloaded.
     *
     * @throws IllegalArgumentException if either the given asset manager or
     *                                  unit type is {@code null}.
     */
    public NavalUnitAssets(AssetManager manager, UnitType type) {
        super(manager, type);
        if (type.isWarship()) {
            register("attack", "data/sounds/common/unit/attack/siege/1.mp3");
        }
    }

    public Sound getAttackSound() {
        return isRegistered("attack") ? getSoundEffect("attack") : SilentSoundEffect;
    }
}
