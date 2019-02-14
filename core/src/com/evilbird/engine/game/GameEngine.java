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
import com.evilbird.engine.game.loader.GameLoader;

import javax.inject.Inject;
import javax.inject.Provider;

/**
 * Instances of this class represent the entry point into the game, which
 * starts loading game assets and displays the game loading page.
 *
 * @author Blair Butterworth
 */
public class GameEngine extends Game implements GameScreenManager
{
    private Provider<GameLoader> gameLoaderProvider;

    @Inject
    public GameEngine(Provider<GameLoader> gameLoaderProvider) {
        this.gameLoaderProvider = gameLoaderProvider;
    }

    @Override
    public void create() {
        GameLoader gameLoader = gameLoaderProvider.get();
        gameLoader.setScreenManager(this);
        super.setScreen(gameLoader);
    }
}
