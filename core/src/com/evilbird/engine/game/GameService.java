/*
 * Blair Butterworth (c) 2019
 *
 * This work is licensed under the MIT License. To view a copy of this
 * license, visit
 *
 *      https://opensource.org/licenses/MIT
 */

package com.evilbird.engine.game;

import com.evilbird.engine.action.ActionFactory;
import com.evilbird.engine.behaviour.BehaviourFactory;
import com.evilbird.engine.common.reflect.TypeRegistry;
import com.evilbird.engine.device.Device;
import com.evilbird.engine.item.ItemFactory;

/**
 * Instances of this single provide static access to the {@link GameInjector},
 * used to obtain the central services used by the application.
 *
 * @author Blair Butterworth
 */
public class GameService implements GameInjector
{
    private static GameService instance;

    public static GameService getInstance() {
        if (instance == null) {
            instance = new GameService();
        }
        return instance;
    }

    private GameInjector injector;

    @Override
    public Device getDevice() {
        return injector.getDevice();
    }

    @Override
    public GameEngine getEngine() {
        return injector.getEngine();
    }

    @Override
    public ActionFactory getActionFactory() {
        return injector.getActionFactory();
    }

    @Override
    public ItemFactory getItemFactory() {
        return injector.getItemFactory();
    }

    @Override
    public BehaviourFactory getBehaviourFactory() {
        return injector.getBehaviourFactory();
    }

    @Override
    public TypeRegistry getTypeRegistry() {
        return injector.getTypeRegistry();
    }

    public void setInjector(GameInjector injector) {
        this.injector = injector;
    }
}
