package com.evilbird.engine.loader;

import com.badlogic.gdx.ScreenAdapter;
import com.badlogic.gdx.graphics.Texture;
import com.evilbird.engine.GameService;
import com.evilbird.engine.game.GameScreenManager;
import com.evilbird.engine.menu.Menu;
import com.evilbird.engine.menu.MenuFactory;
import com.evilbird.engine.utility.Identifier;

import javax.inject.Inject;

public class GameLoaderPresenter extends ScreenAdapter implements GameLoader
{
    private GameLoaderModel model;
    private GameLoaderView view;
    private GameScreenManager screenManager;

    @Inject
    public GameLoaderPresenter(GameLoaderModel model, GameLoaderView view)
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

    @Override
    public void setScreenManager(GameScreenManager screenManager)
    {
        this.screenManager = screenManager;
    }

    public void setGameService(GameService gameService) //TODO setScreen(menu)
    {
        MenuFactory menuFactory = gameService.getMenuFactory();
        Menu rootMenu = menuFactory.newMenu(new Identifier("Root"));

        rootMenu.setScreenManager(screenManager);
        screenManager.setScreen(rootMenu);
    }

    public void setBackground(Texture texture)
    {
        view.setBackground(texture);
    }
}
