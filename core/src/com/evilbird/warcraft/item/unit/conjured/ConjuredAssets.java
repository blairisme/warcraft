/*
 * Copyright (c) 2019, Blair Butterworth
 *
 * This work is licensed under the MIT License. To view a copy of this
 * license, visit
 *
 *        https://opensource.org/licenses/MIT
 */

package com.evilbird.warcraft.item.unit.conjured;

import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.graphics.Texture;
import com.evilbird.engine.common.assets.AssetBundle;

/**
 * Provides access to the assets that are required to display a effect game
 * objects.
 *
 * @author Blair Butterworth
 */
public class ConjuredAssets extends AssetBundle
{
    public ConjuredAssets(AssetManager assetManager) {
        super(assetManager);
        register("data/textures/common/environmental/blizzard.png");
        register("data/textures/common/environmental/rune.png");
        register("data/textures/common/environmental/whirlwind.png");
        register("data/textures/common/spell/death_and_decay.png");
    }

    public Texture getBlizzard() {
        return getTexture("blizzard.png");
    }

    public Texture getDeathAndDecay() {
        return getTexture("death_and_decay.png");
    }

    public Texture getRune() {
        return getTexture("rune.png");
    }

    public Texture getWhirlwind() {
        return getTexture("whirlwind.png");
    }
}
