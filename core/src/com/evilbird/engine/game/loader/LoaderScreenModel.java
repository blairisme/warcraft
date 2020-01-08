/*
 * Copyright (c) 2020, Blair Butterworth
 *
 * This Source Code Form is subject to the terms of the Mozilla Public
 * License, v. 2.0. If a copy of the MPL was not distributed with this
 * file, You can obtain one at http://mozilla.org/MPL/2.0/.
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
