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
import com.badlogic.gdx.graphics.GL20;
import com.evilbird.engine.state.StateScreen;

import javax.inject.Inject;

/**
 * Represents a user interface consisting of a number of selectable options.
 * Overlay menus are generally used to load or save game states, and differ
 * from {@link MenuScreen} in that it draws Menus as well as the game state,
 * which is drawn, but not updated, below the overlay menu while it's shown.
 *
 * @author Blair Butterworth
 */
public class MenuOverlay extends ScreenAdapter
{
    private MenuScreen menuScreen;
    private StateScreen stateScreen;

    @Inject
    public MenuOverlay() {
    }

    public void setMenuScreen(MenuScreen menuScreen) {
        this.menuScreen = menuScreen;
    }

    public void setStateScreen(StateScreen stateScreen) {
        this.stateScreen = stateScreen;
    }

    @Override
    public void dispose() {
       menuScreen.dispose();
       stateScreen.dispose();
    }

    @Override
    public void resize(int width, int height) {
        menuScreen.resize(width, height);
        stateScreen.resize(width, height);
    }

    @Override
    public void show() {
        menuScreen.show();
    }

    @Override
    public void render(float delta) {
        Gdx.gl.glClearColor(0, 0, 0, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        menuScreen.update(delta);
        stateScreen.draw();
        menuScreen.draw();
    }
}
