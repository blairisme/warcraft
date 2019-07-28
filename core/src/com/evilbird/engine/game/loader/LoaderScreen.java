/*
 * Blair Butterworth (c) 2019
 *
 * This work is licensed under the MIT License. To view a copy of this
 * license, visit
 *
 *      https://opensource.org/licenses/MIT
 */

package com.evilbird.engine.game.loader;

import com.badlogic.gdx.ScreenAdapter;
import com.badlogic.gdx.graphics.Texture;
import com.evilbird.engine.game.GameEngine;

import javax.inject.Inject;

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

    public void loadMenuAssets() {
        engine.loadMenuAssets();
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
