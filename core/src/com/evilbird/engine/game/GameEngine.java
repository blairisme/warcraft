package com.evilbird.engine.game;

import com.badlogic.gdx.Game;
import com.evilbird.engine.loader.GameLoader;

import javax.inject.Inject;
import javax.inject.Provider;

public class GameEngine extends Game implements GameScreenManager
{
    private Provider<GameLoader> gameLoaderProvider;

    @Inject
    public GameEngine(Provider<GameLoader> gameLoaderProvider)
    {
        this.gameLoaderProvider = gameLoaderProvider;
    }

    @Override
    public void create()
    {
        GameLoader gameLoader = gameLoaderProvider.get();
        gameLoader.setScreenManager(this);
        super.setScreen(gameLoader);
    }
}
