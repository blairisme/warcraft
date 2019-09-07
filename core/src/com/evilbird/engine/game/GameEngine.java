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
import com.evilbird.engine.common.concurrent.CompleteFuture;
import com.evilbird.engine.device.Device;
import com.evilbird.engine.device.DeviceControls;
import com.evilbird.engine.game.error.ErrorScreen;
import com.evilbird.engine.game.loader.LoaderScreen;
import com.evilbird.engine.menu.Menu;
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
import java.util.concurrent.Future;

import static com.evilbird.engine.game.GameState.AutoSave;

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

    private Device device;
    private ErrorScreen errorScreen;
    private LoaderScreen loaderScreen;
    private MenuScreen menuScreen;
    private MenuOverlay menuOverlay;
    private MenuFactory menuFactory;
    private StateScreen stateScreen;
    private StateService stateService;
    private Runnable initialScreen;
    private GameAssets gameAssets;
    private GamePreferences preferences;

    @Inject
    public GameEngine(
        Device device,
        ErrorScreen errorScreen,
        LoaderScreen loaderScreen,
        MenuScreen menuScreen,
        MenuOverlay menuOverlay,
        StateScreen stateScreen,
        MenuFactory menuFactory,
        StateService stateService,
        GameAssets gameAssets,
        GamePreferences preferences)
    {
        this.device = device;
        this.errorScreen = errorScreen;
        this.gameAssets = gameAssets;
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
        this.preferences = preferences;
    }

    @Override
    public void create() {
        try {
            logger.debug("Game engine started");
            setScreen(loaderScreen);
        }
        catch (Throwable error) {
            handleError(error);
        }
    }

    @Override
    public void dispose() {
        try {
            super.dispose();
            logger.debug("Game engine stopped");
        }
        catch (Throwable error) {
            logger.error("Unexpected error during shutdown", error);
            System.exit(1);
        }
    }

    @Override
    public Menu getMenu() {
        return menuScreen.getMenu();
    }

    @Override
    public MenuIdentifier getMenuIdentifier() {
        return menuScreen.getIdentifier();
    }

    @Override
    public State getState() {
        return stateScreen.getState();
    }

    @Override
    public StateIdentifier getStateIdentifier() {
        return stateScreen.getIdentifier();
    }

    @Override
    public boolean isMenuShown() {
        return getScreen() == menuScreen;
    }

    @Override
    public boolean isMenuOverlayShown() {
        return getScreen() == menuOverlay;
    }

    @Override
    public boolean isStateShown() {
        return getScreen() == stateScreen;
    }

    public Future<?> loadMenuAssets() {
        try {
            return gameAssets.loadMenuAssets();
        }
        catch (Throwable error) {
            handleError(error);
            return new CompleteFuture<>();
        }
    }

    @Override
    public Future<?> loadStateAssets(StateIdentifier identifier) {
        try {
            GameContext context = stateService.context(identifier);
            return gameAssets.loadStateAssets(identifier, context);
        }
        catch (Throwable error) {
            handleError(error);
            return new CompleteFuture<>();
        }
    }

    @Override
    public void pause() {
        DeviceControls controls = device.getDeviceControls();
        if (controls.supportsPause() && isStateShown()) {
            saveState(AutoSave);
            preferences.setGamePaused(true);
        }
    }

    @Override
    public void resume() {
        DeviceControls controls = device.getDeviceControls();
        if (controls.supportsPause() && preferences.getGamePaused()) {
            showState(AutoSave);
        }
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
        try {
            if (menuScreen.getIdentifier() != GameMenu.Root) {
                menuScreen.dispose();
                menuScreen.setMenu(menuFactory.get(), GameMenu.Root);
            }
            setScreen(menuScreen);
        }
        catch (Throwable error) {
            handleError(error);
        }
    }

    @Override
    public void showMenu(MenuIdentifier identifier) {
        try {
            if (identifier != menuScreen.getIdentifier()) {
                menuScreen.dispose();
                menuScreen.setMenu(menuFactory.get(identifier), identifier);
            }
            setScreen(menuScreen);
        }
        catch (Throwable error) {
            handleError(error);
        }
    }

    @Override
    public void showMenuOverlay(MenuIdentifier identifier) {
        try {
            if (identifier != menuScreen.getIdentifier()) {
                menuScreen.dispose();
                menuScreen.setMenu(menuFactory.get(identifier), identifier);
            }
            setScreen(menuOverlay);
        }
        catch (Throwable error) {
            handleError(error);
        }
    }

    @Override
    public void showState() {
        setScreen(stateScreen);
    }

    @Override
    public void showState(StateIdentifier identifier) {
        try {
            if (gameAssets.getLoadedState() != identifier) {
                GameContext context = stateService.context(identifier);
                gameAssets.loadStateAssets(identifier, context);
                gameAssets.finishLoading();
            }
            if (stateScreen.getIdentifier() != identifier) {
                State state = stateService.get(identifier);
                stateScreen.dispose();
                stateScreen.setState(state, identifier);
            }
            setScreen(stateScreen);
        }
        catch (Throwable error) {
            handleError(error);
        }
    }

    @Override
    public void saveState(StateIdentifier identifier) {
        try {
            State state = stateScreen.getState();
            stateService.set(identifier, state);
        }
        catch (Throwable error) {
            handleError(error);
        }
    }

    private void handleError(Throwable error) {
        logger.error(error.getMessage(), error);
        showError(error);
    }

    @Override
    public void showError(Throwable error) {
        loaderScreen.dispose();
        errorScreen.setError(error);
        setScreen(errorScreen);
    }

    @Override
    public void render() {
        try {
            Gdx.graphics.setTitle("Warcraft - FPS: " + Gdx.graphics.getFramesPerSecond());
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