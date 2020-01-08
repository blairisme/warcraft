/*
 * Copyright (c) 2020, Blair Butterworth
 *
 * This Source Code Form is subject to the terms of the Mozilla Public
 * License, v. 2.0. If a copy of the MPL was not distributed with this
 * file, You can obtain one at http://mozilla.org/MPL/2.0/.
 */

package com.evilbird.warcraft.menu.main;

import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.scenes.scene2d.utils.Drawable;
import com.badlogic.gdx.utils.I18NBundle;
import com.evilbird.engine.assets.AssetBundle;
import com.evilbird.engine.audio.music.Music;
import com.evilbird.engine.audio.sound.Sound;

import static com.evilbird.engine.assets.AssetUtilities.fontSize;

/**
 * Provides the assets required to display an {@link MainMenu}, as well as any
 * sound effects used by it.
 *
 * @author Blair Butterworth
 */
public class MainMenuAssets extends AssetBundle
{
    public MainMenuAssets(AssetManager assetManager) {
        super(assetManager);
        register("data/textures/common/menu/button.png");
        register("data/textures/common/menu/menu.png");
        register("data/sounds/common/menu/click.mp3");
        register("data/music/13.mp3", Music.class);
        register("data/strings/common/menu/main", I18NBundle.class);
        register("font", "data/fonts/philosopher-medium.ttf", BitmapFont.class, fontSize(16));
    }

    public Drawable getBackground() {
        return getDrawable("menu.png");
    }

    public Sound getButtonClick() {
        return getSoundEffect("click.mp3");
    }

    public Drawable getButtonEnabled() {
        return getDrawable("button.png", 0, 0, 224, 28);
    }

    public Drawable getButtonDisabled() {
        return getDrawable("button.png", 0, 56, 224, 28);
    }

    public Drawable getButtonSelected() {
        return getDrawable("button.png", 0, 28, 224, 28);
    }

    public Music getMusic() {
        return getMusic("13.mp3");
    }

    public MainMenuStrings getStrings() {
        return new MainMenuStrings(getStrings("main"));
    }

    public BitmapFont getFont() {
        return getFont("font");
    }
}
