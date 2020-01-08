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
 * Implementors this interface provide methods that are used to obtain the
 * central services used by the application.
 *
 * @author Blair Butterworth
 */
public interface GameInjector
{
    Device getDevice();

    GameEngine getEngine();

    GamePreferences getPreferences();

    AudioManager getAudioService();

    ActionFactory getActionFactory();

    BehaviourFactory getBehaviourFactory();

    GameObjectFactory getObjectFactory();

    TypeRegistry getTypeRegistry();
}
