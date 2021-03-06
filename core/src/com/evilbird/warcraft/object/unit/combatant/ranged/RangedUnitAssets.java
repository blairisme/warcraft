/*
 * Copyright (c) 2020, Blair Butterworth
 *
 * This Source Code Form is subject to the terms of the Mozilla Public
 * License, v. 2.0. If a copy of the MPL was not distributed with this
 * file, You can obtain one at http://mozilla.org/MPL/2.0/.
 */

package com.evilbird.warcraft.object.unit.combatant.ranged;

import com.badlogic.gdx.assets.AssetManager;
import com.evilbird.engine.audio.sound.Sound;
import com.evilbird.warcraft.object.unit.UnitType;
import com.evilbird.warcraft.object.unit.combatant.CombatantAssets;

import static com.evilbird.warcraft.common.WarcraftFaction.Human;

/**
 * Defines the assets that are required to display ranged combatants
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
     * @param manager an asset manager used to load and unload assets.
     * @param type    the type of unit whose assets will be loaded and
     *                unloaded.
     * @throws IllegalArgumentException if either the given asset manager or
     *                                  unit type is {@code null}.
     */
    public RangedUnitAssets(AssetManager manager, UnitType type) {
        super(manager, type);

        if (type.getFaction() == Human) {
            register("attack", "data/sounds/common/unit/attack/bow/1.mp3");
        } else {
            register("attack", "data/sounds/common/unit/attack/axe/1.mp3");
        }
    }

    public Sound getAttackSound() {
        return getSoundEffect("attack");
    }
}
