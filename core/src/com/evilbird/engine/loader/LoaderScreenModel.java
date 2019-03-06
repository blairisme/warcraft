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
import com.evilbird.engine.menu.MenuFactory;
import com.evilbird.engine.state.StateService;

import javax.inject.Inject;

/**
 * Initializes the various factories and services that provide game objects and
 * behaviour. When loading is complete the default/root menu is displayed.
 *
 * @author Blair Butterworth
 */
public class LoaderScreenModel
{
    private static final String TITLE = "data/textures/menu/title.png";
    private static final int ANTI_FLASH_DELAY = 2;

    private LoaderScreen presenter;
    private AssetManager assets;
    private ActionFactory actionFactory;
    private BehaviourFactory behaviourFactory;
    private ItemFactory itemFactory;
    private MenuFactory menuFactory;
    private StateService stateService;
    private float loadingTime;

    @Inject
    public LoaderScreenModel(
        Device device,
        ActionFactory actionFactory,
        BehaviourFactory behaviourFactory,
        ItemFactory itemFactory,
        MenuFactory menuFactory,
        StateService stateService)
    {
        this.loadingTime = 0;
        this.assets = device.getAssetStorage();
        this.actionFactory = actionFactory;
        this.behaviourFactory = behaviourFactory;
        this.itemFactory = itemFactory;
        this.menuFactory = menuFactory;
        this.stateService = stateService;
    }

    public void setPresenter(LoaderScreen presenter) {
        this.presenter = presenter;
    }

    public void loadBackground() {
        assets.load(TITLE, Texture.class);
        assets.finishLoading();

        Texture texture = assets.get(TITLE);
        presenter.setBackground(texture);
    }

    public void loadAssets() {
        actionFactory.load();
        menuFactory.load();
        itemFactory.load();
        behaviourFactory.load();
        stateService.load();
    }

    public void update(float delta) {
        loadingTime += delta;
        if (loadingTime >= ANTI_FLASH_DELAY && assets.update()) {
            presenter.setMenuScreen();
        }
    }
}
