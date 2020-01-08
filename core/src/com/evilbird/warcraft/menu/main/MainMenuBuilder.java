/*
 * Copyright (c) 2020, Blair Butterworth
 *
 * This Source Code Form is subject to the terms of the Mozilla Public
 * License, v. 2.0. If a copy of the MPL was not distributed with this
 * file, You can obtain one at http://mozilla.org/MPL/2.0/.
 */

package com.evilbird.warcraft.menu.main;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton.TextButtonStyle;
import com.evilbird.engine.common.control.LabelButtonStyle;
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
        addMainMenuStyle(skin);
        addTextButtonStyle(skin);
        return skin;
    }

    private void addMainMenuStyle(Skin skin) {
        MainMenuStyle mainMenuStyle = new MainMenuStyle();
        mainMenuStyle.background = assets.getBackground();
        mainMenuStyle.music = assets.getMusic();
        skin.add("default", mainMenuStyle, MainMenuStyle.class);
    }

    private void addTextButtonStyle(Skin skin) {
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
}
