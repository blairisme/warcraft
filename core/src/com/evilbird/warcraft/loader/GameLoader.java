package com.evilbird.warcraft.loader;

import com.badlogic.gdx.graphics.Texture;
import com.evilbird.warcraft.GameEngine;
import com.evilbird.warcraft.GameScene;
import com.evilbird.warcraft.GameService;
import com.evilbird.warcraft.device.Device;
import com.evilbird.warcraft.menu.Menu;
import com.evilbird.warcraft.menu.MenuFactory;
import com.evilbird.warcraft.utility.Identifier;

public class GameLoader extends GameScene
{
    private GameLoaderModel model;
    private GameLoaderView view;

    public GameLoader(Device device, GameEngine parent)
    {
        super(device, parent, null);

        view = new GameLoaderView();
        model = new GameLoaderModel(this, device);

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
