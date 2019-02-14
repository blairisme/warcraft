/*
 * Blair Butterworth (c) 2019
 *
 * This work is licensed under the MIT License. To view a copy of this
 * license, visit
 *
 *      https://opensource.org/licenses/MIT
 */

package com.evilbird.engine.game.renderer;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.evilbird.engine.item.ItemRoot;
import com.evilbird.engine.state.State;

import javax.inject.Inject;

public class GameRendererView
{
    private ItemRoot world;
    private ItemRoot hud;

    @Inject
    public GameRendererView() {
    }

    public void setState(State state) {
        this.world = state.getWorld();
        this.hud = state.getHud();
    }

    public void draw() {
        Gdx.gl.glClearColor(0, 0, 0, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        world.draw();
        hud.draw();
    }

    public void resize(int width, int height) {
        world.resize(width, height);
        hud.resize(width, height);
    }

    public void dispose() {
        world.draw();
        hud.dispose();
    }
}
