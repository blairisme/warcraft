/*
 * Copyright (c) 2019, Blair Butterworth
 *
 * This work is licensed under the MIT License. To view a copy of this
 * license, visit
 *
 *        https://opensource.org/licenses/MIT
 */

package com.evilbird.engine.game;

import com.badlogic.gdx.assets.AssetManager;
import com.evilbird.engine.action.ActionFactory;
import com.evilbird.engine.behaviour.BehaviourFactory;
import com.evilbird.engine.device.Device;
import com.evilbird.engine.item.ItemFactory;
import com.evilbird.engine.menu.MenuFactory;
import com.evilbird.engine.state.StateService;

import javax.inject.Inject;

public class GameLoader
{
    private static final int UPDATE_PERIOD = 500;

    private AssetManager assets;
    private ActionFactory actionFactory;
    private BehaviourFactory behaviourFactory;
    private ItemFactory itemFactory;
    private MenuFactory menuFactory;
    private StateService stateService;

    @Inject
    public GameLoader(
        Device device,
        ActionFactory actionFactory,
        BehaviourFactory behaviourFactory,
        ItemFactory itemFactory,
        MenuFactory menuFactory,
        StateService stateService)
    {
        this.assets = device.getAssetStorage();
        this.actionFactory = actionFactory;
        this.behaviourFactory = behaviourFactory;
        this.itemFactory = itemFactory;
        this.menuFactory = menuFactory;
        this.stateService = stateService;
    }

    public void load(GameContext context) {
        actionFactory.load(context);
        menuFactory.load(context);
        itemFactory.load(context);
        behaviourFactory.load(context);
        stateService.load();
    }

    public void unload() {
    }

    public boolean isComplete() {
        return assets.update(UPDATE_PERIOD);
    }
}
