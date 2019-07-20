/*
 * Copyright (c) 2019, Blair Butterworth
 *
 * This work is licensed under the MIT License. To view a copy of this
 * license, visit
 *
 *        https://opensource.org/licenses/MIT
 */

package com.evilbird.warcraft.menu.outro;

import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.scenes.scene2d.utils.Drawable;
import com.badlogic.gdx.utils.I18NBundle;
import com.evilbird.engine.common.assets.AssetBundle;
import com.evilbird.warcraft.common.WarcraftContext;

import static com.evilbird.engine.common.assets.AssetUtilities.fontSize;

/**
 * Provides the assets required to display an {@link OutroMenu}, as well as any
 * sound effects used by it.
 *
 * @author Blair Butterworth
 */
public class OutroMenuAssets extends AssetBundle
{
    public OutroMenuAssets(AssetManager assets, WarcraftContext context) {
        super(assets, context);
        register("data/textures/common/menu/button.png");
        register("data/textures/common/menu/stats_progress_fill.png");
        register("data/textures/common/menu/stats_progress_background.png");
        register("data/textures/${faction}/menu/victory.png");
        register("data/textures/${faction}/menu/defeat.png");
        register("data/strings/common/menu/outro", I18NBundle.class);
        register("font", "data/fonts/philosopher.ttf", BitmapFont.class, fontSize(18));
        register("font-large", "data/fonts/philosopher-large.ttf", BitmapFont.class, fontSize(36));
    }

    public BitmapFont getFont() {
        return getFont("font");
    }

    public BitmapFont getFontLarge() {
        return getFont("font-large");
    }

    public OutroMenuStrings getStrings() {
        return new OutroMenuStrings(getStrings("outro"));
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
