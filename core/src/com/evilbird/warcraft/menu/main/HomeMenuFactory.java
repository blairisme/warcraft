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
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.scenes.scene2d.utils.Drawable;
import com.badlogic.gdx.scenes.scene2d.utils.TextureRegionDrawable;
import com.evilbird.engine.common.inject.AssetProvider;
import com.evilbird.engine.device.Device;
import com.evilbird.engine.menu.IndexMenu;

import javax.inject.Inject;

public class HomeMenuFactory implements AssetProvider<IndexMenu>
{
    private static final String BUTTON = "data/textures/menu/button.png";
    private static final String BACKGROUND = "data/textures/menu/menu.png";

    private AssetManager assets;

    @Inject
    public HomeMenuFactory(Device device) {
        this.assets = device.getAssetStorage().getAssets();
    }

    @Override
    public void load() {
        assets.load(BUTTON, Texture.class);
        assets.load(BACKGROUND, Texture.class);
    }

    @Override
    public IndexMenu get() {
        HomeMenu menu = new HomeMenu();
        menu.setBackground(getBackground());
        menu.setButtonTexture(getEnabled(), getSelected(), getDisabled());
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
}
