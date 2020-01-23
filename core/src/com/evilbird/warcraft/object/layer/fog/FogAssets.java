/*
 * Copyright (c) 2020, Blair Butterworth
 *
 * This Source Code Form is subject to the terms of the Mozilla Public
 * License, v. 2.0. If a copy of the MPL was not distributed with this
 * file, You can obtain one at http://mozilla.org/MPL/2.0/.
 */

package com.evilbird.warcraft.object.layer.fog;

import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.evilbird.engine.assets.AssetBundle;

/**
 * Provides access to the assets that are required to display {@link Fog}.
 *
 * @author Blair Butterworth
 */
public class FogAssets extends AssetBundle
{
    public FogAssets(AssetManager manager) {
        super(manager);
        register("fog", "data/textures/common/terrain/fog.png");
    }

    public Texture getFogTexture() {
        Texture texture = getTexture("fog");
        texture.setFilter(Texture.TextureFilter.Linear, Texture.TextureFilter.Nearest);
        return texture;
    }

    public TextureRegion getTransparentTexture() {
        Texture texture = getTexture("fog");
        return new TextureRegion(texture, 0, 36, 32, 32);
    }

    public TextureRegion getOpaqueTexture() {
        Texture texture = getTexture("fog");
        return new TextureRegion(texture, 0, 0, 32, 32);
    }
}
