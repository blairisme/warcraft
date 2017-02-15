package com.evilbird.warcraft.desktop;

import com.evilbird.engine.game.GameEngine;

import javax.inject.Singleton;

import dagger.Component;

@Component(modules = {DesktopModule.class, WarcraftModule.class})
@Singleton
public interface DesktopInjector
{
    GameEngine newGameEngine();
}
