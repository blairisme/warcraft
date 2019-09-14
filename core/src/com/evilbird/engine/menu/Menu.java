/*
 * Copyright (c) 2019, Blair Butterworth
 *
 * This work is licensed under the MIT License. To view a copy of this
 * license, visit
 *
 *        https://opensource.org/licenses/MIT
 */

package com.evilbird.engine.menu;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.utils.Disposable;
import com.badlogic.gdx.utils.viewport.ScreenViewport;
import com.evilbird.engine.device.DeviceDisplay;
import com.evilbird.engine.game.GameController;
import com.evilbird.engine.state.StateIdentifier;

/**
 * Represents a user interface consisting of a number of selectable options.
 * Menus are generally used to load or save game states.
 *
 * @author Blair Butterworth
 */
public class Menu implements Disposable
{
    protected Stage stage;
    protected Music music;
    protected GameController controller;

    public Menu() {
        this.stage = new Stage(new ScreenViewport());
    }

    public Menu(Stage stage) {
        this.stage = stage;
    }

    public Menu(DeviceDisplay display){
        ScreenViewport viewport = new ScreenViewport();
        viewport.setUnitsPerPixel(display.getPixelUnits());
        this.stage = new Stage(viewport);
    }

    public void dispose() {
        stage.dispose();
    }

    public void resize(int width, int height) {
        stage.getViewport().update(width, height, true);
    }

    public void back() {
    }

    public void show() {
        Gdx.input.setInputProcessor(stage);
        Gdx.input.setCatchBackKey(true);
        stage.addListener(new MenuInput(this));
    }

    public void update(float delta) {
        stage.act(delta);
    }

    public void draw() {
        stage.draw();
    }

    public Stage getStage() {
        return stage;
    }

    public Music getMusic() {
        return music;
    }

    public GameController getController() {
        return controller;
    }

    public void setContent(Actor actor) {
        stage.addActor(actor);
    }

    public void setController(GameController controller) {
        this.controller = controller;
    }

    public void setMusic(Music music) {
        this.music = music;
    }

    public void showError(Throwable error) {
        controller.showError(error);
    }

    public void showMenu() {
        controller.showMenu();
    }

    public void showMenu(MenuIdentifier identifier) {
        controller.showMenu(identifier);
    }

    public void showMenuOverlay(MenuIdentifier identifier) {
        controller.showMenuOverlay(identifier);
    }

    public void showState() {
        controller.showState();
    }

    public void showState(StateIdentifier identifier) {
        controller.showState(identifier);
    }

    public void saveState(StateIdentifier identifier) {
        controller.saveState(identifier);
        controller.showState();
    }
}
