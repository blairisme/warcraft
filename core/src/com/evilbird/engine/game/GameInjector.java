/*
 * Blair Butterworth (c) 2019
 *
 * This work is licensed under the MIT License. To view a copy of this
 * license, visit
 *
 *      https://opensource.org/licenses/MIT
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
