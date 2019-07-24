/*
 * Copyright (c) 2019, Blair Butterworth
 *
 * This work is licensed under the MIT License. To view a copy of this
 * license, visit
 *
 *        https://opensource.org/licenses/MIT
 */

package com.evilbird.warcraft.item.layer.fog;

import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.evilbird.engine.common.assets.AssetBundle;
import com.evilbird.engine.common.assets.SyntheticTexture;
import com.evilbird.engine.common.collection.Maps;
import com.evilbird.warcraft.common.WarcraftContext;

import java.util.Map;

import static com.evilbird.engine.common.assets.SyntheticTextureParameters.with;
import static com.evilbird.engine.common.assets.SyntheticTextureType.Filled;
import static com.evilbird.engine.common.graphics.Colours.OPAQUE;
import static com.evilbird.engine.common.text.CaseUtils.toSnakeCase;
import static com.evilbird.warcraft.item.WarcraftItemConstants.TILE_SIZE;

/**
 * Provides access to the assets that are required to display {@link Fog}.
 *
 * @author Blair Butterworth
 */
public class FogAssets extends AssetBundle
{
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
