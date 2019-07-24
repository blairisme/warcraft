/*
 * Copyright (c) 2019, Blair Butterworth
 *
 * This work is licensed under the MIT License. To view a copy of this
 * license, visit
 *
 *        https://opensource.org/licenses/MIT
 */

package com.evilbird.warcraft.item.ui.display.control;

import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.scenes.scene2d.utils.Drawable;
import com.evilbird.warcraft.common.WarcraftContext;
import com.evilbird.warcraft.common.WarcraftFaction;
import com.evilbird.warcraft.item.ui.display.control.common.IconSet;

import java.util.Collection;

import static com.evilbird.engine.common.graphics.TextureUtils.getDrawable;

/**
 * Provides access to the assets that are required to display a
 * {@link ControlPane}.
 *
 * @author Blair Butterworth
 */
public class ControlPaneAssets
{
    private AssetManager assets;
    private ControlPaneAssetManifest manifest;

    public ControlPaneAssets(AssetManager assets, WarcraftContext context) {
        this(assets, context.getFaction());
    }

    public ControlPaneAssets(AssetManager assets, WarcraftFaction faction) {
        this.assets = assets;
        this.manifest = new ControlPaneAssetManifest(faction);
    }

    public IconSet getIcons() {
        Texture texture = assets.get(manifest.getIcons(), Texture.class);
        return new IconSet(texture);
    }

    public IconSet getDisabledIcons() {
        Texture texture = assets.get(manifest.getDisabledIcons(), Texture.class);
        return new IconSet(texture);
    }

    public Drawable getActionButton() {
        return getDrawable(assets, manifest.getActionButton());
    }

    public Drawable getDeselectButton() {
        return getDrawable(assets, manifest.getDeselectButton());
    }

    public Drawable getActionPanel() {
        return getDrawable(assets, manifest.getActionPanel());
    }

    public Drawable getDetailsPanel() {
        return getDrawable(assets, manifest.getDetailsPanel());
    }

    public Drawable getMenuPanel() {
        return getDrawable(assets, manifest.getMenuPanel());
    }

    public Drawable getMinimapPanel() {
        return getDrawable(assets, manifest.getMinimapPanel());
    }

    public Drawable getSelectionPanel() {
        return getDrawable(assets, manifest.getSelectionPanel());
    }

    public Drawable getUnitPanel() {
        return getDrawable(assets, manifest.getUnitPanel());
    }

    public Drawable getButtonEnabled() {
        return getDrawable(assets, manifest.getButtonEnabled());
    }

    public Drawable getButtonSelected() {
        return getDrawable(assets, manifest.getButtonSelected());
    }

    public Drawable getButtonDisabled() {
        return getDrawable(assets, manifest.getButtonDisabled());
    }

    public Drawable getBuildingFill() {
        return getDrawable(assets, manifest.getBuildingFill());
    }

    public Drawable getBuildingBackground() {
        return getDrawable(assets, manifest.getBuildingBackground());
    }

    public Drawable getHealthProgressHigh() {
        return getDrawable(assets, manifest.getHealthProgressHigh());
    }

    public Drawable getHealthProgressMedium() {
        return getDrawable(assets, manifest.getHealthProgressMedium());
    }

    public Drawable getHealthProgressLow() {
        return getDrawable(assets, manifest.getHealthProgressLow());
    }

    public void load() {
        Collection<String> textures = manifest.getTextures();
        textures.forEach(texture -> assets.load(texture, Texture.class));
    }

    public void unload() {
        Collection<String> textures = manifest.getTextures();
        textures.forEach(texture -> assets.unload(texture));
    }
}
