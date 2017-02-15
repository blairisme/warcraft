package com.evilbird.warcraft;
import com.evilbird.engine.game.GameEngine;

import javax.inject.Singleton;

import dagger.Component;

@Component(modules = {AndroidModule.class, WarcraftModuleAndroid.class})
@Singleton
public interface AndroidInjector
{
    GameEngine newGameEngine();
}
