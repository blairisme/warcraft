/*
 * Blair Butterworth (c) 2019
 *
 * This work is licensed under the MIT License. To view a copy of this
 * license, visit
 *
 *      https://opensource.org/licenses/MIT
 */

package com.evilbird.warcraft.menu.main;

import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.scenes.scene2d.utils.Drawable;
import com.badlogic.gdx.scenes.scene2d.utils.TextureRegionDrawable;
import com.evilbird.engine.common.graphics.Fonts;
import com.evilbird.engine.common.inject.AssetProvider;
import com.evilbird.engine.device.Device;
import com.evilbird.engine.menu.Menu;

import javax.inject.Inject;

public class HomeMenuFactory implements AssetProvider<Menu>
{
    private static final String BUTTON = "data/textures/menu/button.png";
    private static final String BACKGROUND = "data/textures/menu/menu.png";
    private static final String CLICK = "data/sounds/menu/click.mp3";
    private static final String MUSIC = "data/music/13.mp3";
    //private static final String FONT = "data/fonts/philosopher.ttf";

    private AssetManager assets;

    @Inject
    public HomeMenuFactory(Device device) {
        this.assets = device.getAssetStorage().getAssets();
    }

    @Override
    public void load() {
        assets.load(BUTTON, Texture.class);
        assets.load(BACKGROUND, Texture.class);
        //assets.load(FONT, BitmapFont.class);
        assets.load(CLICK, Sound.class);
        assets.load(MUSIC, Music.class);
    }

    @Override
    public Menu get() {
        HomeMenu menu = new HomeMenu();
        menu.setBackground(getBackground());
        menu.setButtonTextures(getEnabled(), getSelected(), getDisabled());
        menu.setButtonFont(getButtonFont());
        menu.setButtonSound(getButtonClick());
        menu.setMusic(getMusic());
        return menu;
    }

    private Drawable getBackground() {
        Texture menuTexture = assets.get(BACKGROUND);
        TextureRegion menuTextureRegion = new TextureRegion(menuTexture);
        return new TextureRegionDrawable(menuTextureRegion);
    }

    private TextureRegion getEnabled() {
        Texture buttonTexture = assets.get(BUTTON);
        return new TextureRegion(buttonTexture, 0, 0, 224, 28);
    }

    private TextureRegion getSelected() {
        Texture buttonTexture = assets.get(BUTTON);
        return new TextureRegion(buttonTexture, 0, 28, 224, 28);
    }

    private TextureRegion getDisabled() {
        Texture buttonTexture = assets.get(BUTTON);
        return new TextureRegion(buttonTexture, 0, 56, 224, 28);
    }

    private BitmapFont getButtonFont() {
        return Fonts.ARIAL;
        //return assets.get(FONT);
    }

    private Sound getButtonClick() {
        return assets.get(CLICK);
    }

    private Music getMusic() {
        return assets.get(MUSIC);
    }
}