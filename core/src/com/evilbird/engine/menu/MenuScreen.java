/*
 * Copyright (c) 2020, Blair Butterworth
 *
 * This Source Code Form is subject to the terms of the Mozilla Public
 * License, v. 2.0. If a copy of the MPL was not distributed with this
 * file, You can obtain one at http://mozilla.org/MPL/2.0/.
 */

package com.evilbird.engine.menu;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.ScreenAdapter;
import com.badlogic.gdx.graphics.GL20;
import com.evilbird.engine.audio.AudioManager;
import com.evilbird.engine.game.GameController;

import javax.inject.Inject;

/**
 * Instances of this class apply and render a {@link Menu}. Menus drawn by
 * this class will occupy the entire screen and are usually drawn above a
 * background image.
 *
 * @author Blair Butterworth
 */
public class MenuScreen extends ScreenAdapter
{
    private Menu menu;
    private MenuIdentifier identifier;
    private GameController controller;
    private AudioManager audioManager;

    @Inject
    public MenuScreen(AudioManager audioManager) {
        this.audioManager = audioManager;
    }

    public Menu getMenu() {
        return menu;
    }

    public MenuIdentifier getIdentifier() {
        return identifier;
    }

    public void setMenu(Menu menu, MenuIdentifier identifier) {
        this.menu = menu;
        this.identifier = identifier;
        if (controller != null) {
            menu.setController(controller);
        }
    }

    public void setController(GameController controller) {
        this.controller = controller;
        if (menu != null) {
            menu.setController(controller);
        }
    }

    @Override
    public void dispose() {
        if (menu != null) {
            menu.dispose();
        }
    }

    @Override
    public void resize(int width, int height) {
        if (menu != null) {
            menu.resize(width, height);
        }
    }

    @Override
    public void show() {
        if (menu != null) {
            menu.show();
            menu.play(audioManager);
        }
    }

    @Override
    public void render(float delta) {
        Gdx.gl.glClearColor(0, 0, 0, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        update(delta);
        draw();
    }

    public void draw() {
        menu.draw();
    }

    public void update(float delta) {
        menu.update(delta);
    }
}
