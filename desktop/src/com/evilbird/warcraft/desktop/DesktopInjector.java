package com.evilbird.warcraft.desktop;

import com.evilbird.engine.game.GameEngine;
import dagger.Component;

import javax.inject.Singleton;

@Component(modules = {DesktopModule.class, WarcraftModule.class})
@Singleton
public interface DesktopInjector
{
    GameEngine newGameEngine();
}
