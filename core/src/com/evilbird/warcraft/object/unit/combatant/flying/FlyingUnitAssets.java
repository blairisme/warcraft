/*
 * Copyright (c) 2019, Blair Butterworth
 *
 * This work is licensed under the MIT License. To view a copy of this
 * license, visit
 *
 *        https://opensource.org/licenses/MIT
 */

package com.evilbird.warcraft.object.unit.combatant.flying;

import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.evilbird.warcraft.object.unit.UnitType;
import com.evilbird.warcraft.object.unit.combatant.CombatantAssets;

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
    }

    public Texture getExplosionTexture() {
        return getTexture("explosion");
    }

    public TextureRegion getShadowTexture() {
        return getTexture("shadow", 0, 64, 32, 32);
    }
}
