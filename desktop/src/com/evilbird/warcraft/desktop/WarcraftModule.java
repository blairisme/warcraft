package com.evilbird.warcraft.desktop;

import com.evilbird.engine.action.ActionFactory;
import com.evilbird.engine.behaviour.BehaviourFactory;
import com.evilbird.engine.device.Device;
import com.evilbird.engine.item.ItemFactory;
import com.evilbird.engine.level.Level;
import com.evilbird.engine.menu.MenuFactory;
import com.evilbird.engine.state.StateFactory;
import com.evilbird.warcraft.action.WarcraftActionFactory;
import com.evilbird.warcraft.behaviour.WarcraftBehaviourFactory;
import com.evilbird.warcraft.item.WarcraftItemFactory;
import com.evilbird.warcraft.menu.WarcraftMenuFactory;
import com.evilbird.warcraft.state.WarcraftStateFactory;

import javax.inject.Provider;
import javax.inject.Singleton;

import dagger.Binds;
import dagger.Module;
import dagger.Provides;

@Module
public abstract class WarcraftModule
{
    @Binds
    @Singleton
    public abstract ActionFactory bindActionFactory(WarcraftActionFactory warcraftActionFactory);

    @Binds
    @Singleton
    public abstract BehaviourFactory bindBehaviourFactory(WarcraftBehaviourFactory warcraftBehaviourFactory);

    @Binds
    @Singleton
    public abstract MenuFactory bindMenuFactory(WarcraftMenuFactory warcraftMenuFactory);

//    @Provides
//    @Singleton
//    public static MenuFactory provideMenuFactory(Device device, Provider<Level> levelProvider)
//    {
//        return new WarcraftMenuFactory(device, levelProvider);
//    }

    @Binds
    @Singleton
    public abstract ItemFactory bindItemFactory(WarcraftItemFactory warcraftItemFactory);

    @Binds
    @Singleton
    public abstract StateFactory bindStateFactory(WarcraftStateFactory warcraftStateFactory);
}
