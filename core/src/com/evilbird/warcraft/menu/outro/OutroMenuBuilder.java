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
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.ProgressBar;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.utils.Drawable;
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
        Label.LabelStyle labelStyle = new Label.LabelStyle();
        labelStyle.font = assets.getFont();
        labelStyle.fontColor = Color.WHITE;
        skin.add("default", labelStyle);
        skin.add("progress-outro", labelStyle);

        Label.LabelStyle largeStyle = new Label.LabelStyle();
        largeStyle.font = assets.getFontLarge();
        largeStyle.fontColor = Color.WHITE;
        skin.add("font-large", largeStyle);
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

    private void addProgressBarStyle(Skin skin) {
        ProgressBar.ProgressBarStyle style = new ProgressBar.ProgressBarStyle();
        style.background = assets.getProgressBackground();
        style.knob = assets.getProgressFill();
        style.knobBefore = style.knob;
        skin.add("progress-outro", style);
    }
}
