package com.evilbird.engine.loader;

import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.graphics.Texture;
import com.evilbird.engine.GameService;
import com.evilbird.engine.action.ActionFactory;
import com.evilbird.engine.behaviour.BehaviourFactory;
import com.evilbird.engine.device.Device;
import com.evilbird.engine.hud.HudFactory;
import com.evilbird.engine.item.ItemFactory;
import com.evilbird.engine.menu.MenuFactory;
import com.evilbird.engine.world.WorldFactory;

import javax.inject.Inject;

public class GameLoaderModel
{
    private GameLoaderPresenter presenter;
    private AssetManager assets;
    private GameService service;
    private float loadingTime;

    @Inject
    public GameLoaderModel(Device device, GameService service)
    {
        this.assets = device.getAssetStorage().getAssets();
        this.service = service;
        this.loadingTime = 0;
    }

    public void setPresenter(GameLoaderPresenter presenter)
    {
        this.presenter = presenter;
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
        ActionFactory actionFactory = service.getActionFactory();
        actionFactory.load(assets);

        MenuFactory menuFactory = service.getMenuFactory();
        menuFactory.load(assets);

        ItemFactory itemFactory = service.getItemFactory();
        itemFactory.load(assets);

        WorldFactory worldFactory = service.getWorldFactory();
        worldFactory.load(assets);

        HudFactory hudFactory = service.getHudFactory();
        hudFactory.load(assets);

        BehaviourFactory behaviourFactory = service.getBehaviourFactory();
        behaviourFactory.load(assets);
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
