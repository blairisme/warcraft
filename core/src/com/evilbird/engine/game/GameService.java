/*
 * Copyright (c) 2020, Blair Butterworth
 *
 * This Source Code Form is subject to the terms of the Mozilla Public
 * License, v. 2.0. If a copy of the MPL was not distributed with this
 * file, You can obtain one at http://mozilla.org/MPL/2.0/.
 */

package com.evilbird.engine.game;

import com.evilbird.engine.action.ActionFactory;
import com.evilbird.engine.audio.AudioManager;
import com.evilbird.engine.behaviour.BehaviourFactory;
import com.evilbird.engine.common.reflect.TypeRegistry;
import com.evilbird.engine.device.Device;
import com.evilbird.engine.object.GameObjectFactory;
import com.evilbird.engine.preferences.GamePreferences;

/**
 * Instances of this single provide static access to the {@link GameInjector},
 * used to obtain the central services used by the application.
 *
 * @author Blair Butterworth
 */
public class GameService implements GameInjector
{
    private static GameService instance;

    public static GameService getInstance() {
        if (instance == null) {
            instance = new GameService();
        }
        return instance;
    }

    private GameInjector injector;

    @Override
    public AudioManager getAudioService() {
        return injector.getAudioService();
    }

    @Override
    public Device getDevice() {
        return injector.getDevice();
    }

    @Override
    public GameEngine getEngine() {
        return injector.getEngine();
    }

    @Override
    public GamePreferences getPreferences() {
        return injector.getPreferences();
    }

    @Override
    public ActionFactory getActionFactory() {
        return injector.getActionFactory();
    }

    @Override
    public GameObjectFactory getObjectFactory() {
        return injector.getObjectFactory();
    }

    @Override
    public BehaviourFactory getBehaviourFactory() {
        return injector.getBehaviourFactory();
    }

    @Override
    public TypeRegistry getTypeRegistry() {
        return injector.getTypeRegistry();
    }

    public void setInjector(GameInjector injector) {
        this.injector = injector;
    }
}
