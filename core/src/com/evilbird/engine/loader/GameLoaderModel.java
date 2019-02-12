/*
 * Blair Butterworth (c) 2019
 *
 * This work is licensed under the MIT License. To view a copy of this
 * license, visit
 *
 *      https://opensource.org/licenses/MIT
 */

package com.evilbird.engine.loader;

import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.graphics.Texture;
import com.evilbird.engine.action.ActionFactory;
import com.evilbird.engine.behaviour.BehaviourFactory;
import com.evilbird.engine.device.Device;
import com.evilbird.engine.item.ItemFactory;
import com.evilbird.engine.menu.Menu;
import com.evilbird.engine.menu.MenuFactory;
import com.evilbird.engine.state.StateFactory;

import javax.inject.Inject;

public class GameLoaderModel
{
    private GameLoader presenter;
    private AssetManager assets;
    private ActionFactory actionFactory;
    private BehaviourFactory behaviourFactory;
    private ItemFactory itemFactory;
    private MenuFactory menuFactory;
    private StateFactory stateFactory;
    private float loadingTime;

    @Inject
    public GameLoaderModel(
        Device device,
        ActionFactory actionFactory,
        BehaviourFactory behaviourFactory,
        ItemFactory itemFactory,
        MenuFactory menuFactory,
        StateFactory stateFactory)
    {
        this.loadingTime = 0;
        this.assets = device.getAssetStorage().getAssets();
        this.actionFactory = actionFactory;
        this.behaviourFactory = behaviourFactory;
        this.itemFactory = itemFactory;
        this.menuFactory = menuFactory;
        this.stateFactory = stateFactory;
    }

    public void setPresenter(GameLoader presenter) {
        this.presenter = presenter;
    }

    public void loadBackground() {
        assets.load("data/textures/menu/title.png", Texture.class);
        assets.finishLoading();

        Texture texture = assets.get("data/textures/menu/title.png");
        presenter.setBackground(texture);
    }

    public void loadAssets() {
        actionFactory.load();
        menuFactory.load();
        itemFactory.load();
        behaviourFactory.load();
        stateFactory.load();
    }

    public void update(float delta) {
        loadingTime += delta;
        if (loadingTime >= 2 && assets.update()) {
            Menu menu = menuFactory.newMenu();
            presenter.setMenuScreen(menu);
        }
    }
}
