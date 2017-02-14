package com.evilbird.engine.level;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.scenes.scene2d.Stage;

import javax.inject.Inject;

public class LevelView
{
    private Stage hud;
    private Stage world;

    @Inject
    public LevelView()
    {
    }

    public void setWorld(Stage world)
    {
        this.world = world;
    }

    public void setHud(Stage hud)
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
        world.getViewport().update(width, height);
        hud.getViewport().update(width, height);
    }

    public void dispose()
    {
        world.dispose();
        hud.dispose();
    }
}
