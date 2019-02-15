/*
 * Blair Butterworth (c) 2019
 *
 * This work is licensed under the MIT License. To view a copy of this
 * license, visit
 *
 *      https://opensource.org/licenses/MIT
 */

package com.evilbird.engine.loader;

import com.badlogic.gdx.ScreenAdapter;
import com.badlogic.gdx.graphics.Texture;
import com.evilbird.engine.game.GameController;

import javax.inject.Inject;

//TODO: Move loading out of constructor - GameEngine.create() should call LoaderScreen.load()
public class LoaderScreen extends ScreenAdapter
{
    private LoaderScreenModel model;
    private LoaderScreenView view;
    private GameController controller;

    @Inject
    public LoaderScreen(LoaderScreenModel model, LoaderScreenView view) {
        this.view = view;
        this.model = model;
        this.model.setPresenter(this);
    }

    public void load() {
        view.load();
        model.loadBackground();
        model.loadAssets();
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

    public void setController(GameController controller) {
        this.controller = controller;
    }

    public void setMenuScreen() {
        controller.showMenuRoot();
    }

    public void setBackground(Texture texture) {
        view.setBackground(texture);
    }
}
