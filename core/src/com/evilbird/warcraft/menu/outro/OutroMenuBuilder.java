/*
 * Copyright (c) 2019, Blair Butterworth
 *
 * This work is licensed under the MIT License. To view a copy of this
 * license, visit
 *
 *        https://opensource.org/licenses/MIT
 */

package com.evilbird.warcraft.menu.outro;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.scenes.scene2d.ui.Label.LabelStyle;
import com.badlogic.gdx.scenes.scene2d.ui.ProgressBar.ProgressBarStyle;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton.TextButtonStyle;
import com.badlogic.gdx.scenes.scene2d.utils.Drawable;
import com.evilbird.engine.common.control.LabelButtonStyle;
import com.evilbird.engine.device.DeviceDisplay;

/**
 * Creates a new {@link OutroMenu} instance whose visual presentation is
 * defined by the given {@link OutroMenuAssets}.
 *
 * @author Blair Butterworth
 */
public class OutroMenuBuilder
{
    private OutroMenuAssets assets;
    private DeviceDisplay display;

    public OutroMenuBuilder(DeviceDisplay display, OutroMenuAssets assets) {
        this.display = display;
        this.assets = assets;
    }

    public OutroMenu build() {
        Skin skin = getSkin();
        return new OutroMenu(display, skin);
    }

    private Skin getSkin() {
        Skin skin = new Skin();
        addLabelStyle(skin);
        addButtonStyle(skin);
        addBackgroundStyle(skin);
        addProgressBarStyle(skin);
        return skin;
    }

    private void addBackgroundStyle(Skin skin) {
        skin.add("background-defeat", assets.getDefeatBackground(), Drawable.class);
        skin.add("background-victory", assets.getVictoryBackground(), Drawable.class);
    }

    private void addLabelStyle(Skin skin) {
        LabelStyle labelStyle = new LabelStyle();
        labelStyle.font = assets.getFont();
        labelStyle.fontColor = Color.WHITE;
        skin.add("default", labelStyle);
        skin.add("progress-outro", labelStyle);

        LabelStyle largeStyle = new LabelStyle();
        largeStyle.font = assets.getFontLarge();
        largeStyle.fontColor = Color.WHITE;
        skin.add("font-large", largeStyle);
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
        defaultStyle.clickSound = assets.getButtonClick();
        skin.add("default", defaultStyle, TextButtonStyle.class);
        skin.add("default", defaultStyle, LabelButtonStyle.class);
    }

    private void addProgressBarStyle(Skin skin) {
        ProgressBarStyle style = new ProgressBarStyle();
        style.background = assets.getProgressBackground();
        style.knob = assets.getProgressFill();
        style.knobBefore = style.knob;
        skin.add("progress-outro", style);
    }
}
