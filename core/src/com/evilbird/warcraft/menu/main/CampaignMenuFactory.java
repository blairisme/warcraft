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
import com.evilbird.engine.common.inject.AssetProvider;
import com.evilbird.engine.device.Device;
import com.evilbird.engine.menu.Menu;

import javax.inject.Inject;

import static com.evilbird.engine.common.graphics.TextureUtils.getDrawable;

public class CampaignMenuFactory implements AssetProvider<Menu>
{
    private static final String BUTTON = "data/textures/menu/button.png";
    private static final String BACKGROUND = "data/textures/menu/menu.png";
    private static final String CLICK = "data/sounds/menu/click.mp3";
    private static final String MUSIC = "data/music/13.mp3";

    private AssetManager assets;

    @Inject
    public CampaignMenuFactory(Device device) {
        this.assets = device.getAssetStorage().getAssets();
    }

    @Override
    public void load() {
        assets.load(BUTTON, Texture.class);
        assets.load(BACKGROUND, Texture.class);
        assets.load(CLICK, Sound.class);
        assets.load(MUSIC, Music.class);
    }

    @Override
    public Menu get() {
        CampaignMenu menu = new CampaignMenu();
        menu.setBackground(getDrawable(assets, BACKGROUND));
        menu.setButtonEnabled(getDrawable(assets, BUTTON, 0, 0, 224, 28));
        menu.setButtonDisabled(getDrawable(assets, BUTTON, 0, 56, 224, 28));
        menu.setButtonSelected(getDrawable(assets, BUTTON, 0, 28, 224, 28));
        menu.setButtonSound(assets.get(CLICK));
        menu.setMusic(assets.get(MUSIC));
        return menu;
    }
}
