/*
 * Blair Butterworth (c) 2019
 *
 * This work is licensed under the MIT License. To view a copy of this
 * license, visit
 *
 *      https://opensource.org/licenses/MIT
 */

package com.evilbird.engine.menu;

import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.utils.viewport.ScreenViewport;
import com.evilbird.engine.game.GameController;
import com.evilbird.engine.state.StateIdentifier;

public class Menu
{
    private Stage stage;
    private Music music;
    private GameController controller;

    public Menu() {
        this(new Stage(new ScreenViewport()));
    }

    public Menu(Stage stage) {
        this.stage = stage;
    }

    public Stage getStage() {
        return stage;
    }

    public Music getMusic() {
        return music;
    }

    public void setController(GameController controller) {
        this.controller = controller;
    }

    public void setMusic(Music music) {
        this.music = music;
    }

    protected void showMenu() {
        controller.showMenu();
    }

    protected void showMenu(MenuIdentifier identifier) {
        controller.showMenu(identifier);
    }

    protected void showMenuOverlay(MenuIdentifier identifier) {
        controller.showMenuOverlay(identifier);
    }

    protected void showState() {
        controller.showState();
        stopMusic();
    }

    protected void showState(StateIdentifier identifier) {
        controller.showState(identifier);
        stopMusic();
    }

    protected void startMusic() {
        if (music != null) {
            music.play();
        }
    }

    protected void stopMusic() {
        if (music != null) {
            music.stop();
        }
    }
}
