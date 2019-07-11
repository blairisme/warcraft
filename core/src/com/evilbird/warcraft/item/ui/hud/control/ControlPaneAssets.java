/*
 * Copyright (c) 2019, Blair Butterworth
 *
 * This work is licensed under the MIT License. To view a copy of this
 * license, visit
 *
 *        https://opensource.org/licenses/MIT
 */

package com.evilbird.warcraft.item.ui.hud.control;

import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.scenes.scene2d.utils.Drawable;
import com.evilbird.warcraft.item.ui.hud.control.common.IconSet;
import com.evilbird.warcraft.item.unit.UnitFaction;

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

    public ControlPaneAssets(AssetManager assets, UnitFaction faction) {
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
        loadActionAssets();
        loadPanelAssets();
        loadButtonAssets();
        loadBuildingAssets();
        loadHealthAssets();
    }

    private void loadActionAssets() {
        assets.load(manifest.getActionButton(), Texture.class);
        assets.load(manifest.getIcons(), Texture.class);
        assets.load(manifest.getDisabledIcons(), Texture.class);
        assets.load(manifest.getActionPanel(), Texture.class);
    }

    private void loadPanelAssets() {
        assets.load(manifest.getDetailsPanel(), Texture.class);
        assets.load(manifest.getMenuPanel(), Texture.class);
        assets.load(manifest.getMinimapPanel(), Texture.class);
        assets.load(manifest.getSelectionPanel(), Texture.class);
        assets.load(manifest.getUnitPanel(), Texture.class);
    }

    private void loadBuildingAssets() {
        assets.load(manifest.getBuildingFill(), Texture.class);
        assets.load(manifest.getBuildingBackground(), Texture.class);
    }

    private void loadHealthAssets() {
        assets.load(manifest.getHealthProgressHigh(), Texture.class);
        assets.load(manifest.getHealthProgressMedium(), Texture.class);
        assets.load(manifest.getHealthProgressLow(), Texture.class);
    }

    private void loadButtonAssets() {
        assets.load(manifest.getDeselectButton(), Texture.class);
        assets.load(manifest.getButtonEnabled(), Texture.class);
        assets.load(manifest.getButtonSelected(), Texture.class);
        assets.load(manifest.getButtonDisabled(), Texture.class);
    }
}
