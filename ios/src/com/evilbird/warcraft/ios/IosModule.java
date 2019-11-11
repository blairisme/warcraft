/*
 * Copyright (c) 2019, Blair Butterworth
 *
 * This work is licensed under the MIT License. To view a copy of this
 * license, visit
 *
 *        https://opensource.org/licenses/MIT
 */

package com.evilbird.warcraft.ios;

import com.evilbird.engine.action.ActionFactory;
import com.evilbird.engine.behaviour.BehaviourFactory;
import com.evilbird.engine.common.reflect.TypeRegistry;
import com.evilbird.engine.device.Device;
import com.evilbird.engine.events.EventQueue;
import com.evilbird.engine.events.Events;
import com.evilbird.engine.object.GameObjectFactory;
import com.evilbird.engine.menu.MenuFactory;
import com.evilbird.engine.preferences.GamePreferences;
import com.evilbird.engine.state.StateService;
import com.evilbird.warcraft.action.WarcraftActionFactory;
import com.evilbird.warcraft.behaviour.WarcraftBehaviourFactory;
import com.evilbird.warcraft.common.WarcraftPreferences;
import com.evilbird.warcraft.item.WarcraftObjectFactory;
import com.evilbird.warcraft.menu.WarcraftMenuFactory;
import com.evilbird.warcraft.state.WarcraftStateService;
import com.evilbird.warcraft.type.WarcraftTypeRegistry;
import dagger.Binds;
import dagger.Module;

import javax.inject.Singleton;

/**
 * Defines the bindings between the game services and their Warcraft
 * implementations.
 *
 * @author Blair Butterworth
 */
@Module
public abstract class IosModule
{
    @Binds
    @Singleton
    public abstract Device bindDevice(IosDevice device);

    @Binds
    @Singleton
    public abstract Events bindEvents(EventQueue events);

    @Binds
    @Singleton
    public abstract ActionFactory bindActionFactory(WarcraftActionFactory warcraftActionFactory);

    @Binds
    @Singleton
    public abstract BehaviourFactory bindBehaviourFactory(WarcraftBehaviourFactory warcraftBehaviourFactory);

    @Binds
    @Singleton
    public abstract MenuFactory bindMenuFactory(WarcraftMenuFactory warcraftMenuFactory);

    @Binds
    @Singleton
    public abstract GameObjectFactory bindItemFactory(WarcraftObjectFactory warcraftItemFactory);

    @Binds
    @Singleton
    public abstract StateService bindStateService(WarcraftStateService warcraftStateService);

    @Binds
    @Singleton
    public abstract TypeRegistry bindTypeRegistry(WarcraftTypeRegistry warcraftTypeRegistry);

    @Binds
    @Singleton
    public abstract GamePreferences bindPreferences(WarcraftPreferences warcraftPreferences);
}
