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
import com.evilbird.engine.common.assets.AssetManagerFuture;
import com.evilbird.engine.common.concurrent.CompleteFuture;
import com.evilbird.engine.device.Device;
import com.evilbird.engine.item.ItemFactory;
import com.evilbird.engine.menu.MenuFactory;
import com.evilbird.engine.state.StateIdentifier;

import javax.inject.Inject;
import java.util.concurrent.Future;

/**
 * Loads and unloads assets used by the game engine.
 *
 * @author Blair Butterworth
 */
public class GameAssets
{
    private AssetManager assets;
    private ActionFactory actionFactory;
    private BehaviourFactory behaviourFactory;
    private ItemFactory itemFactory;
    private MenuFactory menuFactory;
    private GameContext loadedContext;
    private StateIdentifier loadedState;

    @Inject
    public GameAssets(
        Device device,
        ActionFactory actionFactory,
        BehaviourFactory behaviourFactory,
        ItemFactory itemFactory,
        MenuFactory menuFactory)
    {
        this.assets = device.getAssetStorage();
        this.actionFactory = actionFactory;
        this.behaviourFactory = behaviourFactory;
        this.itemFactory = itemFactory;
        this.menuFactory = menuFactory;
    }

    public StateIdentifier getLoadedState() {
        return loadedState;
    }

    public GameContext getLoadedContext() {
        return loadedContext;
    }

    public Future<?> loadMenuAssets() {
        menuFactory.load();
        return new CompleteFuture<>(null);
    }

    public Future<?> loadStateAssets(StateIdentifier state, GameContext context) {
        this.loadedState = state;
        if (context != loadedContext) {
            loadedContext = context;
            unloadContext(loadedContext);
            loadContext(context);
            return new AssetManagerFuture(assets);
        }
        return new CompleteFuture<>(null);
    }

    public void finishLoading() {
        assets.finishLoading();
    }

    private void loadContext(GameContext context) {
        actionFactory.load(context);
        menuFactory.load(context);
        itemFactory.load(context);
        behaviourFactory.load(context);
    }

    private void unloadContext(GameContext context) {
//        if (context != null) {
//            actionFactory.unload(context);
//            menuFactory.unload(context);
//            itemFactory.unload(context);
//            behaviourFactory.unload(context);
//        }
    }
}
