/*
 * Copyright (c) 2020, Blair Butterworth
 *
 * This Source Code Form is subject to the terms of the Mozilla Public
 * License, v. 2.0. If a copy of the MPL was not distributed with this
 * file, You can obtain one at http://mozilla.org/MPL/2.0/.
 */

package com.evilbird.warcraft.object.display.views.resource;

import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.scenes.scene2d.utils.Drawable;
import com.evilbird.engine.assets.AssetBundle;
import com.evilbird.engine.common.collection.Maps;
import com.evilbird.warcraft.state.WarcraftContext;

import java.util.Map;

import static com.evilbird.engine.assets.AssetUtilities.fontSize;
import static com.evilbird.engine.common.text.StringUtils.toSnakeCase;

/**
 * Provides access to the assets that are required to display a
 * {@link ResourceBar}.
 *
 * @author Blair Butterworth
 */
public class ResourceBarAssets extends AssetBundle
{
    public ResourceBarAssets(AssetManager manager, WarcraftContext context) {
        super(manager, assetPathVariables(context));
        register("icons", "data/textures/common/menu/resource_icon.png");
        register("background", "data/textures/${faction}/menu/resource_panel.png");
        register("font", "data/fonts/philosopher-medium.ttf", BitmapFont.class, fontSize(16));
    }

    private static Map<String, String> assetPathVariables(WarcraftContext context) {
        return Maps.of("faction", toSnakeCase(context.getFaction().name()));
    }

    public Drawable getBackground() {
        return getDrawable("background");
    }

    public Drawable getGoldIcon() {
        return getDrawable("icons", 0, 0, 14, 14);
    }

    public Drawable getOilIcon() {
        return getDrawable("icons", 0, 28, 14, 14);
    }

    public Drawable getWoodIcon() {
        return getDrawable("icons", 0, 14, 14, 14);
    }

    public BitmapFont getFont() {
        return getFont("font");
    }
}
