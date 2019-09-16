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
import com.badlogic.gdx.scenes.scene2d.ui.TextField;
import com.badlogic.gdx.scenes.scene2d.utils.BaseDrawable;
import com.badlogic.gdx.scenes.scene2d.utils.Drawable;
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

    private void addLabelStyle(Skin skin) {
        Label.LabelStyle labelStyle = new Label.LabelStyle();
        labelStyle.font = assets.getFont();
        labelStyle.fontColor = Color.GOLD;
        skin.add("default", labelStyle);
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
        TextField.TextFieldStyle textFieldStyle = new TextField.TextFieldStyle();
        textFieldStyle.font = assets.getFont();
        textFieldStyle.fontColor = Color.GOLD;
        textFieldStyle.background = assets.getTextFieldBackground();
        skin.add("default", textFieldStyle);
    }

    private void addMenuStyle(Skin skin) {
        skin.add("menu-background-normal", assets.getBackgroundNormal(), Drawable.class);
        skin.add("menu-background-wide", assets.getBackgroundWide(), Drawable.class);
        skin.add("menu-background-small", assets.getBackgroundSmall(), Drawable.class);
    }

    private void addScrollPaneStyle(Skin skin) {
        ScrollPaneStyle style = new ScrollPaneStyle();
        style.hScroll = assets.getScrollHorizontal();
        style.hScrollKnob = assets.getScrollKnob();
        style.vScroll = assets.getScrollVertical();
        style.vScrollKnob = style.hScrollKnob;
        skin.add("default", style);
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
