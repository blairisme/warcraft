/*
 * Copyright (c) 2020, Blair Butterworth
 *
 * This Source Code Form is subject to the terms of the Mozilla Public
 * License, v. 2.0. If a copy of the MPL was not distributed with this
 * file, You can obtain one at http://mozilla.org/MPL/2.0/.
 */

package com.evilbird.engine.game.loader;

import com.badlogic.gdx.ScreenAdapter;
import com.badlogic.gdx.graphics.Texture;
import com.evilbird.engine.game.GameEngine;

import javax.inject.Inject;
import java.util.concurrent.Future;

/**
 * Instances of this class render an image to the screen whilst the various
 * factories and services that provide game objects and behaviour are
 * initialized. When loading is complete the default/root menu is displayed.
 *
 * @author Blair Butterworth
 */
public class LoaderScreen extends ScreenAdapter
{
    private LoaderScreenModel model;
    private LoaderScreenView view;
    private GameEngine engine;

    @Inject
    public LoaderScreen(LoaderScreenModel model, LoaderScreenView view) {
        this.view = view;
        this.model = model;
        this.model.setPresenter(this);
    }

    public void show() {
        view.show();
        model.show();
        model.load();
    }

    @Override
    public void dispose() {
        view.dispose();
    }

    @Override
    public void render(float delta) {
        model.update(delta);
        view.render(delta);
    }

    @Override
    public void resize(int width, int height) {
        view.resize(width, height);
    }

    public void setEngine(GameEngine engine) {
        this.engine = engine;
    }

    public Future<?> loadMenuAssets() {
        return engine.loadMenuAssets();
    }

    public boolean isLoaded() {
        return true;
    }

    public void showInitialScreen() {
        engine.showInitialScreen();
    }

    public void setBackground(Texture texture) {
        view.setBackground(texture);
    }
}
