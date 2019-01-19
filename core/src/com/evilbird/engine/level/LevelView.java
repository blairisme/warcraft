/*
 * Blair Butterworth (c) 2019
 *
 * This work is licensed under the MIT License. To view a copy of this
 * license, visit
 *
 *      https://opensource.org/licenses/MIT
 */

package com.evilbird.engine.level;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.evilbird.engine.item.ItemRoot;

import javax.inject.Inject;

public class LevelView
{
    private ItemRoot hud;
    private ItemRoot world;

    @Inject
    public LevelView()
    {
    }

    public void setWorld(ItemRoot world)
    {
        this.world = world;
    }

    public void setHud(ItemRoot hud)
    {
        this.hud = hud;
    }

    public void draw()
    {
        Gdx.gl.glClearColor(0, 0, 0, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        world.draw();
        hud.draw();
    }

    public void resize(int width, int height)
    {
        world.resize(width, height);
        hud.resize(width, height);
    }

    public void dispose()
    {
        world.dispose();
        hud.dispose();
    }
}
