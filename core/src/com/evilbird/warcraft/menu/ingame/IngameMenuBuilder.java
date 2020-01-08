/*
 * Copyright (c) 2020, Blair Butterworth
 *
 * This Source Code Form is subject to the terms of the Mozilla Public
 * License, v. 2.0. If a copy of the MPL was not distributed with this
 * file, You can obtain one at http://mozilla.org/MPL/2.0/.
 */

package com.evilbird.warcraft.menu.ingame;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.scenes.scene2d.ui.CheckBox.CheckBoxStyle;
import com.badlogic.gdx.scenes.scene2d.ui.Label.LabelStyle;
import com.badlogic.gdx.scenes.scene2d.ui.List;
import com.badlogic.gdx.scenes.scene2d.ui.ScrollPane.ScrollPaneStyle;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.Slider.SliderStyle;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton.TextButtonStyle;
import com.badlogic.gdx.scenes.scene2d.ui.TextField.TextFieldStyle;
import com.badlogic.gdx.scenes.scene2d.utils.BaseDrawable;
import com.badlogic.gdx.scenes.scene2d.utils.Drawable;
import com.evilbird.engine.common.control.LabelButtonStyle;
import com.evilbird.engine.common.control.ScrollBarPaneStyle;
import com.evilbird.engine.common.control.TextInputStyle;
import com.evilbird.engine.common.graphics.DrawableUtils;
import com.evilbird.engine.common.graphics.TextureUtils;
import com.evilbird.engine.device.DeviceDisplay;

/**
 * Creates a new {@link IngameMenu} instance whose visual presentation is
 * defined by the given {@link IngameMenuAssets}.
 *
 * @author Blair Butterworth
 */
public class IngameMenuBuilder
{
    private DeviceDisplay display;
    private IngameMenuAssets assets;

    public IngameMenuBuilder(DeviceDisplay display, IngameMenuAssets assets) {
        this.assets = assets;
        this.display = display;
    }

    public IngameMenu build() {
        return new IngameMenu(display, getSkin());
    }

    private Skin getSkin() {
        Skin skin = new Skin();
        addButtonStyle(skin);
        addTitleStyle(skin);
        addLabelStyle(skin);
        addListStyle(skin);
        addTextFieldStyle(skin);
        addMenuStyle(skin);
        addScrollPaneStyle(skin);
        addSliderStyle(skin);
        addCheckBoxStyle(skin);
        return skin;
    }

    private void addButtonStyle(Skin skin) {
        LabelButtonStyle defaultStyle = new LabelButtonStyle();
        defaultStyle.font = assets.getFont();
        defaultStyle.fontColor = Color.WHITE;
        defaultStyle.up = assets.getButtonEnabled();
        defaultStyle.over = defaultStyle.up;
        defaultStyle.checked = defaultStyle.up;
        defaultStyle.checkedOver = defaultStyle.up;
        defaultStyle.disabled = assets.getButtonDisabled();
        defaultStyle.down = assets.getButtonSelected();
        defaultStyle.borderColour = null;
        defaultStyle.borderColourFocused = Color.GOLD;
        defaultStyle.clickSound = assets.getButtonClick();
        skin.add("default", defaultStyle, TextButtonStyle.class);
        skin.add("default", defaultStyle, LabelButtonStyle.class);

        LabelButtonStyle errorStyle = new LabelButtonStyle(defaultStyle);
        errorStyle.borderColour = Color.RED;
        errorStyle.borderColourFocused = Color.RED;
        skin.add("error", errorStyle, TextButtonStyle.class);
        skin.add("error", errorStyle, LabelButtonStyle.class);
    }

    private void addTitleStyle(Skin skin) {
        LabelStyle labelStyle = new LabelStyle();
        labelStyle.font = assets.getFont();
        labelStyle.fontColor = Color.GOLD;
        skin.add("title", labelStyle);
    }

    private void addLabelStyle(Skin skin) {
        LabelStyle defaultStyle = new LabelStyle();
        defaultStyle.font = assets.getFont();
        defaultStyle.fontColor = Color.WHITE;
        skin.add("label", defaultStyle);
        skin.add("default", defaultStyle);

        LabelStyle errorStyle = new LabelStyle();
        errorStyle.font = assets.getFontSmall();
        errorStyle.fontColor = Color.RED;
        skin.add("error", errorStyle);
    }

    private void addListStyle(Skin skin) {
        List.ListStyle listStyle = new List.ListStyle();
        listStyle.font = assets.getFontSmall();
        listStyle.fontColorSelected = Color.GOLD;
        listStyle.fontColorUnselected = Color.WHITE;
        listStyle.background = assets.getListBackground();
        listStyle.selection = new BaseDrawable();
        skin.add("default", listStyle);
    }

    private void addTextFieldStyle(Skin skin) {
        TextInputStyle defaultStyle = new TextInputStyle();
        defaultStyle.font = assets.getFont();
        defaultStyle.fontColor = Color.WHITE;
        defaultStyle.cursor = DrawableUtils.getDrawable(TextureUtils.getRectangle(2, 20, Color.WHITE));
        defaultStyle.background = assets.getTextFieldBackground();
        defaultStyle.borderColour = null;
        defaultStyle.borderColourFocused = Color.GOLD;
        skin.add("default", defaultStyle, TextFieldStyle.class);
        skin.add("default", defaultStyle, TextInputStyle.class);

        TextInputStyle errorStyle = new TextInputStyle(defaultStyle);
        errorStyle.borderColour = Color.RED;
        errorStyle.borderColourFocused = Color.RED;
        skin.add("error", errorStyle, TextFieldStyle.class);
        skin.add("error", errorStyle, TextInputStyle.class);
    }

    private void addMenuStyle(Skin skin) {
        skin.add("menu-background-normal", assets.getBackgroundNormal(), Drawable.class);
        skin.add("menu-background-wide", assets.getBackgroundWide(), Drawable.class);
        skin.add("menu-background-small", assets.getBackgroundSmall(), Drawable.class);
    }

    private void addScrollPaneStyle(Skin skin) {
        ScrollBarPaneStyle defaultStyle = new ScrollBarPaneStyle();
        defaultStyle.hScroll = assets.getScrollHorizontal();
        defaultStyle.hScrollKnob = assets.getScrollKnob();
        defaultStyle.vScroll = assets.getScrollVertical();
        defaultStyle.vScrollKnob = defaultStyle.hScrollKnob;
        defaultStyle.borderColour = null;
        defaultStyle.borderColourFocused = Color.GOLD;
        skin.add("default", defaultStyle, ScrollPaneStyle.class);
        skin.add("default", defaultStyle, ScrollBarPaneStyle.class);

        ScrollBarPaneStyle errorStyle = new ScrollBarPaneStyle(defaultStyle);
        errorStyle.borderColour = Color.RED;
        errorStyle.borderColourFocused = Color.RED;
        skin.add("error", errorStyle, ScrollPaneStyle.class);
        skin.add("error", errorStyle, ScrollBarPaneStyle.class);
    }

    private void addSliderStyle(Skin skin) {
        SliderStyle style = new SliderStyle();
        style.background = assets.getScrollHorizontal();
        style.knob = assets.getScrollKnob();
        skin.add("default-horizontal", style);
    }

    private void addCheckBoxStyle(Skin skin) {
        CheckBoxStyle style = new CheckBoxStyle();
        style.font = assets.getFont();
        style.fontColor = Color.WHITE;
        style.checkboxOn = assets.getCheckboxSelected();
        style.checkboxOnOver = assets.getCheckboxSelectedPressed();
        style.checkboxOnDisabled = assets.getCheckboxDisabled();
        style.checkboxOff = assets.getCheckboxUnselected();
        style.checkboxOver = assets.getCheckboxUnselectedPressed();
        style.checkboxOffDisabled = assets.getCheckboxDisabled();
        skin.add("default", style);
    }
}
