/*
 * Copyright (c) 2020, Blair Butterworth
 *
 * This Source Code Form is subject to the terms of the Mozilla Public
 * License, v. 2.0. If a copy of the MPL was not distributed with this
 * file, You can obtain one at http://mozilla.org/MPL/2.0/.
 */

package com.evilbird.warcraft.menu.outro;

import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.scenes.scene2d.utils.Drawable;
import com.badlogic.gdx.utils.I18NBundle;
import com.evilbird.engine.assets.AssetBundle;
import com.evilbird.engine.audio.sound.Sound;
import com.evilbird.engine.common.collection.Maps;
import com.evilbird.warcraft.state.WarcraftContext;

import java.util.Map;

import static com.evilbird.engine.assets.AssetUtilities.fontSize;
import static com.evilbird.engine.common.text.CaseUtils.toSnakeCase;

/**
 * Provides the assets required to display an {@link OutroMenu}, as well as any
 * sound effects used by it.
 *
 * @author Blair Butterworth
 */
public class OutroMenuAssets extends AssetBundle
{
    public OutroMenuAssets(AssetManager assets, WarcraftContext context) {
        super(assets, pathVariables(context));
        register("data/textures/common/menu/button.png");
        register("data/sounds/common/menu/click.mp3");
        register("data/textures/common/menu/stats_progress_fill.png");
        register("data/textures/common/menu/stats_progress_background.png");
        register("data/textures/${faction}/menu/victory.png");
        register("data/textures/${faction}/menu/defeat.png");
        register("data/strings/common/menu/outro", I18NBundle.class);
        register("data/strings/common/menu/nations", I18NBundle.class);
        register("font", "data/fonts/philosopher-medium.ttf", BitmapFont.class, fontSize(16));
        register("font-large", "data/fonts/philosopher-extra-large.ttf", BitmapFont.class, fontSize(36));
    }

    private static Map<String, String> pathVariables(WarcraftContext context) {
        return Maps.of("faction", toSnakeCase(context.getFaction().name()));
    }

    public BitmapFont getFont() {
        return getFont("font");
    }

    public BitmapFont getFontLarge() {
        return getFont("font-large");
    }

    public OutroMenuStrings getStrings() {
        I18NBundle outroBundle = getStrings("outro");
        I18NBundle nationsBundle = getStrings("nations");
        return new OutroMenuStrings(outroBundle, nationsBundle);
    }

    public Sound getButtonClick() {
        return getSoundEffect("click.mp3");
    }

    public Drawable getButtonEnabled() {
        return getDrawable("button.png", 0, 0, 225, 30);
    }

    public Drawable getButtonDisabled() {
        return getDrawable("button.png", 0, 60, 225, 30);
    }

    public Drawable getButtonSelected() {
        return getDrawable("button.png", 0, 30, 225, 30);
    }

    public Drawable getVictoryBackground() {
        return getDrawable("victory.png");
    }

    public Drawable getDefeatBackground() {
        return getDrawable("defeat.png");
    }

    public Drawable getProgressFill() {
        return getDrawable("stats_progress_fill.png");
    }

    public Drawable getProgressBackground() {
        return getDrawable("stats_progress_background.png");
    }
}
