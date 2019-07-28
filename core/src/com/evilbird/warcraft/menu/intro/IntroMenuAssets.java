/*
 * Copyright (c) 2019, Blair Butterworth
 *
 * This work is licensed under the MIT License. To view a copy of this
 * license, visit
 *
 *        https://opensource.org/licenses/MIT
 */

package com.evilbird.warcraft.menu.intro;

import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.scenes.scene2d.utils.Drawable;
import com.badlogic.gdx.utils.I18NBundle;
import com.evilbird.engine.common.assets.AssetBundle;
import com.evilbird.engine.common.audio.MusicCombination;
import com.evilbird.engine.common.audio.MusicSequence;
import com.evilbird.engine.common.collection.Maps;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import static com.evilbird.engine.common.assets.AssetUtilities.fontSize;
import static com.evilbird.engine.common.text.CaseUtils.toSnakeCase;

/**
 * Defines the assets that are required to display an {@link IntroMenu}, as
 * well as any sound effects used by it, especially narration.
 *
 * @author Blair Butterworth
 */
public class IntroMenuAssets extends AssetBundle
{
    private  IntroMenuType type;

    public IntroMenuAssets(AssetManager manager, IntroMenuType type) {
        super(manager, pathVariables(type));
        this.type = type;

        register("background", "data/textures/${faction}/menu/${name}.png");
        register("font", "data/fonts/intro.ttf", BitmapFont.class, fontSize(20));
        register("button", "data/textures/common/menu/button.png");
        register("music", "data/music/4.mp3", Music.class);
        register("strings", "data/strings/${faction}/menu/${name}", I18NBundle.class);
        registerOptional("narration-1", "data/sounds/${faction}/menu/${name}.mp3", Music.class);
        registerOptional("narration-1", "data/sounds/${faction}/menu/${name}a.mp3", Music.class);
        registerOptional("narration-2", "data/sounds/${faction}/menu/${name}b.mp3", Music.class);
    }

    private static Map<String, String> pathVariables(IntroMenuType type) {
        return Maps.of("faction", toSnakeCase(type.getCampaign().getFaction().name()),
                "name", "intro" + type.getIndex());
    }

    public IntroMenuType getType() {
        return type;
    }

    public Drawable getBackground() {
        return getDrawable("background");
    }

    public Drawable getButtonUp() {
        return getDrawable("button", 0, 0, 225, 30);
    }

    public Drawable getButtonDown() {
        return getDrawable("button", 0, 30, 225, 30);
    }

    public Drawable getButtonDisabled() {
        return getDrawable("button", 0, 60, 225, 30);
    }

    public BitmapFont getFont() {
        return getFont("font");
    }

    public IntroMenuStrings getStrings() {
        return new IntroMenuStrings(getStrings("strings"));
    }

    public Music getNarration() {
        Music narration = new MusicSequence(getNarrationSequence());
        narration.setVolume(1f);

        Music background = getMusic("music");
        background.setVolume(0.7f);

        return new MusicCombination(narration, background);
    }

    private List<Music> getNarrationSequence() {
        List<Music> sequence = new ArrayList<>();

        if (isRegistered("narration-1")) {
            sequence.add(getMusic("narration-1"));
        }
        if (isRegistered("narration-2")) {
            sequence.add(getMusic("narration-2"));
        }
        return sequence;
    }
}