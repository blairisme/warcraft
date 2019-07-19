/*
 * Copyright (c) 2019, Blair Butterworth
 *
 * This work is licensed under the MIT License. To view a copy of this
 * license, visit
 *
 *        https://opensource.org/licenses/MIT
 */

package com.evilbird.warcraft.menu.main;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.evilbird.engine.common.graphics.Fonts;
import com.evilbird.engine.device.DeviceDisplay;

/**
 * Creates a new {@link MainMenu} instance whose visual presentation is
 * defined by the given {@link MainMenuAssets}.
 *
 * @author Blair Butterworth
 */
public class MainMenuBuilder
{
    private MainMenuAssets assets;
    private DeviceDisplay display;

    public MainMenuBuilder(DeviceDisplay display, MainMenuAssets assets) {
        this.display = display;
        this.assets = assets;
    }

    public MainMenu build() {
        Skin skin = getSkin();
        return new MainMenu(display, skin);
    }

    private Skin getSkin() {
        Skin skin = new Skin();
        skin.add("default", getMainMenuStyle(), MainMenuStyle.class);
        skin.add("default", getTextButtonStyle(), TextButton.TextButtonStyle.class);
        return skin;
    }

    private MainMenuStyle getMainMenuStyle() {
        MainMenuStyle mainMenuStyle = new MainMenuStyle();
        mainMenuStyle.background = assets.getBackground();
        mainMenuStyle.music = assets.getMusic();
        return mainMenuStyle;
    }

    private TextButton.TextButtonStyle getTextButtonStyle() {
        TextButton.TextButtonStyle textButtonStyle = new TextButton.TextButtonStyle();
        textButtonStyle.font = Fonts.ARIAL;
        textButtonStyle.fontColor = Color.WHITE;
        textButtonStyle.up = assets.getButtonEnabled();
        textButtonStyle.over = textButtonStyle.up;
        textButtonStyle.checked = textButtonStyle.up;
        textButtonStyle.checkedOver = textButtonStyle.up;
        textButtonStyle.disabled = assets.getButtonDisabled();
        textButtonStyle.down = assets.getButtonSelected();
        return textButtonStyle;
    }
}
