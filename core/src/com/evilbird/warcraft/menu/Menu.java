package com.evilbird.warcraft.menu;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.evilbird.warcraft.GameScene;

public class Menu extends GameScene
{
    private Stage stage;

    public Menu(Stage stage)
    {
        this.stage = stage;
    }

    public void render (float delta)
    {
        Gdx.gl.glClearColor(0, 0, 1, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        stage.act(delta);
        stage.draw();
    }

    @Override
    public void resize (int width, int height)
    {
        stage.getViewport().update(width, height, false);
    }

    @Override
    public void dispose ()
    {
        stage.dispose();
    }
}
