/*
 * Copyright (c) 2019, Blair Butterworth
 *
 * This work is licensed under the MIT License. To view a copy of this
 * license, visit
 *
 *        https://opensource.org/licenses/MIT
 */

package com.evilbird.warcraft.item.ui.display.control;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.ProgressBar;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.utils.Drawable;
import com.evilbird.warcraft.item.ui.display.control.actions.ActionButtonStyle;
import com.evilbird.warcraft.item.ui.display.control.common.HealthBarStyle;
import com.evilbird.warcraft.item.ui.display.control.common.UnitPaneStyle;
import com.evilbird.warcraft.item.ui.display.control.status.details.DetailsPaneStyle;
import com.evilbird.warcraft.item.ui.display.control.status.selection.SelectionButtonStyle;

/**
 * Creates a new {@link ControlPane} instance whose visual presentation is
 * defined by the given {@link ControlPaneAssets}.
 *
 * @author Blair Butterworth
 */
public class ControlPaneBuilder
{
    private ControlPaneAssets assets;

    public ControlPaneBuilder(ControlPaneAssets assets) {
        this.assets = assets;
    }

    public ControlPane newControlPane() {
        Skin skin = getSkin(false);
        return new ControlPane(skin);
    }

    public ControlPane newCompactControlPane() {
        Skin skin = getSkin(true);
        return new ControlPane(skin);
    }

    private Skin getSkin(boolean compact) {
        Skin skin = new Skin();
        skin.add("default", getFontStyle());
        skin.add("default", getHealthBarStyle());
        skin.add("default", getSelectionButtonStyle());
        skin.add("default", getDetailsPaneStyle());
        skin.add("default", getControlPaneStyle(compact));
        skin.add("default", getUnitPaneStyle());
        skin.add("button-thin-medium", getButtonStyle());
        skin.add("building-progress", getBuildingProgressStyle());
        skin.add("action-button", getActionButtonStyle());
        skin.add("action-panel", assets.getActionPanel(), Drawable.class);
        skin.add("menu-panel", assets.getMenuPanel(), Drawable.class);
        skin.add("minimap-panel", assets.getMinimapPanel(), Drawable.class);
        skin.add("selection-panel", assets.getSelectionPanel(), Drawable.class);
        return skin;
    }

    private ControlPaneStyle getControlPaneStyle(boolean compact) {
        ControlPaneStyle style = new ControlPaneStyle();
        style.showActions = true;
        style.showStatus = true;
        style.showMenuButton = !compact;
        style.showMiniMap = !compact;
        return style;
    }

    private Label.LabelStyle getFontStyle() {
        Label.LabelStyle style = new Label.LabelStyle();
        style.font = assets.getFont();
        style.fontColor = Color.WHITE;
        return style;
    }

    private TextButton.TextButtonStyle getButtonStyle() {
        TextButton.TextButtonStyle textButtonStyle = new TextButton.TextButtonStyle();
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

    private ProgressBar.ProgressBarStyle getBuildingProgressStyle() {
        ProgressBar.ProgressBarStyle style = new ProgressBar.ProgressBarStyle();
        style.background = assets.getBuildingBackground();
        style.knob = assets.getBuildingFill();
        style.knobBefore = style.knob;
        return style;
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
}
