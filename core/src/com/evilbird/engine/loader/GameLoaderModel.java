package com.evilbird.engine.loader;

import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.graphics.Texture;
import com.evilbird.engine.action.ActionFactory;
import com.evilbird.engine.behaviour.BehaviourFactory;
import com.evilbird.engine.device.Device;
import com.evilbird.engine.hud.HudFactory;
import com.evilbird.engine.item.ItemFactory;
import com.evilbird.engine.menu.Menu;
import com.evilbird.engine.menu.MenuFactory;
import com.evilbird.engine.utility.Identifier;
import com.evilbird.engine.world.WorldFactory;

import javax.inject.Inject;

public class GameLoaderModel
{
    private GameLoader presenter;
    private AssetManager assets;
    private ActionFactory actionFactory;
    private BehaviourFactory behaviourFactory;
    private HudFactory hudFactory;
    private ItemFactory itemFactory;
    private MenuFactory menuFactory;
    private WorldFactory worldFactory;
    private float loadingTime;

    @Inject
    public GameLoaderModel(
        Device device,
        ActionFactory actionFactory,
        BehaviourFactory behaviourFactory,
        HudFactory hudFactory,
        ItemFactory itemFactory,
        MenuFactory menuFactory,
        WorldFactory worldFactory)
    {
        this.loadingTime = 0;
        this.assets = device.getAssetStorage().getAssets();
        this.actionFactory = actionFactory;
        this.behaviourFactory = behaviourFactory;
        this.hudFactory = hudFactory;
        this.itemFactory = itemFactory;
        this.menuFactory = menuFactory;
        this.worldFactory = worldFactory;
    }

    public void setPresenter(GameLoader presenter)
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
        actionFactory.load(assets);
        menuFactory.load(assets);
        itemFactory.load(assets);
        worldFactory.load(assets);
        hudFactory.load(assets);
        behaviourFactory.load(assets);
    }

    public void update(float delta)
    {
        loadingTime += delta;
        if (loadingTime >= 2 && assets.update())
        {
            Menu menu = menuFactory.newMenu(new Identifier("Root"));
            presenter.setMenuScreen(menu);
        }
    }
}
