package com.evilbird.warcraft;

import com.evilbird.engine.loader.GameLoader;
import com.evilbird.engine.loader.GameLoaderModel;
import com.evilbird.engine.loader.GameLoaderPresenter;
import com.evilbird.engine.loader.GameLoaderView;

import dagger.Module;
import dagger.Provides;

@Module
public class GameModule
{
    @Provides
    public static GameLoader provideGameLoader(GameLoaderModel model, GameLoaderView view)
    {
        return new GameLoaderPresenter(model, view);
    }
}
