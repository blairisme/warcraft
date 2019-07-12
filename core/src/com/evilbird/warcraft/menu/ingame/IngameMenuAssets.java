/*
 * Copyright (c) 2019, Blair Butterworth
 *
 * This work is licensed under the MIT License. To view a copy of this
 * license, visit
 *
 *        https://opensource.org/licenses/MIT
 */

package com.evilbird.warcraft.menu.ingame;

import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.scenes.scene2d.utils.Drawable;
import com.evilbird.warcraft.item.unit.UnitFaction;

import static com.evilbird.engine.common.graphics.TextureUtils.getDrawable;
import static com.evilbird.engine.common.graphics.TextureUtils.getTiledDrawable;

/**
 * Defines the assets that are required to display an {@link IngameMenu}, as
 * well as any sound effects used by it, especially narration.
 *
 * @author Blair Butterworth
 */
public class IngameMenuAssets
{
    private AssetManager assets;
    private IngameMenuAssetManifest manifest;

    public IngameMenuAssets(AssetManager assets, UnitFaction faction) {
        this.assets = assets;
        this.manifest = new IngameMenuAssetManifest(faction);
    }

    public Drawable getButtonEnabled() {
        return getDrawable(assets, manifest.getButtonEnabled());
    }

    public Drawable getButtonDisabled() {
        return getDrawable(assets, manifest.getButtonDisabled());
    }

    public Drawable getButtonSelected() {
        return getDrawable(assets, manifest.getButtonSelected());
    }

    public Drawable getListBackground() {
        return getTiledDrawable(assets, manifest.getTextPanelNormal());
    }

    public Drawable getTextFieldBackground() {
        return getDrawable(assets, manifest.getTextPanelNormal());
    }

    public Drawable getBackgroundNormal() {
        return getDrawable(assets, manifest.getBackgroundNormal());
    }

    public Drawable getBackgroundWide() {
        return getDrawable(assets, manifest.getBackgroundWide());
    }

    public Drawable getBackgroundSmall() {
        return getDrawable(assets, manifest.getBackgroundSmall());
    }

    public void load() {
        assets.load(manifest.getBackgroundNormal(), Texture.class);
        assets.load(manifest.getBackgroundWide(), Texture.class);
        assets.load(manifest.getBackgroundSmall(), Texture.class);
        assets.load(manifest.getButtonEnabled(), Texture.class);
        assets.load(manifest.getButtonDisabled(), Texture.class);
        assets.load(manifest.getButtonSelected(), Texture.class);
        assets.load(manifest.getTextPanelNormal(), Texture.class);
        assets.load(manifest.getButtonClick(), Sound.class);
    }
}
