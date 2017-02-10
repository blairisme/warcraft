package com.evilbird.engine.loader;

import com.badlogic.gdx.graphics.Texture;
import com.evilbird.engine.GameEngine;
import com.evilbird.engine.GameScene;
import com.evilbird.engine.GameService;
import com.evilbird.engine.device.Device;
import com.evilbird.engine.menu.Menu;
import com.evilbird.engine.utility.Identifier;
import com.evilbird.warcraft.menu.MenuFactory;

public class GameLoader extends GameScene
{
    private com.evilbird.engine.loader.GameLoaderModel model;
    private GameLoaderView view;

    public GameLoader(Device device, GameEngine parent)
    {
        super(device, parent, null);

        view = new GameLoaderView();
        model = new com.evilbird.engine.loader.GameLoaderModel(this, device);

        model.loadBackground();
        model.loadAssets();
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

    void setGameService(GameService gameService)
    {
        MenuFactory menuFactory = gameService.getMenuFactory();
        Menu rootMenu = menuFactory.newMenu(new Identifier("Root"));
        setService(gameService);
        setScreen(rootMenu);
    }

    void setBackground(Texture texture)
    {
        view.setBackground(texture);
    }
}
