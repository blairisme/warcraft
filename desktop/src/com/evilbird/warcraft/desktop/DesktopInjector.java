package com.evilbird.warcraft.desktop;

import com.evilbird.engine.GameEngine;

import javax.inject.Singleton;

import dagger.Component;

@Component(modules = {DesktopModule.class, GameModule.class, WarcraftModule.class})
@Singleton
public interface DesktopInjector
{
    GameEngine newGameEngine();
}
