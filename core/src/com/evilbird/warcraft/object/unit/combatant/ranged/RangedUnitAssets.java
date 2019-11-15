/*
 * Copyright (c) 2019, Blair Butterworth
 *
 * This work is licensed under the MIT License. To view a copy of this
 * license, visit
 *
 *        https://opensource.org/licenses/MIT
 */

package com.evilbird.warcraft.object.unit.combatant.ranged;

import com.badlogic.gdx.assets.AssetManager;
import com.evilbird.engine.common.audio.sound.SilentSound;
import com.evilbird.engine.common.audio.sound.Sound;
import com.evilbird.warcraft.object.unit.UnitType;
import com.evilbird.warcraft.object.unit.combatant.CombatantAssets;
import com.evilbird.warcraft.object.unit.combatant.RangedCombatant;

/**
 * Defines the assets that are required to display a {@link RangedCombatant},
 * as well as any sound effects used by it.
 *
 * @author Blair Butterworth
 */
public class RangedUnitAssets extends CombatantAssets
{
    /**
     * Constructs a new instance of this class given an {@link AssetManager}
     * from which assets will be obtained and the {@link UnitType} whose assets
     * will be obtained.
     *
     * @param manager   an asset manager used to load and unload assets.
     * @param type      the type of unit whose assets will be loaded and
     *                  unloaded.
     *
     * @throws IllegalArgumentException if either the given asset manager or
     *                                  unit type is {@code null}.
     */
    public RangedUnitAssets(AssetManager manager, UnitType type) {
        super(manager, type);
        register("hit", "data/sounds/common/unit/hit/${weapon}/1.mp3");
    }

    public Sound getHitSound() {
        return isRegistered("hit") ? getSoundEffect("hit") : new SilentSound();
    }
}
