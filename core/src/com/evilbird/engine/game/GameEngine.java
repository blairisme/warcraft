/*
 * Blair Butterworth (c) 2019
 *
 * This work is licensed under the MIT License. To view a copy of this
 * license, visit
 *
 *      https://opensource.org/licenses/MIT
 */

package com.evilbird.engine.game;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Preferences;
import com.evilbird.engine.loader.LoaderScreen;
import com.evilbird.engine.menu.MenuFactory;
import com.evilbird.engine.menu.MenuIdentifier;
import com.evilbird.engine.menu.MenuScreen;
import com.evilbird.engine.state.State;
import com.evilbird.engine.state.StateIdentifier;
import com.evilbird.engine.state.StateScreen;
import com.evilbird.engine.state.StateService;

import javax.inject.Inject;

/**
 * Instances of this class represent the entry point into the game, which
 * starts loading game assets and displays the game loading page.
 *
 * @author Blair Butterworth
 */
public class GameEngine extends Game implements GameController
{
    private LoaderScreen loaderScreen;
    private MenuScreen menuScreen;
    private MenuFactory menuFactory;
    private StateScreen stateScreen;
    private StateService stateService;

    @Inject
    public GameEngine(
        LoaderScreen loaderScreen,
        MenuScreen menuScreen,
        MenuFactory menuFactory,
        StateScreen stateScreen,
        StateService stateService)
    {
        this.loaderScreen = loaderScreen;
        this.loaderScreen.setController(this);
        this.menuScreen = menuScreen;
        this.menuScreen.setController(this);
        this.menuFactory = menuFactory;
        this.stateScreen = stateScreen;
        this.stateScreen.setController(this);
        this.stateService = stateService;
    }

    @Override
    public void create() {
        loaderScreen.load();
        setScreen(loaderScreen);
    }

    @Override
    public void showMenuRoot() {
        menuScreen.setMenu(menuFactory.newMenu());
        setScreen(menuScreen);
    }

    @Override
    public void showMenu(MenuIdentifier identifier) {
        menuScreen.setMenu(menuFactory.newMenu(identifier));
        setScreen(menuScreen);
    }

    @Override
    public void showState(StateIdentifier identifier) {
        stateScreen.setState(stateService.get(identifier));
        setScreen(stateScreen);
    }

    @Override
    public void saveState(StateIdentifier identifier) {
        State state = stateScreen.getState();
        stateService.set(identifier, state);
    }
}