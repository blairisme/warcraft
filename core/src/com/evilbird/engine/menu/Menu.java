/*
 * Blair Butterworth (c) 2019
 *
 * This work is licensed under the MIT License. To view a copy of this
 * license, visit
 *
 *      https://opensource.org/licenses/MIT
 */

package com.evilbird.engine.menu;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.ScreenAdapter;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.utils.viewport.ScreenViewport;
import com.evilbird.engine.game.GameScreenManager;

public class Menu extends ScreenAdapter
{
    private Stage stage;
    private GameScreenManager screenManager;

    public Menu() {
        this(new Stage(new ScreenViewport()));
    }

    public Menu(Stage stage) {
        this.stage = stage;
        Gdx.input.setInputProcessor(stage); //TODO
    }

    public Stage getStage() {
        return stage;
    }

    @Override
    public void render(float delta) {
        Gdx.gl.glClearColor(0, 0, 0, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        stage.act(delta);
        stage.draw();
    }

    @Override
    public void resize(int width, int height) {
        stage.getViewport().update(width, height, true);
    }

    @Override
    public void dispose() {
        stage.dispose();
    }

    public void setScreen(Screen screen) {
        this.screenManager.setScreen(screen);
    }

    public void setScreenManager(GameScreenManager screenManager) {
        this.screenManager = screenManager;
    }
}
