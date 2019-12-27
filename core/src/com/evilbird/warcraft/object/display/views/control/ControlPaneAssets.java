/*
 * Copyright (c) 2019, Blair Butterworth
 *
 * This work is licensed under the MIT License. To view a copy of this
 * license, visit
 *
 *        https://opensource.org/licenses/MIT
 */

package com.evilbird.warcraft.object.display.views.control;

import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.scenes.scene2d.utils.Drawable;
import com.badlogic.gdx.utils.I18NBundle;
import com.evilbird.engine.assets.AssetBundle;
import com.evilbird.engine.common.collection.Maps;
import com.evilbird.warcraft.object.display.components.common.IconSet;
import com.evilbird.warcraft.object.display.components.status.details.DetailsPaneStrings;
import com.evilbird.warcraft.state.WarcraftContext;

import java.util.Map;

import static com.evilbird.engine.assets.AssetUtilities.fontSize;
import static com.evilbird.engine.common.text.CaseUtils.toSnakeCase;

/**
 * Provides access to the assets that are required to display a
 * {@link ControlPane}.
 *
 * @author Blair Butterworth
 */
public class ControlPaneAssets extends AssetBundle
{
    public ControlPaneAssets(AssetManager manager, WarcraftContext context) {
        super(manager, pathVariables(context));
        registerCommonTextures();
        registerFactionTextures();
        registerStringBundles();
        registerFonts();
    }

    private static Map<String, String> pathVariables(WarcraftContext context) {
        return Maps.of("faction", toSnakeCase(context.getFaction().name()));
    }
    
    private void registerCommonTextures() {
        register("icons", "data/textures/common/menu/icons.png");
        register("iconsDisabled", "data/textures/common/menu/icons_disabled.png");
        register("actionButton", "data/textures/common/menu/action.png");
        register("deselectButton", "data/textures/common/menu/unselect.png");
        register("buildingFill", "data/textures/common/menu/building_progress_bar.png");
        register("buildingBackground", "data/textures/common/menu/building_progress_background.png");
        register("manaFill", "data/textures/common/menu/mana_progress_bar.png");
        register("manaBackground", "data/textures/common/menu/mana_progress_background.png");
        register("healthProgressHigh", "data/textures/common/menu/health_bar_high.png");
        register("healthProgressMedium", "data/textures/common/menu/health_bar_medium.png");
        register("healthProgressLow", "data/textures/common/menu/health_bar_low.png");
        register("unitPanel", "data/textures/common/menu/selection.png");
    }

    private void registerFactionTextures() {
        register("actionPanel", "data/textures/${faction}/menu/action_panel.png");
        register("detailsPanel", "data/textures/${faction}/menu/details_panel.png");
        register("menuPanel", "data/textures/${faction}/menu/menu_panel.png");
        register("minimapPanel", "data/textures/${faction}/menu/minimap_panel.png");
        register("selectionPanel", "data/textures/${faction}/menu/selection_panel.png");
        register("buttonEnabled", "data/textures/${faction}/menu/button-thin-medium-normal.png");
        register("buttonSelected", "data/textures/${faction}/menu/button-thin-medium-pressed.png");
        register("buttonDisabled", "data/textures/${faction}/menu/button-thin-medium-grayed.png");
    }

    private void registerStringBundles() {
        register("detailStrings", "data/strings/common/menu/details", I18NBundle.class);
        register("nameStrings", "data/strings/common/menu/names", I18NBundle.class);
    }

    private void registerFonts() {
        register("font", "data/fonts/philosopher-small.ttf", BitmapFont.class, fontSize(14));
    }

    public IconSet getIcons() {
        return new IconSet(getTexture("icons"));
    }

    public IconSet getDisabledIcons() {
        return new IconSet(getTexture("iconsDisabled"));
    }

    public Drawable getActionButton() {
        return getDrawable("actionButton");
    }

    public Drawable getDeselectButton() {
        return getDrawable("deselectButton");
    }

    public Drawable getActionPanel() {
        return getDrawable("actionPanel");
    }

    public Drawable getDetailsPanel() {
        return getDrawable("detailsPanel");
    }

    public DetailsPaneStrings getDetailsPaneStrings() {
        I18NBundle detailsBundle = getStrings("detailStrings");
        I18NBundle namesBundle = getStrings("nameStrings");
        return new DetailsPaneStrings(detailsBundle, namesBundle);
    }

    public Drawable getMenuPanel() {
        return getDrawable("menuPanel");
    }

    public Drawable getMinimapPanel() {
        return getDrawable("minimapPanel");
    }

    public Drawable getSelectionPanel() {
        return getDrawable("selectionPanel");
    }

    public Drawable getUnitPanel() {
        return getDrawable("unitPanel");
    }

    public Drawable getButtonEnabled() {
        return getDrawable("buttonEnabled");
    }

    public Drawable getButtonSelected() {
        return getDrawable("buttonSelected");
    }

    public Drawable getButtonDisabled() {
        return getDrawable("buttonDisabled");
    }

    public Drawable getBuildingFill() {
        return getDrawable("buildingFill");
    }

    public Drawable getBuildingBackground() {
        return getDrawable("buildingBackground");
    }

    public Drawable getManaFill() {
        return getDrawable("manaFill");
    }

    public Drawable getManaBackground() {
        return getDrawable("manaBackground");
    }

    public Drawable getHealthProgressHigh() {
        return getDrawable("healthProgressHigh");
    }

    public Drawable getHealthProgressMedium() {
        return getDrawable("healthProgressMedium");
    }

    public Drawable getHealthProgressLow() {
        return getDrawable("healthProgressLow");
    }

    public BitmapFont getFont() {
        return getFont("font");
    }
}
