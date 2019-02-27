/*
 * Blair Butterworth (c) 2019
 *
 * This work is licensed under the MIT License. To view a copy of this
 * license, visit
 *
 *      https://opensource.org/licenses/MIT
 */

package com.evilbird.warcraft;

import com.evilbird.engine.game.GameInjector;
import dagger.Component;

import javax.inject.Singleton;

@Component(modules = {AndroidModule.class, WarcraftModuleAndroid.class})
@Singleton
public interface AndroidInjector extends GameInjector
{
}
