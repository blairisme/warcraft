/*
 * Copyright (c) 2020, Blair Butterworth
 *
 * This Source Code Form is subject to the terms of the Mozilla Public
 * License, v. 2.0. If a copy of the MPL was not distributed with this
 * file, You can obtain one at http://mozilla.org/MPL/2.0/.
 */

package com.evilbird.warcraft.object.unit.combatant.flying;

import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.evilbird.engine.audio.sound.Sound;
import com.evilbird.warcraft.object.unit.UnitType;
import com.evilbird.warcraft.object.unit.combatant.CombatantAssets;

import static com.evilbird.engine.audio.sound.SilentSound.SilentSoundEffect;
import static com.evilbird.warcraft.object.unit.UnitType.Daemon;
import static com.evilbird.warcraft.object.unit.UnitType.Dragon;

/**
 * Defines the assets that are required to display a {@link FlyingUnit}, as well
 * as any sound effects used by it.
 *
 * @author Blair Butterworth
 */
public class FlyingUnitAssets extends CombatantAssets
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
    public FlyingUnitAssets(AssetManager manager, UnitType type) {
        super(manager, type);

        register("explosion", "data/textures/common/explosion/explosion.png");
        register("shadow", "data/textures/common/unit/shadow.png");
        register("dead", "data/sounds/common/explosion/explosion.mp3");

        if (type == Dragon || type == Daemon) {
            register("attack", "data/sounds/common/unit/attack/fireball/1.mp3");
        }
    }

    @Override
    public Sound getAttackSound() {
        return isRegistered("attack") ? getSoundEffect("attack") : SilentSoundEffect;
    }

    @Override
    public Sound getDieSound() {
        return getSoundEffect("dead");
    }

    public Texture getExplosionTexture() {
        return getTexture("explosion");
    }

    public TextureRegion getShadowTexture() {
        return getTexture("shadow", 0, 64, 32, 32);
    }
}
