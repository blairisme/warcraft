package com.evilbird.warcraft.loader;

import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.graphics.Texture;
import com.evilbird.warcraft.GameService;
import com.evilbird.warcraft.action.ActionFactory;
import com.evilbird.warcraft.device.Device;
import com.evilbird.warcraft.level.LevelFactory;
import com.evilbird.warcraft.menu.MenuFactory;
import com.evilbird.warcraft.unit.UnitFactory;

class GameLoaderModel
{
    private GameLoader presenter;
    private AssetManager assets;
    private GameService service;
    private float loadingTime;

    public GameLoaderModel(GameLoader presenter, Device device)
    {
        this.presenter = presenter;
        this.assets = device.getAssetStorage().getAssets();
        this.loadingTime = 0;
    }

    public void loadBackground()
    {
        assets.load("data/textures/menu/title.png", Texture.class);
        assets.finishLoading();

        Texture texture = assets.get("data/textures/menu/title.png");
        presenter.setBackground(texture);
    }

    public void loadAssets()
    {
        UnitFactory unitFactory = new UnitFactory(assets);
        unitFactory.loadAssets();

        ActionFactory actionFactory = new ActionFactory(unitFactory);
        actionFactory.loadAssets();

        LevelFactory levelFactory = new LevelFactory(assets, unitFactory, actionFactory);
        levelFactory.loadAssets();

        MenuFactory menuFactory = new MenuFactory(assets, levelFactory);
        menuFactory.loadAssets();

        service = new GameService(actionFactory, levelFactory, menuFactory, unitFactory);
    }

    public void update(float delta)
    {
        loadingTime += delta;
        if (loadingTime >= 2 && assets.update())
        {
            presenter.setGameService(service);
        }
    }
}
