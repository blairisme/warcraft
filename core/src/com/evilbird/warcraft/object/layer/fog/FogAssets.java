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
import com.badlogic.gdx.math.GridPoint2;
import com.evilbird.engine.assets.AssetBundle;
import com.evilbird.engine.assets.SyntheticTexture;
import com.evilbird.engine.common.collection.Maps;
import com.evilbird.warcraft.state.WarcraftContext;

import java.util.Map;

import static com.evilbird.engine.assets.SyntheticTextureParameters.with;
import static com.evilbird.engine.assets.SyntheticTextureType.Filled;
import static com.evilbird.engine.common.graphics.Colours.OPAQUE;
import static com.evilbird.engine.common.text.CaseUtils.toSnakeCase;

/**
 * Provides access to the assets that are required to display {@link Fog}.
 *
 * @author Blair Butterworth
 */
public class FogAssets extends AssetBundle
{
    private static GridPoint2 TILE_SIZE = new GridPoint2(32, 32);

    public FogAssets(AssetManager manager, WarcraftContext context) {
        super(manager, assetPathVariables(context));
        register("terrain", "data/textures/common/terrain/${season}.png");
        register("opaque", "opaque_fog", SyntheticTexture.class, with(OPAQUE, TILE_SIZE, Filled));
    }

    private static Map<String, String> assetPathVariables(WarcraftContext context) {
        return Maps.of("season", toSnakeCase(context.getAssetSet().name()));
    }

    public Texture getTerrainTexture() {
        Texture texture = getTexture("terrain");
        texture.setFilter(Texture.TextureFilter.Linear, Texture.TextureFilter.Nearest);
        return texture;
    }

    public TextureRegion getTransparentTexture() {
        Texture texture = getTexture("terrain");
        return new TextureRegion(texture, 0, 0, 32, 32);
    }

    public Texture getOpaqueTexture() {
        return getSyntheticTexture("opaque");
    }
}
