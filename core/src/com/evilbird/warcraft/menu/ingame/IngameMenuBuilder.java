/*
 * Copyright (c) 2019, Blair Butterworth
 *
 * This work is licensed under the MIT License. To view a copy of this
 * license, visit
 *
 *        https://opensource.org/licenses/MIT
 */

package com.evilbird.warcraft.menu.ingame;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.scenes.scene2d.ui.CheckBox.CheckBoxStyle;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.List;
import com.badlogic.gdx.scenes.scene2d.ui.ScrollPane.ScrollPaneStyle;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.Slider.SliderStyle;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.ui.TextField.TextFieldStyle;
import com.badlogic.gdx.scenes.scene2d.utils.BaseDrawable;
import com.badlogic.gdx.scenes.scene2d.utils.Drawable;
import com.evilbird.engine.common.graphics.TextureUtils;
import com.evilbird.engine.device.DeviceDisplay;
import com.evilbird.engine.item.specialized.ScrollBarPaneStyle;
import com.evilbird.engine.item.specialized.TextInputStyle;

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
        TextButton.TextButtonStyle textButtonStyle = new TextButton.TextButtonStyle();
        textButtonStyle.font = assets.getFont();
        textButtonStyle.fontColor = Color.WHITE;
        textButtonStyle.up = assets.getButtonEnabled();
        textButtonStyle.over = textButtonStyle.up;
        textButtonStyle.checked = textButtonStyle.up;
        textButtonStyle.checkedOver = textButtonStyle.up;
        textButtonStyle.disabled = assets.getButtonDisabled();
        textButtonStyle.down = assets.getButtonSelected();
        skin.add("default", textButtonStyle);
    }

    private void addTitleStyle(Skin skin) {
        Label.LabelStyle labelStyle = new Label.LabelStyle();
        labelStyle.font = assets.getFont();
        labelStyle.fontColor = Color.GOLD;
        skin.add("title", labelStyle);
    }

    private void addLabelStyle(Skin skin) {
        Label.LabelStyle defaultStyle = new Label.LabelStyle();
        defaultStyle.font = assets.getFont();
        defaultStyle.fontColor = Color.WHITE;
        skin.add("label", defaultStyle);
        skin.add("default", defaultStyle);

        Label.LabelStyle errorStyle = new Label.LabelStyle();
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
        defaultStyle.cursor = TextureUtils.getDrawable(2, 20, Color.WHITE);
        defaultStyle.background = assets.getTextFieldBackground();
        defaultStyle.borderColour = null;
        defaultStyle.borderColourFocused = Color.GOLD;
        skin.add("default", defaultStyle, TextFieldStyle.class);
        skin.add("default", defaultStyle, TextInputStyle.class);

        TextInputStyle errorStyle = new TextInputStyle();
        errorStyle.font = assets.getFont();
        errorStyle.fontColor = Color.WHITE;
        errorStyle.cursor = TextureUtils.getDrawable(2, 20, Color.WHITE);
        errorStyle.background = assets.getTextFieldBackground();
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

        ScrollBarPaneStyle errorStyle = new ScrollBarPaneStyle();
        errorStyle.hScroll = assets.getScrollHorizontal();
        errorStyle.hScrollKnob = assets.getScrollKnob();
        errorStyle.vScroll = assets.getScrollVertical();
        errorStyle.vScrollKnob = defaultStyle.hScrollKnob;
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
