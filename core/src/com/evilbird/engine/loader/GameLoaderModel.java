package com.evilbird.engine.loader;

import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.graphics.Texture;
import com.evilbird.engine.GameService;
import com.evilbird.engine.device.Device;
import com.evilbird.warcraft.action.ActionFactory;
import com.evilbird.warcraft.behaviour.BehaviourFactory;
import com.evilbird.warcraft.hud.HudFactory;
import com.evilbird.warcraft.menu.MenuFactory;
import com.evilbird.warcraft.unit.UnitFactory;
import com.evilbird.warcraft.unit.WorldFactory;

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

        MenuFactory menuFactory = new MenuFactory(assets);
        menuFactory.loadAssets();

        WorldFactory worldFactory = new WorldFactory(assets, unitFactory);
        worldFactory.loadAssets();

        HudFactory hudFactory = new HudFactory(assets);
        hudFactory.loadAssets();

        BehaviourFactory behaviourFactory = new BehaviourFactory(actionFactory);
        behaviourFactory.loadAssets();

        service = new GameService(actionFactory, menuFactory, unitFactory, worldFactory, hudFactory, behaviourFactory);
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
