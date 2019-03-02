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
    public GameEngine getGameEngine() {
        return injector.getGameEngine();
    }

    @Override
    public ActionFactory getActionFactory() {
        return injector.getActionFactory();
    }

    @Override
    public ItemFactory getItemFactory() {
        return injector.getItemFactory();
    }

    public void setInjector(GameInjector injector) {
        this.injector = injector;
    }
}
