package com.evilbird.engine.loader;

import com.badlogic.gdx.ScreenAdapter;
import com.badlogic.gdx.graphics.Texture;
import com.evilbird.engine.game.GameScreenManager;
import com.evilbird.engine.menu.Menu;

import javax.inject.Inject;

public class GameLoader extends ScreenAdapter
{
    private GameLoaderModel model;
    private GameLoaderView view;
    private GameScreenManager screenManager;

    @Inject
    public GameLoader(GameLoaderModel model, GameLoaderView view)
    {
        this.view = view;
        this.model = model;
        this.model.setPresenter(this);
        this.model.loadBackground();
        this.model.loadAssets();
    }

    @Override
    public void dispose()
    {
        view.dispose();
    }

    @Override
    public void render(float delta)
    {
        model.update(delta);
        view.render(delta);
    }

    @Override
    public void resize(int width, int height)
    {
        view.resize(width, height);
    }

    public void setScreenManager(GameScreenManager screenManager)
    {
        this.screenManager = screenManager;
    }

    public void setMenuScreen(Menu menu)
    {
        menu.setScreenManager(screenManager);
        screenManager.setScreen(menu);
    }
    public void setBackground(Texture texture)
    {
        view.setBackground(texture);
    }
}
