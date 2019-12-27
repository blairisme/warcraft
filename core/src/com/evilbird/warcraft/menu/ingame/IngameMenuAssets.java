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
 * Defines the assets that are required to display an {@link IngameMenu}, as
 * well as any sound effects used by it, especially narration.
 *
 * @author Blair Butterworth
 */
public class IngameMenuAssets extends AssetBundle
{
    public IngameMenuAssets(AssetManager assets, WarcraftContext context) {
        super(assets, pathVariables(context));
        register("data/textures/${faction}/menu/button-large-normal.png");
        register("data/textures/${faction}/menu/button-large-grayed.png");
        register("data/textures/${faction}/menu/button-large-pressed.png");
        register("data/textures/${faction}/menu/checkbox_disabled.png");
        register("data/textures/${faction}/menu/checkbox_selected.png");
        register("data/textures/${faction}/menu/checkbox_selected_pressed.png");
        register("data/textures/${faction}/menu/checkbox_unselected.png");
        register("data/textures/${faction}/menu/checkbox_unselected_pressed.png");
        register("data/textures/${faction}/menu/text_panel_normal.png");
        register("data/textures/${faction}/menu/panel_normal.png");
        register("data/textures/${faction}/menu/panel_wide.png");
        register("data/textures/${faction}/menu/panel_small.png");
        register("data/textures/${faction}/menu/scroll_knob.png");
        register("data/textures/${faction}/menu/scroll_horizontal.png");
        register("data/textures/${faction}/menu/scroll_vertical.png");
        register("data/sounds/common/menu/click.mp3");
        register("strings", "data/strings/common/menu/ingame", I18NBundle.class);
        register("objectives", "data/strings/${faction}/menu/objectives", I18NBundle.class);
        register("font-medium", "data/fonts/philosopher-medium.ttf", BitmapFont.class, fontSize(16));
        register("font-small", "data/fonts/philosopher-small.ttf", BitmapFont.class, fontSize(14));
        register("font-tiny", "data/fonts/philosopher-tiny.ttf", BitmapFont.class, fontSize(12));
    }

    private static Map<String, String> pathVariables(WarcraftContext context) {
        return Maps.of("faction", toSnakeCase(context.getFaction().name()));
    }

    public Sound getButtonClick() {
        return getSoundEffect("click.mp3");
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

    public Drawable getCheckboxDisabled() {
        return getDrawable("checkbox_disabled.png");
    }

    public Drawable getCheckboxSelected() {
        return getDrawable("checkbox_selected.png");
    }

    public Drawable getCheckboxSelectedPressed() {
        return getDrawable("checkbox_selected_pressed.png");
    }

    public Drawable getCheckboxUnselected() {
        return getDrawable("checkbox_unselected.png");
    }

    public Drawable getCheckboxUnselectedPressed() {
        return getDrawable("checkbox_unselected_pressed.png");
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

    public Drawable getScrollKnob() {
        return getDrawable("scroll_knob.png");
    }

    public Drawable getScrollHorizontal() {
        return getDrawable("scroll_horizontal.png");
    }

    public Drawable getScrollVertical() {
        return getDrawable("scroll_vertical.png");
    }

    public BitmapFont getFont() {
        return getFont("font-medium");
    }

    public BitmapFont getFontSmall() {
        return getFont("font-small");
    }

    public BitmapFont getFontTiny() {
        return getFont("font-tiny");
    }

    public IngameMenuStrings getStrings() {
        I18NBundle strings = getStrings("strings");
        I18NBundle objectives = getStrings("objectives");
        return new IngameMenuStrings(strings, objectives);
    }
}
