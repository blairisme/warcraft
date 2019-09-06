/*
 * Copyright (c) 2019, Blair Butterworth
 *
 * This work is licensed under the MIT License. To view a copy of this
 * license, visit
 *
 *        https://opensource.org/licenses/MIT
 */

package com.evilbird.warcraft.menu.intro;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.utils.Drawable;
import com.evilbird.engine.device.DeviceDisplay;

/**
 * Creates a new {@link IntroMenu} instance whose visual presentation is
 * defined by the given {@link IntroMenuAssets}.
 *
 * @author Blair Butterworth
 */
public class IntroMenuBuilder
{
    private IntroMenuAssets assets;
    private DeviceDisplay display;

    public IntroMenuBuilder(DeviceDisplay display, IntroMenuAssets assets) {
        this.display = display;
        this.assets = assets;
    }

    public IntroMenu build() {
        Skin skin = getSkin(assets);
        return new IntroMenu(display, skin);
    }

    private Skin getSkin(IntroMenuAssets assets) {
        Skin skin = new Skin();
        addLabelStyle(skin, assets);
        addButtonStyle(skin, assets);
        addBackgroundStyle(skin, assets);
        return skin;
    }

    private void addBackgroundStyle(Skin skin, IntroMenuAssets assets) {
        Drawable background = assets.getBackground();
        skin.add("intro-background", background, Drawable.class);
    }

    private void addLabelStyle(Skin skin, IntroMenuAssets assets) {
        Label.LabelStyle labelStyle = new Label.LabelStyle();
        labelStyle.font = assets.getLargeFont();
        labelStyle.fontColor = Color.WHITE;
        skin.add("default", labelStyle);
    }

    private void addButtonStyle(Skin skin, IntroMenuAssets assets) {
        TextButton.TextButtonStyle textButtonStyle = new TextButton.TextButtonStyle();
        textButtonStyle.font = assets.getSmallFont();
        textButtonStyle.fontColor = Color.WHITE;
        textButtonStyle.up = assets.getButtonUp();
        textButtonStyle.over = textButtonStyle.up;
        textButtonStyle.checked = textButtonStyle.up;
        textButtonStyle.checkedOver = textButtonStyle.up;
        textButtonStyle.disabled = assets.getButtonDisabled();
        textButtonStyle.down = assets.getButtonDown();
        skin.add("default", textButtonStyle);
    }
}
