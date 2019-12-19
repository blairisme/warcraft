/*
 * Copyright (c) 2019, Blair Butterworth
 *
 * This work is licensed under the MIT License. To view a copy of this
 * license, visit
 *
 *        https://opensource.org/licenses/MIT
 */

package com.evilbird.warcraft.object.display.views.control;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.scenes.scene2d.ui.Label.LabelStyle;
import com.badlogic.gdx.scenes.scene2d.ui.ProgressBar.ProgressBarStyle;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton.TextButtonStyle;
import com.badlogic.gdx.scenes.scene2d.utils.Drawable;
import com.evilbird.warcraft.object.display.components.actions.ActionButtonStyle;
import com.evilbird.warcraft.object.display.components.common.HealthBarStyle;
import com.evilbird.warcraft.object.display.components.common.UnitPaneStyle;
import com.evilbird.warcraft.object.display.components.map.MapPaneStyle;
import com.evilbird.warcraft.object.display.components.status.details.DetailsPaneStyle;
import com.evilbird.warcraft.object.display.components.status.selection.SelectionButtonStyle;

/**
 * Creates a new {@link ControlPane} instance whose visual presentation is
 * defined by the given {@link ControlPaneAssets}.
 *
 * @author Blair Butterworth
 */
public class ControlPaneBuilder
{
    private ControlPaneAssets assets;
    private boolean showActions;
    private boolean showMenuButton;
    private boolean showMiniMap;
    private boolean showStatus;

    public ControlPaneBuilder(ControlPaneAssets assets) {
        this.assets = assets;
        this.showActions = true;
        this.showMenuButton = true;
        this.showMiniMap = true;
        this.showStatus = true;
    }

    public ControlPane build() {
        return new ControlPane(getSkin());
    }

    public void showActions(boolean enabled) {
        this.showActions = enabled;
    }

    public void showMenuOption(boolean enabled) {
        this.showMenuButton = enabled;
    }

    public void showMiniMap(boolean enabled) {
        this.showMiniMap = enabled;
    }

    public void showStatus(boolean enabled) {
        this.showStatus = enabled;
    }

    private Skin getSkin() {
        Skin skin = new Skin();
        skin.add("default", getFontStyle());
        skin.add("default", getHealthBarStyle());
        skin.add("default", getSelectionButtonStyle());
        skin.add("default", getDetailsPaneStyle());
        skin.add("default", getControlPaneStyle());
        skin.add("default", getUnitPaneStyle());
        skin.add("default", getMinimapStyle());
        skin.add("button-thin-medium", getButtonStyle());
        skin.add("building-progress", getBuildingProgressStyle());
        skin.add("action-button", getActionButtonStyle());
        skin.add("action-panel", assets.getActionPanel(), Drawable.class);
        skin.add("menu-panel", assets.getMenuPanel(), Drawable.class);
        skin.add("selection-panel", assets.getSelectionPanel(), Drawable.class);
        addManaBarStyle(skin);
        return skin;
    }

    private ControlPaneStyle getControlPaneStyle() {
        ControlPaneStyle style = new ControlPaneStyle();
        style.showActions = this.showActions;
        style.showMenuButton = this.showMenuButton;
        style.showMiniMap = this.showMiniMap;
        style.showStatus = this.showStatus;
        return style;
    }

    private LabelStyle getFontStyle() {
        LabelStyle style = new LabelStyle();
        style.font = assets.getFont();
        style.fontColor = Color.WHITE;
        return style;
    }

    private TextButtonStyle getButtonStyle() {
        TextButtonStyle textButtonStyle = new TextButtonStyle();
        textButtonStyle.font = assets.getFont();
        textButtonStyle.fontColor = Color.WHITE;
        textButtonStyle.up = assets.getButtonEnabled();
        textButtonStyle.over = textButtonStyle.up;
        textButtonStyle.checked = textButtonStyle.up;
        textButtonStyle.checkedOver = textButtonStyle.up;
        textButtonStyle.disabled = assets.getButtonDisabled();
        textButtonStyle.down = assets.getButtonSelected();
        return textButtonStyle;
    }

    private ProgressBarStyle getBuildingProgressStyle() {
        ProgressBarStyle style = new ProgressBarStyle();
        style.background = assets.getBuildingBackground();
        style.knob = assets.getBuildingFill();
        style.knobBefore = style.knob;
        return style;
    }

    private void addManaBarStyle(Skin skin) {
        ProgressBarStyle progressBarStyle = new ProgressBarStyle();
        progressBarStyle.background = assets.getManaBackground();
        progressBarStyle.knob = assets.getManaFill();
        progressBarStyle.knobBefore = progressBarStyle.knob;
        skin.add("mana-bar", progressBarStyle, ProgressBarStyle.class);

        LabelStyle labelStyle = new LabelStyle();
        labelStyle.font = assets.getFont();
        labelStyle.fontColor = Color.WHITE;
        skin.add("mana-bar", labelStyle, LabelStyle.class);
    }

    private DetailsPaneStyle getDetailsPaneStyle() {
        DetailsPaneStyle style = new DetailsPaneStyle();
        style.strings = assets.getDetailsPaneStrings();
        style.icons = assets.getIcons();
        style.background = assets.getDetailsPanel();
        style.productionBackground = assets.getActionButton();
        return style;
    }

    private HealthBarStyle getHealthBarStyle() {
        HealthBarStyle style = new HealthBarStyle();
        style.highBar = assets.getHealthProgressHigh();
        style.mediumBar = assets.getHealthProgressMedium();
        style.lowBar = assets.getHealthProgressLow();
        return style;
    }

    private SelectionButtonStyle getSelectionButtonStyle() {
        SelectionButtonStyle style =  new SelectionButtonStyle();
        style.closeButtonEnabled = assets.getDeselectButton();
        style.closeButtonDisabled = style.closeButtonEnabled;
        style.closeButtonSelected = style.closeButtonEnabled;
        return style;
    }

    private ActionButtonStyle getActionButtonStyle() {
        ActionButtonStyle style = new ActionButtonStyle();
        style.icons = assets.getIcons();
        style.disabledIcons = assets.getDisabledIcons();
        style.background = assets.getActionButton();
        return style;
    }

    private UnitPaneStyle getUnitPaneStyle() {
        UnitPaneStyle style = new UnitPaneStyle();
        style.icons = assets.getIcons();
        style.background = assets.getUnitPanel();
        return style;
    }

    private MapPaneStyle getMinimapStyle() {
        MapPaneStyle style = new MapPaneStyle();
        style.background = assets.getMinimapPanel();
        style.mapSize = new Vector2(128.0f, 128.0f);
        return style;
    }
}
