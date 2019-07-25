/*
 * Copyright (c) 2019, Blair Butterworth
 *
 * This work is licensed under the MIT License. To view a copy of this
 * license, visit
 *
 *        https://opensource.org/licenses/MIT
 */

package com.evilbird.warcraft.item.ui.display.resource;

import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.scenes.scene2d.utils.Drawable;
import com.evilbird.engine.common.assets.AssetBundle;
import com.evilbird.engine.common.collection.Maps;
import com.evilbird.warcraft.common.WarcraftContext;

import java.util.Map;

import static com.evilbird.engine.common.text.CaseUtils.toSnakeCase;

/**
 * Provides access to the assets that are required to display a
 * {@link ResourcePane}.
 *
 * @author Blair Butterworth
 */
public class ResourcePaneAssets extends AssetBundle
{
    public ResourcePaneAssets(AssetManager manager, WarcraftContext context) {
        super(manager, assetPathVariables(context));
        register("icons", "data/textures/common/menu/resource_icon.png");
        register("background", "data/textures/${faction}/menu/resource_panel.png");
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
}
