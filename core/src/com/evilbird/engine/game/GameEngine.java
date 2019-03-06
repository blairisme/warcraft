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
import com.evilbird.engine.common.lang.Identifier;
import com.evilbird.engine.loader.LoaderScreen;
import com.evilbird.engine.menu.MenuFactory;
import com.evilbird.engine.menu.MenuIdentifier;
import com.evilbird.engine.menu.MenuOverlay;
import com.evilbird.engine.menu.MenuScreen;
import com.evilbird.engine.state.State;
import com.evilbird.engine.state.StateScreen;
import com.evilbird.engine.state.StateService;

import javax.inject.Inject;
import javax.inject.Singleton;
import java.io.IOException;

/**
 * Instances of this class represent the entry point into the game, which
 * starts loading game assets and displays the game loading page.
 *
 * @author Blair Butterworth
 */
//TODO: Dispose of menus and states when switching
@Singleton
public class GameEngine extends Game implements GameController
{
    private LoaderScreen loaderScreen;
    private MenuScreen menuScreen;
    private MenuOverlay menuOverlay;
    private MenuFactory menuFactory;
    private StateScreen stateScreen;
    private StateService stateService;

    @Inject
    public GameEngine(
        LoaderScreen loaderScreen,
        MenuScreen menuScreen,
        MenuOverlay menuOverlay,
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
        this.menuOverlay = menuOverlay;
        this.menuOverlay.setMenuScreen(menuScreen);
        this.menuOverlay.setStateScreen(stateScreen);
    }

    @Override
    public void create() {
        loaderScreen.load();
        setScreen(loaderScreen);
    }

    @Override
    public void showMenu() {
        menuScreen.setMenu(menuFactory.newMenu());
        setScreen(menuScreen);
    }

    @Override
    public void showMenu(MenuIdentifier identifier) {
        menuScreen.setMenu(menuFactory.newMenu(identifier));
        setScreen(menuScreen);
    }

    @Override
    public void showMenuOverlay(MenuIdentifier identifier) {
        menuScreen.setMenu(menuFactory.newMenu(identifier));
        setScreen(menuOverlay);
    }

    @Override
    public void showState() {
        setScreen(stateScreen);
    }

    @Override
    public void showState(Identifier identifier) throws IOException {
        stateScreen.setState(stateService.get(identifier));
        setScreen(stateScreen);
    }

    @Override
    public void saveState(Identifier identifier) throws IOException {
        State state = stateScreen.getState();
        stateService.set(identifier, state);
    }
}