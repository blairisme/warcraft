/*
 * Copyright (c) 2019, Blair Butterworth
 *
 * This work is licensed under the MIT License. To view a copy of this
 * license, visit
 *
 *        https://opensource.org/licenses/MIT
 */

package com.evilbird.warcraft.item.ui.hud.resource;

import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.scenes.scene2d.utils.Drawable;
import com.evilbird.warcraft.item.unit.UnitFaction;

import static com.evilbird.engine.common.graphics.TextureUtils.getDrawable;

/**
 * Provides access to the assets that are required to display a
 * {@link ResourcePane}.
 *
 * @author Blair Butterworth
 */
public class ResourcePaneAssets
{
    private AssetManager assets;
    private ResourcePaneAssetManifest manifest;

    public ResourcePaneAssets(AssetManager assets, UnitFaction faction) {
        this.assets = assets;
        this.manifest = new ResourcePaneAssetManifest(faction);
    }

    public Drawable getBackground() {
        return getDrawable(assets, manifest.getBackground());
    }

    public Drawable getGoldIcon() {
        return getDrawable(assets, manifest.getIcons(), 0, 0, 14, 14);
    }

    public Drawable getOilIcon() {
        return getDrawable(assets, manifest.getIcons(), 0, 28, 14, 14);
    }

    public Drawable getWoodIcon() {
        return getDrawable(assets, manifest.getIcons(), 0, 14, 14, 14);
    }

    public void load() {
        assets.load(manifest.getIcons(), Texture.class);
        assets.load(manifest.getBackground(), Texture.class);
    }
}
