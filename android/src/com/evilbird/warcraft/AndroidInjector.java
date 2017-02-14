package com.evilbird.warcraft;
import com.evilbird.engine.GameEngine;

import javax.inject.Singleton;

import dagger.Component;

@Component(modules = {AndroidModule.class, GameModule.class, WarcraftModuleAndroid.class})
@Singleton
public interface AndroidInjector
{
    GameEngine newGameEngine();
}
