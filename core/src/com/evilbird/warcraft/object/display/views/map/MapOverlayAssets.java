/*
 * Copyright (c) 2019, Blair Butterworth
 *
 * This work is licensed under the MIT License. To view a copy of this
 * license, visit
 *
 *        https://opensource.org/licenses/MIT
 */

package com.evilbird.warcraft.object.display.views.map;

import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.scenes.scene2d.utils.Drawable;
import com.evilbird.engine.common.assets.AssetBundle;
import com.evilbird.engine.common.collection.Maps;
import com.evilbird.warcraft.state.WarcraftContext;

import java.util.Map;

import static com.evilbird.engine.common.text.CaseUtils.toSnakeCase;

/**
 * Provides access to the assets that are required to display a
 * {@link MapOverlay} control.
 *
 * @author Blair Butterworth
 */
public class MapOverlayAssets extends AssetBundle
{
    public MapOverlayAssets(AssetManager manager, WarcraftContext context) {
        super(manager, assetPathVariables(context));
        register("background", "data/textures/${faction}/menu/panel_normal.png");
    }

    private static Map<String, String> assetPathVariables(WarcraftContext context) {
        return Maps.of("faction", toSnakeCase(context.getFaction().name()));
    }

    public Drawable getBackground() {
        return getDrawable("background");
    }
}
