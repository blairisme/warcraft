/*
 * Blair Butterworth (c) 2019
 *
 * This work is licensed under the MIT License. To view a copy of this
 * license, visit
 *
 *      https://opensource.org/licenses/MIT
 */

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
