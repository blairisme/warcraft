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
import com.badlogic.gdx.ScreenAdapter;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.evilbird.engine.common.lang.Objects;
import com.evilbird.engine.game.GameController;

import javax.inject.Inject;

public class MenuScreen extends ScreenAdapter
{
    private Menu menu;
    private Stage stage;
    private Music music;
    private GameController controller;

    @Inject
    public MenuScreen() {
    }

    public void setMenu(Menu menu) {
        this.menu = menu;
        this.stage = menu.getStage();
        updateMusic(menu.getMusic());
        updateController();
    }

    public void setController(GameController controller) {
        this.controller = controller;
        updateController();
    }

    @Override
    public void dispose() {
        stage.dispose();
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
    public void show () {
        Gdx.input.setInputProcessor(stage);
        startMusic();
    }

    private void startMusic() {
        if (music != null && !music.isPlaying()) {
            music.play();
        }
    }

    private void pauseMusic() {
        if (music != null && music.isPlaying()) {
            music.pause();
        }
    }

    private void updateMusic(Music newMusic) {
        if (!Objects.equals(music, newMusic)) {
            pauseMusic();
            music = newMusic;
        }
    }

    private void updateController() {
        if (menu != null && controller != null) {
            menu.setController(controller);
        }
    }
}
