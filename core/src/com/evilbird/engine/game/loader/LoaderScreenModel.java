/*
 * Blair Butterworth (c) 2019
 *
 * This work is licensed under the MIT License. To view a copy of this
 * license, visit
 *
 *      https://opensource.org/licenses/MIT
 */

package com.evilbird.engine.game.loader;

import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.graphics.Texture;
import com.evilbird.engine.device.Device;

import javax.inject.Inject;

/**
 * Initializes the various factories and services that provide game objects and
 * behaviour. When loading is complete the default/root menu is displayed by
 * the {@link LoaderScreenView}.
 *
 * @author Blair Butterworth
 */
public class LoaderScreenModel
{
    private static final String TITLE = "data/textures/common/menu/title.png";
    private static final float FLASH_DELAY = 0.5f;

    private LoaderScreen presenter;
    private AssetManager assets;

    private float loadingTime;

    @Inject
    public LoaderScreenModel(Device device) {
        this.loadingTime = 0;
        this.assets = device.getAssetStorage();
    }

    public void setPresenter(LoaderScreen presenter) {
        this.presenter = presenter;
    }

    public void show() {
        assets.load(TITLE, Texture.class);
        assets.finishLoading();

        Texture texture = assets.get(TITLE);
        presenter.setBackground(texture);
    }

    public void load() {
        presenter.loadMenuAssets();
        assets.finishLoading();
    }

    public void update(float delta) {
        loadingTime += delta;
        if (loadingTime >= FLASH_DELAY && presenter.isLoaded()) {
            presenter.showInitialScreen();
        }
    }
}
