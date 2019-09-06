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
import com.evilbird.engine.behaviour.BehaviourFactory;
import com.evilbird.engine.common.reflect.TypeRegistry;
import com.evilbird.engine.device.Device;
import com.evilbird.engine.item.ItemFactory;

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

    ItemFactory getItemFactory();

    ActionFactory getActionFactory();

    BehaviourFactory getBehaviourFactory();

    TypeRegistry getTypeRegistry();
}
