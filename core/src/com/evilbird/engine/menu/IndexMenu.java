/*
 * Blair Butterworth (c) 2019
 *
 * This work is licensed under the MIT License. To view a copy of this
 * license, visit
 *
 *      https://opensource.org/licenses/MIT
 */

package com.evilbird.engine.menu;

import com.evilbird.engine.game.GameScreenManager;
import com.evilbird.engine.level.Level;
import com.evilbird.engine.state.StateIdentifier;

import javax.inject.Provider;

//TODO: Combine into menu?
public class IndexMenu extends Menu
{
    private IndexMenuFactory menuFactory;
    private Provider<Level> levelFactory;
    private GameScreenManager screenManager;

    public IndexMenu() {
    }

    public void showMenu(MenuIdentifier identifier) {
        Menu menu = menuFactory.newMenu(identifier);
        menu.setScreenManager(screenManager); //TODO - should set screen do this?
        screenManager.setScreen(menu);
    }

    public void showLevel(StateIdentifier world, StateIdentifier hud) {
        Level level = levelFactory.get();
        level.load(world, hud, null);
        screenManager.setScreen(level);
    }

    public void setMenuFactory(IndexMenuFactory menuFactory) {
        this.menuFactory = menuFactory;
    }

    public void setLevelFactory(Provider<Level> levelFactory) {
        this.levelFactory = levelFactory;
    }

    public void setScreenManager(GameScreenManager screenManager) {
        this.screenManager = screenManager;
    }
}
