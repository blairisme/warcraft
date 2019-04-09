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
import com.evilbird.engine.game.error.ErrorScreen;
import com.evilbird.engine.game.loader.LoaderScreen;
import com.evilbird.engine.menu.MenuFactory;
import com.evilbird.engine.menu.MenuIdentifier;
import com.evilbird.engine.menu.MenuOverlay;
import com.evilbird.engine.menu.MenuScreen;
import com.evilbird.engine.state.State;
import com.evilbird.engine.state.StateIdentifier;
import com.evilbird.engine.state.StateScreen;
import com.evilbird.engine.state.StateService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.inject.Inject;
import javax.inject.Singleton;

/**
 * Instances of this class represent the entry point into the game, which
 * starts loading game assets and displays the game loading page.
 *
 * @author Blair Butterworth
 */
@Singleton
public class GameEngine extends Game implements GameController
{
    private static final Logger logger = LoggerFactory.getLogger(GameEngine.class);

    private ErrorScreen errorScreen;
    private LoaderScreen loaderScreen;
    private MenuScreen menuScreen;
    private MenuOverlay menuOverlay;
    private MenuFactory menuFactory;
    private StateScreen stateScreen;
    private StateService stateService;
    private Runnable initialScreen;

    @Inject
    public GameEngine(
        ErrorScreen errorScreen,
        LoaderScreen loaderScreen,
        MenuScreen menuScreen,
        MenuOverlay menuOverlay,
        MenuFactory menuFactory,
        StateScreen stateScreen,
        StateService stateService)
    {
        this.errorScreen = errorScreen;
        this.loaderScreen = loaderScreen;
        this.loaderScreen.setEngine(this);
        this.menuScreen = menuScreen;
        this.menuScreen.setController(this);
        this.menuFactory = menuFactory;
        this.stateScreen = stateScreen;
        this.stateScreen.setController(this);
        this.stateService = stateService;
        this.menuOverlay = menuOverlay;
        this.menuOverlay.setMenuScreen(menuScreen);
        this.menuOverlay.setStateScreen(stateScreen);
        this.initialScreen = this::showMenu;
    }

    @Override
    public void create() {
        loaderScreen.load();
        setScreen(loaderScreen);
    }

    public void showInitialScreen() {
        initialScreen.run();
    }

    public void setInitialScreen(StateIdentifier identifier) {
        initialScreen = () -> showState(identifier);
    }

    public void setInitialScreen(MenuIdentifier identifier) {
        initialScreen = () -> showMenu(identifier);
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
    public void showState(StateIdentifier identifier) {
        stateScreen.setState(stateService.get(identifier));
        setScreen(stateScreen);
    }

    @Override
    public void saveState(StateIdentifier identifier) {
        State state = stateScreen.getState();
        stateService.set(identifier, state);
    }

    @Override
    public void showError(Throwable error) {
        errorScreen.setError(error);
        setScreen(errorScreen);
    }

    @Override
    public void render() {
        try {
            super.render();
        }
        catch (Throwable error) {
            logger.error(error.getMessage(), error);
            if (getScreen() != errorScreen) {
                showError(error);
            }
        }
    }
}