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
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.utils.viewport.ScreenViewport;
import com.evilbird.engine.game.GameScreenManager;
import com.evilbird.engine.level.Level;
import com.evilbird.engine.state.StateIdentifier;

import javax.inject.Provider;

public class Menu extends ScreenAdapter
{
    private Stage stage;
    private Music music;
    private MenuFactory menuFactory;
    private Provider<Level> levelFactory;
    private GameScreenManager screenManager;

    public Menu() {
        this(new Stage(new ScreenViewport()));
    }

    public Menu(Stage stage) {
        this.stage = stage;
    }

    @Override
    public void dispose() {
        stage.dispose();
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
    public void show () {
        Gdx.input.setInputProcessor(stage);
        startBackgroundMusic();
    }

    public void showMenu(MenuIdentifier identifier) {
        Menu menu = menuFactory.newMenu(identifier);
        menu.setScreenManager(screenManager);
        menu.setBackgroundMusic(music);
        showScreen(menu);
    }

    public void showLevel(StateIdentifier world, StateIdentifier hud) {
        Level level = levelFactory.get();
        level.load(world, hud, null);
        stopBackgroundMusic();
        showScreen(level);
    }

    public void showScreen(Screen screen) {
        screenManager.setScreen(screen);
        dispose();
    }

    public void setBackgroundMusic(Music backgroundMusic) {
        this.music = backgroundMusic;
    }

    public void setMenuFactory(MenuFactory menuFactory) {
        this.menuFactory = menuFactory;
    }

    public void setLevelFactory(Provider<Level> levelFactory) {
        this.levelFactory = levelFactory;
    }

    public void setScreenManager(GameScreenManager screenManager) {
        this.screenManager = screenManager;
    }

    public void startBackgroundMusic() {
        if (music != null && !music.isPlaying()) {
            music.play();
        }
    }

    public void stopBackgroundMusic() {
        if (music != null && music.isPlaying()) {
            music.stop();
        }
    }
}
