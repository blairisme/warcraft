/*
 * Copyright (c) 2019, Blair Butterworth
 *
 * This work is licensed under the MIT License. To view a copy of this
 * license, visit
 *
 *        https://opensource.org/licenses/MIT
 */

package com.evilbird.warcraft.menu.outro;

import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.scenes.scene2d.utils.Drawable;
import com.badlogic.gdx.utils.I18NBundle;
import com.evilbird.warcraft.common.WarcraftContext;
import com.evilbird.warcraft.common.WarcraftFaction;

import static com.evilbird.engine.common.assets.AssetUtilities.fontSize;
import static com.evilbird.engine.common.graphics.TextureUtils.getDrawable;

/**
 * Provides the assets required to display an {@link OutroMenu}, as well as any
 * sound effects used by it.
 *
 * @author Blair Butterworth
 */
public class OutroMenuAssets
{
    private AssetManager assets;
    private OutroMenuAssetManifest manifest;

    public OutroMenuAssets(AssetManager assets, WarcraftContext context) {
        this(assets, context.getFaction());
    }

    public OutroMenuAssets(AssetManager assets, WarcraftFaction faction) {
        this.assets = assets;
        this.manifest = new OutroMenuAssetManifest(faction);
    }

    public BitmapFont getFont() {
        return assets.get(manifest.getFont(), BitmapFont.class);
    }

    public BitmapFont getFontLarge() {
        return assets.get(manifest.getFontLarge(), BitmapFont.class);
    }

    public OutroMenuStrings getStrings() {
        I18NBundle bundle = assets.get(manifest.getStrings(), I18NBundle.class);
        return new OutroMenuStrings(bundle);
    }

    public Drawable getButtonEnabled() {
        return getDrawable(assets, manifest.getButton(), 0, 0, 225, 30);
    }

    public Drawable getButtonDisabled() {
        return getDrawable(assets, manifest.getButton(), 0, 60, 225, 30);
    }

    public Drawable getButtonSelected() {
        return getDrawable(assets, manifest.getButton(), 0, 30, 225, 30);
    }

    public Drawable getVictoryBackground() {
        return getDrawable(assets, manifest.getVictoryBackground());
    }

    public Drawable getDefeatBackground() {
        return getDrawable(assets, manifest.getDefeatBackground());
    }

    public Drawable getProgressFill() {
        return getDrawable(assets, manifest.getProgressFill());
    }

    public Drawable getProgressBackground() {
        return getDrawable(assets, manifest.getProgressBackground());
    }

    public void load() {
        assets.load(manifest.getFont(), BitmapFont.class, fontSize(18));
        assets.load(manifest.getFontLarge(), BitmapFont.class, fontSize(36));
        assets.load(manifest.getButton(), Texture.class);
        assets.load(manifest.getDefeatBackground(), Texture.class);
        assets.load(manifest.getVictoryBackground(), Texture.class);
        assets.load(manifest.getProgressBackground(), Texture.class);
        assets.load(manifest.getProgressFill(), Texture.class);
        assets.load(manifest.getStrings(), I18NBundle.class);
    }

    public void unload() {
        assets.unload(manifest.getFont());
        assets.unload(manifest.getFontLarge());
        assets.unload(manifest.getButton());
        assets.unload(manifest.getDefeatBackground());
        assets.unload(manifest.getVictoryBackground());
        assets.unload(manifest.getProgressBackground());
        assets.unload(manifest.getProgressFill());
        assets.unload(manifest.getStrings());
    }
}
