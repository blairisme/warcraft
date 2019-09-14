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
import com.badlogic.gdx.ScreenAdapter;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.graphics.GL20;
import com.evilbird.engine.common.audio.music.MusicService;
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
    private MusicService musicService;

    @Inject
    public MenuScreen(MusicService musicService) {
        this.musicService = musicService;
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
        updateController();
    }

    public void setController(GameController controller) {
        this.controller = controller;
        updateController();
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
            updateMusic();
        }
    }

    @Override
    public void render(float delta) {
        Gdx.gl.glClearColor(0, 0, 0, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        update(delta);
        draw();
    }

    public void update(float delta) {
        menu.update(delta);
    }

    public void draw() {
        menu.draw();
    }

    private void updateMusic() {
        Music music = menu.getMusic();
        if (music != null) {
            musicService.play(music);
        }
    }

    private void updateController() {
        if (menu != null && controller != null) {
            menu.setController(controller);
        }
    }
}
