/*
 * Copyright (c) 2019, Blair Butterworth
 *
 * This work is licensed under the MIT License. To view a copy of this
 * license, visit
 *
 *        https://opensource.org/licenses/MIT
 */

package com.evilbird.warcraft.menu.ingame;

import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.scenes.scene2d.utils.Drawable;
import com.badlogic.gdx.utils.I18NBundle;
import com.evilbird.engine.common.assets.AssetBundle;
import com.evilbird.warcraft.common.WarcraftContext;

/**
 * Defines the assets that are required to display an {@link IngameMenu}, as
 * well as any sound effects used by it, especially narration.
 *
 * @author Blair Butterworth
 */
public class IngameMenuAssets extends AssetBundle
{
    public IngameMenuAssets(AssetManager assets, WarcraftContext context) {
        super(assets, context);
        register("data/textures/${faction}/menu/button-large-normal.png");
        register("data/textures/${faction}/menu/button-large-grayed.png");
        register("data/textures/${faction}/menu/button-large-pressed.png");
        register("data/textures/${faction}/menu/text_panel_normal.png");
        register("data/textures/${faction}/menu/panel_normal.png");
        register("data/textures/${faction}/menu/panel_wide.png");
        register("data/textures/${faction}/menu/panel_small.png");
        register("data/sounds/common/menu/click.mp3");
        register("data/strings/common/menu/ingame", I18NBundle.class);
    }

    public Drawable getButtonEnabled() {
        return getDrawable("button-large-normal.png");
    }

    public Drawable getButtonDisabled() {
        return getDrawable("button-large-grayed.png");
    }

    public Drawable getButtonSelected() {
        return getDrawable("button-large-pressed.png");
    }

    public Drawable getListBackground() {
        return getTiledDrawable("text_panel_normal.png");
    }

    public Drawable getTextFieldBackground() {
        return getDrawable("text_panel_normal.png");
    }

    public Drawable getBackgroundNormal() {
        return getDrawable("panel_normal.png");
    }

    public Drawable getBackgroundWide() {
        return getDrawable("panel_wide.png");
    }

    public Drawable getBackgroundSmall() {
        return getDrawable("panel_small.png");
    }

    public IngameMenuStrings getStrings() {
        return new IngameMenuStrings(getStrings("ingame"));
    }
}
