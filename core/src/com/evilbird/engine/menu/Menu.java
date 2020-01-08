/*
 * Copyright (c) 2020, Blair Butterworth
 *
 * This Source Code Form is subject to the terms of the Mozilla Public
 * License, v. 2.0. If a copy of the MPL was not distributed with this
 * file, You can obtain one at http://mozilla.org/MPL/2.0/.
 */

package com.evilbird.engine.menu;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.utils.Disposable;
import com.badlogic.gdx.utils.viewport.ScreenViewport;
import com.evilbird.engine.audio.music.Music;
import com.evilbird.engine.device.DeviceDisplay;
import com.evilbird.engine.game.GameController;
import com.evilbird.engine.state.StateIdentifier;

import static com.evilbird.warcraft.menu.ingame.IngameMenuType.Root;

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

    public Menu(Menu menu) {
        this.stage = menu.stage;
        this.music = menu.music;
        this.controller = menu.controller;
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

    public void showMenu() {
        controller.showMenu();
    }

    public void showMenu(MenuIdentifier identifier) {
        controller.showMenu(identifier);
    }

    public void showMenuOverlay(MenuIdentifier identifier) {
        controller.showMenuOverlay(identifier);
    }

    public void showRootMenu() {
        showMenuOverlay(Root);
    }

    public void showState() {
        controller.showState();
    }

    public void showState(StateIdentifier identifier) {
        controller.showState(identifier);
    }
}
