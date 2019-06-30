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
import com.badlogic.gdx.assets.loaders.FileHandleResolver;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.scenes.scene2d.utils.Drawable;
import com.badlogic.gdx.utils.I18NBundle;
import com.evilbird.engine.common.audio.LazyLoadedMusic;
import com.evilbird.engine.common.audio.MusicCombination;
import com.evilbird.engine.common.audio.MusicSequence;

import java.util.List;

import static com.evilbird.engine.common.graphics.TextureUtils.getDrawable;
import static java.util.stream.Collectors.toList;

/**
 * Defines the assets that are required to display an {@link IntroMenu}, as
 * well as any sound effects used by it, especially narration.
 *
 * @author Blair Butterworth
 */
public class IntroMenuAssets
{
    private AssetManager assets;
    private IntroMenuAssetManifest manifest;

    public IntroMenuAssets(AssetManager assets, IntroMenuType type) {
        this.assets = assets;
        this.manifest = new IntroMenuAssetManifest(type);
    }

    public Drawable getBackground() {
        return getDrawable(assets, manifest.getBackground());
    }

    public Drawable getButtonUp() {
        return getDrawable(assets, manifest.getButton(), 0, 0, 225, 30);
    }

    public Drawable getButtonDown() {
        return getDrawable(assets, manifest.getButton(), 0, 30, 225, 30);
    }

    public Drawable getButtonDisabled() {
        return getDrawable(assets, manifest.getButton(), 0, 60, 225, 30);
    }

    public BitmapFont getFont() {
        return assets.get(manifest.getFont(), BitmapFont.class);
    }

    public Music getNarration() {
        FileHandleResolver resolver = assets.getFileHandleResolver();

        List<String> paths = manifest.getNarration();
        List<Music> sequence = paths.stream().map(path -> new LazyLoadedMusic(resolver, path)).collect(toList());

        Music narration = new MusicSequence(sequence);
        narration.setVolume(1f);

        Music background = new LazyLoadedMusic(resolver, manifest.getMusic());
        background.setVolume(0.7f);

        return new MusicCombination(narration, background);
    }

    public IntroMenuStrings getStrings() {
        I18NBundle bundle = assets.get(manifest.getStrings(), I18NBundle.class);
        return new IntroMenuStrings(bundle);
    }

    public void load() {
        assets.load(manifest.getBackground(), Texture.class);
        assets.load(manifest.getButton(), Texture.class);
        assets.load(manifest.getStrings(), I18NBundle.class);

        assets.finishLoadingAsset(manifest.getBackground());
        assets.finishLoadingAsset(manifest.getButton());
        assets.finishLoadingAsset(manifest.getStrings());
    }
}
