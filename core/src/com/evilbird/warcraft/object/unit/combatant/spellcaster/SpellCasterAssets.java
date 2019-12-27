/*
 * Copyright (c) 2019, Blair Butterworth
 *
 * This work is licensed under the MIT License. To view a copy of this
 * license, visit
 *
 *        https://opensource.org/licenses/MIT
 */

package com.evilbird.warcraft.object.unit.combatant.spellcaster;

import com.badlogic.gdx.assets.AssetManager;
import com.evilbird.engine.audio.sound.Sound;
import com.evilbird.warcraft.object.unit.UnitType;
import com.evilbird.warcraft.object.unit.combatant.CombatantAssets;

import static com.evilbird.engine.audio.sound.SilentSound.SilentSoundEffect;
import static com.evilbird.warcraft.object.unit.UnitType.Chogall;
import static com.evilbird.warcraft.object.unit.UnitType.DeathKnight;
import static com.evilbird.warcraft.object.unit.UnitType.Mage;
import static com.evilbird.warcraft.object.unit.UnitType.OgreMage;
import static com.evilbird.warcraft.object.unit.UnitType.Paladin;

/**
 * Defines the assets that are required to display spell casters
 * as well as any sound effects used by it.
 *
 * @author Blair Butterworth
 */
public class SpellCasterAssets extends CombatantAssets
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
    public SpellCasterAssets(AssetManager manager, UnitType type) {
        super(manager, type);

        if (type == Mage) {
            register("attack", "data/sounds/common/unit/attack/fireball/1.mp3");
        }
        if (type == DeathKnight) {
            register("attack", "data/sounds/common/spell/touch_of_darkness.mp3");
        }
        if (type == OgreMage || type == Chogall) {
            register("attack", "data/sounds/common/unit/attack/punch/1.mp3");
        }
        if (type == Paladin ){
            registerSequence("attack", "data/sounds/common/unit/attack/sword/", ".mp3", 3);
        }
    }

    public Sound getAttackSound() {
        if (isSetRegistered("attack")) {
            return getSoundEffectSet("attack", 3);
        }
        if (isRegistered("attack")) {
            return getSoundEffect("attack");
        }
        return SilentSoundEffect;
    }
}
